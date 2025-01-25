package net.ensah.shipementcommandservice.aggregate;


import net.ensah.commands.CreateShipementCommand;
import net.ensah.events.ShipementCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

@Aggregate
public class ShipmentAggregate {

    private static final Logger log = LoggerFactory.getLogger(ShipmentAggregate.class);

    @AggregateIdentifier
    private String id;
    private String senderName;
    private String recipientName;
    private String recipientAddress;
    private String deliveryDate;
    private String recipientPhone;
    private int weight;


    public ShipmentAggregate() {
        //for axon
    }

    @CommandHandler
    public ShipmentAggregate(CreateShipementCommand command) {
       log.info("CreateShipmentCommand  received  ");
        AggregateLifecycle.apply(new ShipementCreatedEvent(
                command.getId(),
                command.getSenderName(),
                command.getRecipientName(),
                command.getRecipientAddress(),
                command.getRecipientPhone(),
                command.getDeliveryDate(),
                "IN_PROGRESS",
                command.getWeight()
        ));
    }

    @EventSourcingHandler
    public void on(ShipementCreatedEvent event) {
       log.info("CreateShipementevent  received : {}", event.getId());
        this.id=event.getId();
        this.senderName = event.getSenderName();
        this.recipientName = event.getReceiverName();
        this.recipientAddress = event.getReceiverAddress();
        this.deliveryDate = event.getDeliveryDate();
        this.recipientPhone = event.getReceiverPhoneNumber();
        this.weight= event.getWeight();
    }
}
