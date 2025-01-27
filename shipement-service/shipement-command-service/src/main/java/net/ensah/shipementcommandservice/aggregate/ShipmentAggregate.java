package net.ensah.shipementcommandservice.aggregate;


import net.ensah.commands.CancelShipmentCommand;
import net.ensah.commands.CreateShipmentCommand;
import net.ensah.commands.UpdateShipmentCommand;
import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;
import net.ensah.events.ShipmentCancelledEvent;
import net.ensah.events.ShipmentCreatedEvent;
import net.ensah.events.ShipmentUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ShipmentAggregate {

    private static final Logger log = LoggerFactory.getLogger(ShipmentAggregate.class);

    @AggregateIdentifier
    private String shipmentId;
    private String senderName;
    private String recipientName;
    private String recipientAddress;
    private LocalDate deliveryDate;
    private String recipientPhone;
    private int weight;
    private boolean cancelled=false;
    private ShipmentStatus status;
    private Location location;


    public ShipmentAggregate() {
        //for axon
    }

    @CommandHandler
    public ShipmentAggregate(CreateShipmentCommand command) {
       log.info("CreateShipmentCommand  received  ");
        if (command.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero.");
        }
        if (!command.getRecipientPhoneNumber().matches("\\+?[0-9]{10,13}")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        apply(new ShipmentCreatedEvent(
                command.getId(),
                command.getSenderName(),
                command.getRecipientName(),
                command.getRecipientAddress(),
                command.getRecipientPhoneNumber(),
                ShipmentStatus.IN_PROGRESS,
                command.getWeight(),
                command.getLocation()
        ));
    }

    @EventSourcingHandler
    public void on(ShipmentCreatedEvent event) {
       log.info("ShipmentCreatedEvent  received : {}", event.getId());
        this.shipmentId=event.getId();
        this.senderName = event.getSenderName();
        this.recipientName = event.getRecipientName();
        this.recipientAddress = event.getRecipientAddress();
        this.recipientPhone = event.getRecipientPhoneNumber();
        this.weight= event.getWeight();
        this.status=event.getStatus();
        this.location=event.getLocation();

    }

    @CommandHandler
    public void handle(UpdateShipmentCommand command) {
        log.info("UpdateShipmentCommand  received  ");
        if (cancelled) {
            throw new IllegalStateException("Cannot update a cancelled shipment.");
        }
        apply(new ShipmentUpdatedEvent(
                command.getId(),
                command.getSenderName(),
                command.getRecipientName(),
                command.getRecipientAddress(),
                ShipmentStatus.IN_PROGRESS,
                command.getRecipientPhoneNumber(),
                command.getLocation()
        ));
    }

    @CommandHandler
    public void handle(CancelShipmentCommand command) {
        log.info("CancelShipmentCommand received for shipment ID: {}", command.getId());

        if (this.status == ShipmentStatus.CANCELLED) {
            throw new IllegalStateException("Shipment is already cancelled.");
        }

        if (this.status == ShipmentStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel a delivered shipment.");
        }
        apply(new ShipmentCancelledEvent(command.getId(), ShipmentStatus.CANCELLED));
    }

    @EventSourcingHandler
    public void on(ShipmentUpdatedEvent event) {
        log.info("ShipmentUpdatedEvent  received : {}", event.getId());
        this.shipmentId = event.getId();
        this.senderName = event.getSenderName();
        this.recipientName = event.getRecipientName();
        this.recipientAddress = event.getRecipientAddress();
        this.recipientPhone = event.getRecipientPhoneNumber();
        this.status=event.getShipmentStatus();
        this.location=event.getLocation();
        if (this.status == ShipmentStatus.CANCELLED) {
            this.cancelled = true;
        }
    }

    @EventSourcingHandler
    public void on(ShipmentCancelledEvent event) {
        this.cancelled = true;
        this.status = ShipmentStatus.CANCELLED;
    }
}
