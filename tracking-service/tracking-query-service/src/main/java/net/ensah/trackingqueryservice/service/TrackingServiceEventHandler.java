package net.ensah.trackingqueryservice.service;


import net.ensah.coreapi.events.ShipmentCreatedEvent;
import net.ensah.coreapi.events.ShipmentUpdatedEvent;
import net.ensah.coreapi.events.TrackingArchivedEvent;
import net.ensah.trackingqueryservice.entity.TrackingView;
import net.ensah.trackingqueryservice.repository.TrackingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TrackingServiceEventHandler {

    private static final Logger log = LoggerFactory.getLogger(TrackingServiceEventHandler.class);
    private final TrackingRepository trackingRepository;

    public TrackingServiceEventHandler(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }


    @EventHandler
    public void on(ShipmentCreatedEvent event){
        log.info("Received ShipmentCreatedEvent");
        TrackingView trackingView=new TrackingView();
        log.info("location {}",event.getLocation());
        trackingView.setLocation(event.getLocation());
        trackingView.setTrackingStatus(event.getStatus());
        trackingView.setArchivedAt(LocalDate.now());
        trackingView.setArchived(true);
        trackingView.setId(event.getId());
        trackingRepository.save(trackingView);
    }

    @EventHandler
    public void on(ShipmentUpdatedEvent event) {
        log.info("shipmentUpdated event {}",event);
        TrackingView trackingView = trackingRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Tracking not found!"));
        trackingView.setTrackingStatus(event.getShipmentStatus());
        trackingView.setArchivedAt(LocalDate.now());
        trackingView.setLocation(event.getLocation());
        trackingView.setArchived(true);
        trackingRepository.save(trackingView);
    }

    @EventHandler
    public void on(TrackingArchivedEvent event) {
        log.info("trackingArchived event {}",event);
        TrackingView trackingView = trackingRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Tracking not found!"));
        trackingView.setArchived(true);
        trackingView.setArchivedAt(LocalDate.now());
        trackingRepository.save(trackingView);
    }
}
