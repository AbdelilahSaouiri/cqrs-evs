package net.ensah.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreateShipmentException extends RuntimeException{

    public CreateShipmentException(String message){
        super(message);
    }
}
