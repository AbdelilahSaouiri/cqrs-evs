package net.ensah.events;

import java.time.LocalDateTime;

public class ShipmentCancelledEvent<String> extends BaseEvent<String> {

     private String shipmentId;
     private LocalDateTime timestamp;

     public ShipmentCancelledEvent(String id, String shipmentId, LocalDateTime timestamp) {
        super(id);
        this.shipmentId = shipmentId;
        this.timestamp = timestamp;
    }
}
