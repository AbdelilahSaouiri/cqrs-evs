package net.ensah.shipementcommandservice.aggregate;


import net.ensah.coreapi.commands.CancelShipmentCommand;
import net.ensah.coreapi.commands.CreateShipmentCommand;
import net.ensah.coreapi.commands.UpdateShipmentCommand;
import net.ensah.coreapi.enums.Location;
import net.ensah.coreapi.enums.ShipmentStatus;
import net.ensah.coreapi.events.ShipmentCancelledEvent;
import net.ensah.coreapi.events.ShipmentCreatedEvent;
import net.ensah.coreapi.events.ShipmentUpdatedEvent;
import net.ensah.coreapi.exceptions.CancelShipmentException;
import net.ensah.coreapi.exceptions.CreateShipmentException;
import net.ensah.coreapi.exceptions.UpdateShipmentException;
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
    private String id;
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
        //for axon framework
    }

    @CommandHandler
    public ShipmentAggregate(CreateShipmentCommand command) {
        log.info("CreateShipmentCommand received {}",command.getId());
        try {
            if (command.getWeight() <= 0) {
                log.warn("Weight must be greater than zero.");
                throw new CreateShipmentException("Weight must be greater than zero.");
            }
            if (!command.getRecipientPhoneNumber().matches("\\+[0-9]{10,13}")) {
                log.warn("Invalid phone number format.");
                throw new CreateShipmentException("Invalid phone number format.");
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

        } catch (IllegalArgumentException e) {
            log.error("Exception during shipment creation: {}", e.getMessage());
            throw e;
        }
    }


    @EventSourcingHandler
    public void on(ShipmentCreatedEvent event) {
       log.info("ShipmentCreatedEvent  received : {}", event.getId());
        this.id=event.getId();
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
            throw new UpdateShipmentException("Cannot update a cancelled shipment.");
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
        try {
            log.info("CancelShipmentCommand received for shipment ID: {}", command.getId());

            if (this.status.equals(ShipmentStatus.CANCELLED)) {
                log.warn("shipment is already cancelled {}", command.getId());
                throw new CancelShipmentException("Shipment is already cancelled.");
            }
            else if (this.status.equals(ShipmentStatus.DELIVERED)) {
                log.warn("shipment already delivered {}", command.getId());
                throw new CancelShipmentException("Cannot cancel a delivered shipment.");
            } else {
                apply(new ShipmentCancelledEvent(command.getId(), ShipmentStatus.CANCELLED));
            }
        } catch (CancelShipmentException e) {
            log.error("Error while processing CancelShipmentCommand for shipment ID: {}. Message: {}", command.getId(), e.getMessage());
            throw e;
        }
    }


    @EventSourcingHandler
    public void on(ShipmentUpdatedEvent event) {
        log.info("ShipmentUpdatedEvent  received : {}", event.getId());
        this.id = event.getId();
        this.senderName = event.getSenderName();
        this.recipientName = event.getRecipientName();
        this.recipientAddress = event.getRecipientAddress();
        this.recipientPhone = event.getRecipientPhoneNumber();
        this.status=event.getShipmentStatus();
        this.location=event.getLocation();
        if (this.status == ShipmentStatus.CANCELLED) {
            log.warn("Shipment is now marked as CANCELLED.{}",event.getId());
            this.cancelled = true;
        }
    }

    @EventSourcingHandler
    public void on(ShipmentCancelledEvent event) {
        log.info("shipmentCancelledEvent  received : {}", event.getId());
        this.id= event.getId();
        this.status = ShipmentStatus.CANCELLED;
        this.cancelled=true;
    }
}
