package net.ensah.trackingcommandservice.controller;


import net.ensah.coreapi.commands.ArchiveTrackingCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CompletableFuture<ResponseEntity<String>> archiveTracking(@PathVariable String id) {

        return commandGateway.send(new ArchiveTrackingCommand(id, LocalDate.now()));
    }

}
