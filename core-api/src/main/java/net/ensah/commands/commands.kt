package net.ensah.commands

import net.ensah.enums.Location
import net.ensah.enums.ShipmentStatus
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Instant
import java.time.LocalDate

abstract class BaseCommand<T>(
    @TargetAggregateIdentifier
    open val id : T
)
data class CreateShipmentCommand(
    override val id: String,
    val senderName: String,
    val recipientName: String,
    val recipientAddress: String,
    val deliveryDate: LocalDate,
    val recipientPhoneNumber: String,
    val location:Location,
    val weight: Int
):BaseCommand<String>(id)

data class UpdateShipmentCommand(
    override val id:String,
    val senderName:String,
    val recipientName: String,
    val recipientAddress : String,
    val recipientPhoneNumber: String,
    val location:Location

):BaseCommand<String>(id)

data class CancelShipmentCommand(
    override val id:String,
):BaseCommand<String>(id)

data class ArchiveTrackingCommand(
    override val id: String,
    val archivedAt: LocalDate
) : BaseCommand<String>(id)


//data class UpdateTrackingStatusCommand(
//    override val id: String,
//    val trackingStatus: ShipmentStatus,
//    val location: Location,
//    val timestamp: Instant
//) : BaseCommand<String>(id)