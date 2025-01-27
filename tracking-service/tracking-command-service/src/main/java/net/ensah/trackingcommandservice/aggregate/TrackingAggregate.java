package net.ensah.trackingcommandservice.aggregate;


import net.ensah.commands.ArchiveTrackingCommand;
import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;
import net.ensah.events.TrackingArchivedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import java.time.Instant;


@Aggregate
public class TrackingAggregate {
    @AggregateIdentifier
    private String id;
    private ShipmentStatus shipmentStatus;
    private Location location;
    private Instant timestamp;
    private boolean archived=false;

    public TrackingAggregate() {
        // for Axon Framework
    }

    @CommandHandler
    public void handle(ArchiveTrackingCommand command) {
        if (archived) {
            throw new IllegalStateException("Tracking is already archived.");
        }
        AggregateLifecycle.apply(new TrackingArchivedEvent(
                command.getId()
        ));
    }

    @EventSourcingHandler
    public void on(TrackingArchivedEvent event) {
        this.archived = true;
    }
}
