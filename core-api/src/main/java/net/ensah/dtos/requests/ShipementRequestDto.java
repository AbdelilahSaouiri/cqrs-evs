package net.ensah.dtos.requests;

import java.time.LocalDate;


public record ShipementRequestDto(
        String senderName,
        String recipientName,
        String recipientAddress,
        String  recipientPhone,
        LocalDate deliveryDate,
        int weight) {
}
