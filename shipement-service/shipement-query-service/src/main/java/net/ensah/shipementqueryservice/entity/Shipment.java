package net.ensah.shipementqueryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor   @Getter
public class Shipment {
    @Id
    private String id;
    private  String senderName;
    private  String receiverName;
    private  String receiverAddress;
    private  String receiverPhoneNumber;
    private  String deliveryDate;
    private int weight;
    private  String status;
    private  LocalDate createAt;

    public void setId(String id) {
        this.id = id;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
