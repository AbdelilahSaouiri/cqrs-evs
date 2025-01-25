package net.ensah.events;

import net.ensah.enums.ShipmentStatus;

import java.time.LocalDate;

public class ShipmentUpdatedEvent<String>  extends  BaseEvent<String> {

    private String shipmentId;
    private String  senderName ;
    private String receiverName;
    private String receiverAddress ;
    private String deliveryDate;
    private ShipmentStatus status;
   private LocalDate timestamp;

    public ShipmentUpdatedEvent(String id, String shipmentId,
                                String senderName,
                                String receiverName,
                                String receiverAddress,
                                String deliveryDate,
                                ShipmentStatus status,
                                LocalDate timestamp) {
        super(id);
        this.shipmentId = shipmentId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.timestamp = timestamp;
    }


}
