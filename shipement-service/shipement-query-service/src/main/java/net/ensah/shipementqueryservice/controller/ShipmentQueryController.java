package net.ensah.shipementqueryservice.controller;

import net.ensah.shipementqueryservice.service.ShipmentServiceEventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/query/sh")
public class ShipmentQueryController {

    private final QueryGateway queryGateway;
    private final ShipmentServiceEventHandler serviceEventHandler;

    public ShipmentQueryController(QueryGateway queryGateway, ShipmentServiceEventHandler serviceEventHandler) {
        this.queryGateway = queryGateway;
        this.serviceEventHandler = serviceEventHandler;
    }
}
