package net.ensah.commands;

import net.ensah.enums.TrackingStatus;

import java.time.LocalDate;

public class UpdateTrackingStatusCommand<String> extends BaseCommand<String> {

     private String trackingId;
     private TrackingStatus status ;
     private String location ;
     private LocalDate timestamp ;

    public UpdateTrackingStatusCommand(String id, String trackingId, TrackingStatus status, String location, LocalDate timestamp) {
        super(id);
        this.trackingId = trackingId;
        this.status = status;
        this.location = location;
        this.timestamp = timestamp;
    }
}
