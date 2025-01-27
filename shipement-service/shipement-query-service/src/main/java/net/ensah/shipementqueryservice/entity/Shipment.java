package net.ensah.shipementqueryservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class Shipment {
    @Id
    private String id;
    private  String senderName;
    private  String recipientName;
    private  String recipientAddress;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  LocalDate deliveryDate;
    private  String recipientPhoneNumber;
    private int weight;
    @Enumerated(EnumType.STRING)
    private Location location;
    private  LocalDate createAt;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus  status;


    public String getId() {
        return id;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public int getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }
}
