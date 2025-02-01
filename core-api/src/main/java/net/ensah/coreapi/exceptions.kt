package net.ensah.coreapi.exceptions


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CancelShipmentException(message: String) : RuntimeException(message)


@ResponseStatus(HttpStatus.BAD_REQUEST)
class CreateShipmentException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UpdateShipmentException(message: String) : RuntimeException(message)


