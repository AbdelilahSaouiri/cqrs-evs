package net.ensah.shipementqueryservice.service;


import net.ensah.coreapi.events.ShipmentCancelledEvent;
import net.ensah.coreapi.events.ShipmentCreatedEvent;
import net.ensah.coreapi.events.ShipmentUpdatedEvent;
import net.ensah.shipementqueryservice.entity.Shipment;
import net.ensah.shipementqueryservice.repository.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class ShipmentServiceEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ShipmentServiceEventHandler.class);
    private final ShipmentRepository shipmentRepository;
    private final InfoEndpoint infoEndpoint;

    public ShipmentServiceEventHandler(ShipmentRepository shipmentRepository1, InfoEndpoint infoEndpoint) {
        this.shipmentRepository = shipmentRepository1;
        this.infoEndpoint = infoEndpoint;
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
        log.info("ShipmentCancelledEvent: {} ", event);
        Shipment shipment = shipmentRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Shipment not found!"));
        shipment.setStatus(event.getStatus());
        shipmentRepository.save(shipment);
    }

}
