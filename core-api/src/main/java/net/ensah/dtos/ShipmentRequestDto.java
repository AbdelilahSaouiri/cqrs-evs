package net.ensah.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import net.ensah.enums.Location;


public record ShipmentRequestDto(
        @NotBlank String senderName,
        @NotBlank String recipientName,
        @NotBlank String recipientAddress,
        @Pattern(regexp = "\\+?[0-9]{10,13}") String recipientPhone,
        @NotBlank Location location,
        @Positive int weight
) {}
