package net.ensah.shipementcommandservice.controller;


import net.ensah.commands.CreateShipementCommand;
import net.ensah.dtos.requests.ShipementRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/ship")

public class ShipmentCommandController {

    private final CommandGateway commandGateway;

    public ShipmentCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createNewShipment(@RequestBody ShipementRequestDto request){
        return commandGateway.send(new CreateShipementCommand(
                UUID.randomUUID().toString(),
                request.senderName(),
                request.recipientName(),
                request.recipientAddress(),
                LocalDate.now(),
                request.recipientPhone(),
                request.weight()
        ));
    }

}
