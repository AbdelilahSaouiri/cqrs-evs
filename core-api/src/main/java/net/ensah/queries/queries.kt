package net.ensah.queries

data class GetAllShipmentsQuery (
    val page:Int,
    val size:Int
)

data class GetShipmentById(
    val id:String,
)

data class GetShipmentByRecipientPhoneNumber(
    val phoneNumber:String,
)

data class GetTrackingViewById (
    val id:String,
)