package net.ensah.shipementqueryservice.service;


import net.ensah.events.ShipementCreatedEvent;
import net.ensah.shipementqueryservice.entity.Shipment;
import net.ensah.shipementqueryservice.repository.ShipementRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class ShipmentServiceEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ShipmentServiceEventHandler.class);
    private final ShipementRepository shipementRepository;

    public ShipmentServiceEventHandler(ShipementRepository shipementRepository) {
        this.shipementRepository = shipementRepository;
    }

    @EventHandler
    public void on(ShipementCreatedEvent event) {
        log.info("***********************");
        log.info("ShipmentCreatedEvent: {}", event);
             Shipment shipment =new Shipment();
             shipment.setId(event.getId());
             shipment.setSenderName(event.getSenderName());
             shipment.setReceiverName(event.getReceiverName());
             shipment.setWeight(event.getWeight());
             shipment.setReceiverAddress(event.getReceiverAddress());
             shipment.setReceiverPhoneNumber(event.getReceiverPhoneNumber());
             shipment.setDeliveryDate(event.getDeliveryDate());
             shipment.setStatus(event.getStatus());
             shipment.setCreateAt(LocalDate.now());
             shipementRepository.save(shipment);
    }
}
