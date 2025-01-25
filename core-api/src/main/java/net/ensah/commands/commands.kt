package net.ensah.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class BaseCommand<T>(
    @TargetAggregateIdentifier
    open val id : T
)
data class CreateShipmentCommand(
    override val id:String,
    val senderName:String,
    val recipientAddress : String,
    val deliveryDate:String,
    val recipientPhoneNumber: String,
    val weight:Int
):BaseCommand<String>(id)

data class UpdateShipmentCommand(
    override val id:String,
    val senderName:String,
    val recipientAddress : String,
    val deliveryDate:String

):BaseCommand<String>(id)

data class CancelShipmentCommand(
    override val id:String,
):BaseCommand<String>(id)

/**
 * private String shipmentId;
 *      private String trackingId;
 *      private TrackingRecordStatus status;
 *      private String location;
 *      private LocalDate timestamp ;
 *
 */
data class CreateTrackingRecordCommand(
    override val id:String,
    val shipmentId:String,
    val trackingRecordStatus : String,
    val location:String,
    val timestamp: String,
):BaseCommand<String>(id)

data class UpdateTrackingStatusCommand(
    override val id:String,
    val trackingStatus:String,
    val location : String,
    val timestamp: String,
):BaseCommand<String>(id)