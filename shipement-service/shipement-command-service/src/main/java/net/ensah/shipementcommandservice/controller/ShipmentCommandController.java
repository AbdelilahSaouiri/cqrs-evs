package net.ensah.shipementcommandservice.controller;


import net.ensah.commands.CancelShipmentCommand;
import net.ensah.commands.CreateShipmentCommand;
import net.ensah.commands.UpdateShipmentCommand;
import net.ensah.dtos.ShipmentRequestDto;
import net.ensah.shipementcommandservice.aggregate.ShipmentAggregate;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/command/ship")

public class ShipmentCommandController {



    private final CommandGateway commandGateway;
    private static final Logger log = LoggerFactory.getLogger(ShipmentCommandController.class);

    public ShipmentCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createNewShipment(@RequestBody ShipmentRequestDto request){
        log.info("Creating new shipment {}",request);
        return commandGateway.send(new CreateShipmentCommand(
                UUID.randomUUID().toString(),
                request.senderName(),
                request.recipientName(),
                request.recipientAddress(),
                LocalDate.now(),
                request.recipientPhone(),
                request.location(),
                request.weight()
        ));
    }

    @PutMapping("/{id}")
    public CompletableFuture<String>  UpdateShipment(@PathVariable String id,
                                                       @RequestBody ShipmentRequestDto request){
        log.info("Updating shipment {}",id);
        return commandGateway.send(new UpdateShipmentCommand(
                id,
                request.senderName(),
                request.recipientName(),
                request.recipientAddress(),
                request.recipientPhone(),
                request.location()
                ));
    }

    @PatchMapping("/{id}")
    public CompletableFuture<String>  CancelShipment(@PathVariable String id){
        log.info("Canceling shipment {}",id);
        return commandGateway.send(new CancelShipmentCommand(id));
    }

}
