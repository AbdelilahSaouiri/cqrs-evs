package net.ensah.coreapi.events

import net.ensah.coreapi.enums.Location
import net.ensah.coreapi.enums.ShipmentStatus


abstract class BaseEvent<T>(
    open val id : T
)

data class ShipmentCreatedEvent(
    override val id: String,
    val senderName: String,
    val recipientName: String,
    val recipientAddress: String,
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
): BaseEvent<String>(id)

data class ShipmentCancelledEvent(
    override val id:String,
    val status: ShipmentStatus,
): BaseEvent<String>(id)

data class TrackingArchivedEvent(
   override val id: String,
) : BaseEvent<String>(id)

