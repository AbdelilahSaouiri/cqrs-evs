package net.ensah.shipementqueryservice.service;


import net.ensah.events.ShipmentCancelledEvent;
import net.ensah.events.ShipmentCreatedEvent;
import net.ensah.events.ShipmentUpdatedEvent;
import net.ensah.shipementqueryservice.entity.Shipment;
import net.ensah.shipementqueryservice.repository.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class ShipmentServiceEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ShipmentServiceEventHandler.class);
    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceEventHandler(ShipmentRepository shipmentRepository1) {
        this.shipmentRepository = shipmentRepository1;
    }

    @EventHandler
    public void on(ShipmentCreatedEvent event) {
        log.info("ShipmentCreatedEvent: {}", event);
             Shipment shipment =new Shipment();
             shipment.setId(event.getId());
             shipment.setSenderName(event.getSenderName());
             shipment.setRecipientName(event.getRecipientName());
             shipment.setWeight(event.getWeight());
             shipment.setRecipientAddress(event.getRecipientAddress());
             shipment.setRecipientPhoneNumber(event.getRecipientPhoneNumber());
             shipment.setDeliveryDate(event.getDeliveryDate());
             shipment.setStatus(event.getStatus());
             shipment.setLocation(event.getLocation());
             shipment.setCreateAt(LocalDate.now());
             shipmentRepository.save(shipment);
    }

    @EventHandler
    public void on(ShipmentUpdatedEvent event) {
        Shipment shipment = shipmentRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Shipment not found!"));

        shipment.setSenderName(event.getSenderName());
        shipment.setRecipientName(event.getRecipientName());
        shipment.setRecipientAddress(event.getRecipientAddress());
        shipment.setRecipientPhoneNumber(event.getRecipientPhoneNumber());
        shipment.setStatus(event.getShipmentStatus());
        shipment.setLocation(event.getLocation());
        shipmentRepository.save(shipment);
    }

    @EventHandler
    public void on(ShipmentCancelledEvent event) {
        Shipment shipment = shipmentRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Shipment not found!"));
        shipment.setStatus(event.getStatus());
        shipmentRepository.save(shipment);
    }

}
