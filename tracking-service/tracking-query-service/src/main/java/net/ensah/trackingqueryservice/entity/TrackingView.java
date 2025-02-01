package net.ensah.trackingqueryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import net.ensah.coreapi.enums.Location;
import net.ensah.coreapi.enums.ShipmentStatus;

import java.time.LocalDate;

@Entity
public class TrackingView {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus trackingStatus;
    @Enumerated(EnumType.STRING)
    private Location location;
    private boolean archived;
    private LocalDate archivedAt;

    public TrackingView() {
    }

    public TrackingView(String id, ShipmentStatus trackingStatus, Location location, boolean archived, LocalDate archivedAt) {
        this.id = id;
        this.trackingStatus = trackingStatus;
        this.location = location;
        this.archived = archived;
        this.archivedAt = archivedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTrackingStatus(ShipmentStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void setArchivedAt(LocalDate archivedAt) {
        this.archivedAt = archivedAt;
    }

    public String getId() {
        return id;
    }

    public ShipmentStatus getTrackingStatus() {
        return trackingStatus;
    }

    public Location getLocation() {
        return location;
    }



    public boolean isArchived() {
        return archived;
    }

    public LocalDate getArchivedAt() {
        return archivedAt;
    }
}
