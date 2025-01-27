package net.ensah.events

import net.ensah.enums.Location
import net.ensah.enums.ShipmentStatus
import java.time.LocalDate


abstract class BaseEvent<T>(
    open val id : T
)

data class ShipmentCreatedEvent(
    override val id: String,
    val senderName: String,
    val recipientName: String,
    val recipientAddress: String,
    val deliveryDate: LocalDate,
    val recipientPhoneNumber: String,
    val status: ShipmentStatus,
    val weight: Int,
    val location: Location?
) : BaseEvent<String>(id)


data class ShipmentUpdatedEvent(
     override val id:String,
    val senderName:String,
    val recipientName: String,
    val recipientAddress : String,
    val shipmentStatus: ShipmentStatus,
    val recipientPhoneNumber: String,
    val location: Location
):BaseEvent<String>(id)

data class ShipmentCancelledEvent(
    override val id:String,
     val status: ShipmentStatus,
):BaseEvent<String>(id)

data class TrackingArchivedEvent(
   override val id: String,
    val archivedAt: LocalDate
) : BaseEvent<String>(id)

