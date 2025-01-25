package net.ensah.commands;


import java.time.LocalDate;


public class CreateShipementCommand extends BaseCommand<String> {

    private final String senderName;
    private final String recipientName;
    private final String recipientAddress;
    private final LocalDate deliveryDate;
    private final String recipientPhone;
    private final int weight;


    public CreateShipementCommand(
            String id,
            String senderName,
            String recipientName,
            String recipientAddress,
            LocalDate deliveryDate,
            String recipientPhone, int weight)
    {
        super(id);
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.deliveryDate = deliveryDate;
        this.recipientPhone = recipientPhone;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
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

    public String getRecipientPhone() {
        return recipientPhone;
    }

}
