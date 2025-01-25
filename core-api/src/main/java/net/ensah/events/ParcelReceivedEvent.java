package net.ensah.events;

import net.ensah.enums.TrackingRecordStatus;

import java.time.LocalDate;

public class ParcelReceivedEvent<String> extends BaseEvent<String>{

    private String trackingId;
    private TrackingRecordStatus status;
    private String location;
    private LocalDate timestamp ;

    public ParcelReceivedEvent(String id, String trackingId, TrackingRecordStatus status, String location, LocalDate timestamp) {
        super(id);
        this.trackingId = trackingId;
        this.status = status;
        this.location = location;
        this.timestamp = timestamp;
    }
}
