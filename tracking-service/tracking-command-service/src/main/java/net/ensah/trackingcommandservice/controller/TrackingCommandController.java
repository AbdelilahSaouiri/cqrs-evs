package net.ensah.trackingcommandservice.controller;


import net.ensah.commands.ArchiveTrackingCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/command/tracking")
public class TrackingCommandController {

    private final CommandGateway commandGateway;

    public TrackingCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PatchMapping("/{id}/archive")
    public CompletableFuture<ResponseEntity<String>> archiveTracking(@PathVariable String id) {

        return commandGateway.send(new ArchiveTrackingCommand(id, LocalDate.now()));
    }

}
