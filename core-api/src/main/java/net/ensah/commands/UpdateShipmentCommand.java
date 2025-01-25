package net.ensah.commands;

import java.time.LocalDate;


public class UpdateShipmentCommand<String> extends BaseCommand<String> {


    private String senderName;
    private String recipientName;
    private String recipientAddress;
    private LocalDate deliveryDate;

    public UpdateShipmentCommand(
            String id,
            String senderName,
            String recipientName,
            String recipientAddress,
            LocalDate deliveryDate)
    {
        super(id);
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.deliveryDate = deliveryDate;

    }
}
