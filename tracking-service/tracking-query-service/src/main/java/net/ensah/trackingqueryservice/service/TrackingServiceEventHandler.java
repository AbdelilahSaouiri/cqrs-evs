package net.ensah.trackingqueryservice.service;


import lombok.extern.slf4j.Slf4j;
import net.ensah.events.ShipmentCreatedEvent;
import net.ensah.events.ShipmentUpdatedEvent;
import net.ensah.events.TrackingArchivedEvent;
import net.ensah.trackingqueryservice.entity.TrackingView;
import net.ensah.trackingqueryservice.repository.TrackingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Slf4j
@Service
public class TrackingServiceEventHandler {

    private final TrackingRepository trackingRepository;

    public TrackingServiceEventHandler(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }


    @EventHandler
    public void on(ShipmentCreatedEvent event){
        TrackingView trackingView=new TrackingView();
        log.info("********************************");
        log.info("location {}",event.getLocation());
        trackingView.setLocation(event.getLocation());
        trackingView.setTrackingStatus(event.getStatus());
        trackingView.setTimestamp(event.getDeliveryDate());
        trackingView.setId(event.getId());
        trackingRepository.save(trackingView);
    }

    @EventHandler
    public void on(ShipmentUpdatedEvent event) {
        TrackingView trackingView = trackingRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Tracking not found!"));
        trackingView.setTrackingStatus(event.getShipmentStatus());
        trackingView.setLocation(event.getLocation());
        trackingRepository.save(trackingView);
    }

    @EventHandler
    public void on(TrackingArchivedEvent event) {
        TrackingView trackingView = trackingRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Tracking not found!"));
        trackingView.setArchived(true);
        trackingView.setArchivedAt(LocalDate.now());
        trackingRepository.save(trackingView);
    }
}
