package net.ensah.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;

import java.time.Instant;
import java.time.LocalDate;

public record TrackingRequestDto(
        @NotEmpty(message = "ID cannot be empty") String id,
        @NotNull(message = "Tracking status is required") ShipmentStatus trackingStatus,
        @NotNull(message = "Location is required") Location location,
        @NotNull(message = "Timestamp is required") LocalDate timestamp
) { }

