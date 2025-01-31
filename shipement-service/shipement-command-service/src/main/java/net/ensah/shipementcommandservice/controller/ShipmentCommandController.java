package net.ensah.shipementcommandservice.controller;


import net.ensah.coreapi.commands.CancelShipmentCommand;
import net.ensah.coreapi.commands.CreateShipmentCommand;
import net.ensah.coreapi.commands.UpdateShipmentCommand;
import net.ensah.coreapi.dtos.ShipmentRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CompletableFuture<String> createNewShipment(@RequestBody ShipmentRequestDTO request){
        log.info("Creating new shipment {}",request);
        return commandGateway.send(new CreateShipmentCommand(
                UUID.randomUUID().toString(),
                request.getSenderName(),
                request.getRecipientName(),
                request.getRecipientAddress(),
                LocalDate.now(),
                request.getRecipientPhone(),
                request.getLocation(),
                request.getWeight()
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CompletableFuture<String>  UpdateShipment(@PathVariable String id,
                                                       @RequestBody ShipmentRequestDTO request){
        log.info("Updating shipment {}",id);
        return commandGateway.send(new UpdateShipmentCommand(
                id,
                request.getSenderName(),
                request.getRecipientName(),
                request.getRecipientAddress(),
                request.getRecipientAddress(),
                request.getLocation()
                ));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CompletableFuture<String>  CancelShipment(@PathVariable String id){
        log.info("Canceling shipment {}",id);
        return commandGateway.send(new CancelShipmentCommand(id));
    }

}
