package net.ensah.trackingqueryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.ensah.coreapi.enums.Location;
import net.ensah.coreapi.enums.ShipmentStatus;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class TrackingView {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus trackingStatus;
    @Enumerated(EnumType.STRING)
    private Location location;
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
