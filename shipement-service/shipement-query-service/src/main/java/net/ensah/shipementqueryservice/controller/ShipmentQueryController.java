package net.ensah.shipementqueryservice.controller;

import net.ensah.queries.GetAllShipmentsQuery;
import net.ensah.queries.GetShipmentById;
import net.ensah.shipementqueryservice.entity.Shipment;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/query/shipment")
public class ShipmentQueryController {

    private final QueryGateway queryGateway;


    public ShipmentQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public CompletableFuture<List<Shipment>> getAllShipments(){
        return queryGateway.query(
                new GetAllShipmentsQuery(), ResponseTypes.multipleInstancesOf(Shipment.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<Shipment> getShipmentById(@PathVariable("id") String id){
        return queryGateway.query(new GetShipmentById(id), ResponseTypes.instanceOf(Shipment.class));
    }

}
