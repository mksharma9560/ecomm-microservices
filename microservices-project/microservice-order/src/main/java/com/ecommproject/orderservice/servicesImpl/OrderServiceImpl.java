package com.ecommproject.orderservice.servicesImpl;

import com.ecommproject.orderservice.entities.Order;
import com.ecommproject.orderservice.entities.OrderLineItems;
import com.ecommproject.orderservice.entityDto.InventoryResponse;
import com.ecommproject.orderservice.entityDto.OrderLineItemsDto;
import com.ecommproject.orderservice.entityDto.OrderRequest;
import com.ecommproject.orderservice.event.OrderPlacedEvent;
import com.ecommproject.orderservice.repository.OrderRepo;
import com.ecommproject.orderservice.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    //    private WebClient webClient;
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private Tracer tracer;
    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public String createOrder(OrderRequest orderRequest) {
        logger.info("=====Inside createOrder of OrderServiceImpl=====");
        Order order = new Order();
        logger.info("=====Set Order Number");
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> olItemsList = orderRequest.getOrderLineItemsDtoList().stream().map(items -> dtoToOrderLineItems(items)).toList();
        order.setOrderLineItemsList(olItemsList);
        logger.info("=====Order Detail : {}", order);
        List<String> skuCodes = order.getOrderLineItemsList().stream().map(orderLineItems -> orderLineItems.getOrderLineSKU()).toList();

//      Added try-fibnally to add specfic span id for specific serviec call

        Span inventoryServiceLookup = tracer.nextSpan().name("inventoryServiceLookup");
        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())) {
            log.info("Calling inventory service to check stock availability");

//          Call inventory service using webclient if product is in stock.
//          It's a asynch call but block() method will make it sysnc call

            InventoryResponse[] inventoryResponsesArray = new InventoryResponse[0];
            inventoryResponsesArray = webClientBuilder.build().get().uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();
            boolean result = Arrays.stream(inventoryResponsesArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());
            logger.info("=====Inside createOrder of OrderServiceIMPL- after Calling the Invetory Service with InventoryResponse[] result: {}", result);
            if (result) {
                orderRepo.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "Order Placed Successfully";
            } else {
                throw new IllegalArgumentException("Product is not in stock Please try again later");
            }
        } finally {
            inventoryServiceLookup.end();
        }
    }
    // DTO Methods
    public OrderLineItems dtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = this.modelMapper.map(orderLineItemsDto, OrderLineItems.class);
        return orderLineItems;
    }
    public OrderLineItemsDto orderLineItemToDto(OrderLineItems orderLineItems) {
        OrderLineItemsDto orderLineItemsDto = this.modelMapper.map(orderLineItems, OrderLineItemsDto.class);
        return orderLineItemsDto;
    }
}