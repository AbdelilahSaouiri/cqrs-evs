package net.ensah.trackingqueryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class TrackingView {
    @Id
    private String id;
    private ShipmentStatus trackingStatus;
    @Enumerated(EnumType.STRING)
    private Location location;
    private LocalDate timestamp;
    private boolean archived;
    private LocalDate archivedAt;

    public void setId(String id) {
        this.id = id;
    }

    public void setTrackingStatus(ShipmentStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public boolean isArchived() {
        return archived;
    }

    public LocalDate getArchivedAt() {
        return archivedAt;
    }
}
