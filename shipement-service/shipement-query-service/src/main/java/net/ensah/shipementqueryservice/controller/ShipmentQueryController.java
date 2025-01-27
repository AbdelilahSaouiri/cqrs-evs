package net.ensah.shipementqueryservice.controller;

import net.ensah.queries.GetAllShipmentsQuery;
import net.ensah.queries.GetShipmentById;
import net.ensah.queries.GetShipmentByRecipientPhoneNumber;
import net.ensah.shipementqueryservice.entity.Shipment;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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

    @GetMapping("/{page}/{size}")
    public CompletableFuture<List<Shipment>> getAllShipments(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size) {
        return queryGateway.query(
                new GetAllShipmentsQuery(page,size), ResponseTypes.multipleInstancesOf(Shipment.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Shipment>> getShipmentById(@PathVariable("id") String id){
        CompletableFuture<Shipment> query = queryGateway.query(new GetShipmentById(id), ResponseTypes.instanceOf(Shipment.class));
        return query.thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{phone}")
    public CompletableFuture<ResponseEntity<Shipment>> getShipmentByRecipientPhoneNumber(@PathVariable("phone") String phone){
        CompletableFuture<Shipment> query = queryGateway.query(new GetShipmentByRecipientPhoneNumber(phone), ResponseTypes.instanceOf(Shipment.class));
        return query.thenApply(ResponseEntity::ok);
    }

}
