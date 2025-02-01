package net.ensah.coreapi.dtos

import net.ensah.coreapi.enums.Location
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import net.ensah.coreapi.enums.ShipmentStatus

import java.time.LocalDate

data class ShipmentRequestDTO(
    var senderName: String = "",
    var recipientName: String = "",
    var recipientAddress: String = "",
    @field:Pattern(regexp = "\\+?[0-9]{10,13}") var recipientPhone: String = "",
    var location: Location = Location.WAREHOUSE,
    @field:Positive var weight: Int = 0
);

data class TrackingRequestDTO(
    var id: String = "",
    var trackingStatus: ShipmentStatus? = null,
    var location: Location? = null,
    var timestamp: LocalDate? = null
)
