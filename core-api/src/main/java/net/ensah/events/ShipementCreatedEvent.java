package net.ensah.events;


import java.io.Serializable;
import java.time.LocalDate;

public class ShipementCreatedEvent extends BaseEvent<String> implements Serializable {

    private String senderName;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhoneNumber;
    private String deliveryDate;
    private String status;
    private int weight;

    public ShipementCreatedEvent(String id,
                                 String senderName,
                                 String receiverName,
                                 String receiverAddress,
                                 String receiverPhoneNumber,
                                 LocalDate deliveryDate,
                                 String status,
                                 int weight) {
        super(id);
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.deliveryDate = deliveryDate.toString(); // Convertir en chaîne
        this.status = status;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }
}
