package net.ensah.shipementqueryservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import net.ensah.coreapi.enums.Location;
import net.ensah.coreapi.enums.ShipmentStatus;

import java.time.LocalDate;

@Entity
public class Shipment {
    @Id
    private String id;
    private  String senderName;
    private  String recipientName;
    private  String recipientAddress;
    @Column(unique = true)
    private  String recipientPhoneNumber;
    private int weight;
    @Enumerated(EnumType.STRING)
    private Location location;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  LocalDate createAt;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus  status;


    public Shipment() {
    }

    public Shipment(String id, String senderName, String recipientName, String recipientAddress, String recipientPhoneNumber, int weight, Location location, LocalDate createAt, ShipmentStatus status) {
        this.id = id;
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.weight = weight;
        this.location = location;
        this.createAt = createAt;
        this.status = status;
    }

    public Shipment(String string) {
    }

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
