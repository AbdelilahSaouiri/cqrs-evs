package net.ensah.shipementcommandservice.aggregate;

import net.ensah.coreapi.commands.CancelShipmentCommand;
import net.ensah.coreapi.commands.CreateShipmentCommand;
import net.ensah.coreapi.commands.UpdateShipmentCommand;
import net.ensah.coreapi.enums.Location;
import net.ensah.coreapi.enums.ShipmentStatus;
import net.ensah.coreapi.events.ShipmentCancelledEvent;
import net.ensah.coreapi.events.ShipmentCreatedEvent;
import net.ensah.coreapi.events.ShipmentUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.axonframework.test.aggregate.AggregateTestFixture;
import java.time.LocalDate;


class ShipmentAggregateTest {

    private AggregateTestFixture<ShipmentAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture= new AggregateTestFixture<>(ShipmentAggregate.class);
    }

     @Test
    void ShouldCreateShipmentCommand() {
         CreateShipmentCommand command= new CreateShipmentCommand(
                 "shipment1234",
                 "abdelilah",
                 "mohamed",
                 "khemisset",
                 LocalDate.now(),
                 "+212612131415",
                 Location.WAREHOUSE,
                 10
         );
         ShipmentCreatedEvent event= new ShipmentCreatedEvent(
                 "shipment1234",
                 "abdelilah",
                 "mohamed",
                 "khemisset",
                 "+212612131415",
                 ShipmentStatus.IN_PROGRESS,
                 10,
                 Location.WAREHOUSE
         );

         fixture.givenNoPriorActivity()
                 .when(command)
                 .expectSuccessfulHandlerExecution()
                 .expectEvents(event);
     }

     @Test
    void shouldNotCreateShipmentCommand() {
         CreateShipmentCommand command= new CreateShipmentCommand(
                 "shipment1234",
                 "abdelilah",
                 "mohamed",
                 "khemisset",
                 LocalDate.now(),
                 "0123456789",
                 Location.WAREHOUSE,
                 -6
         );

         fixture.givenNoPriorActivity()
                 .when(command)
                 .expectException(CreateShipmentException.class)
                 .expectExceptionMessage("Weight must be greater than zero.");

     }
     @Test
    void shouldThrowExceptionForInvalidPhoneNumber(){
         CreateShipmentCommand command= new CreateShipmentCommand(
                 "shipment1234",
                 "abdelilah",
                 "mohamed",
                 "khemisset",
                 LocalDate.now(),
                 "03456789094",
                 Location.WAREHOUSE,
                 10
         );
         fixture.givenNoPriorActivity()
                 .when(command)
                 .expectException(CreateShipmentException.class)
                 .expectExceptionMessage("Invalid phone number format.");
     }

     @Test
     void shouldUpdateShipmentCommand() {
         ShipmentCreatedEvent createdEvent = new ShipmentCreatedEvent(
                 "shipment127",
                 "abdelilah",
                 "mohamed",
                 "khemisset",
                 "+12345678901",
                 ShipmentStatus.IN_PROGRESS,
                 10,
                 Location.CURRENT_ADDRESS
         );
         UpdateShipmentCommand updateCommand = new UpdateShipmentCommand(
                 "shipment127",
                 "John Doe",
                 "Alice Doe",
                 "5678 Oak Avenue",
                 "+10987654321",
                 Location.CURRENT_ADDRESS
         );
         fixture.given(createdEvent)
                 .when(updateCommand)
                 .expectSuccessfulHandlerExecution()
                 .expectEvents(new ShipmentUpdatedEvent(
                         "shipment127",
                         "John Doe",
                         "Alice Doe",
                         "5678 Oak Avenue",
                         ShipmentStatus.IN_PROGRESS,
                         "+10987654321",
                         Location.CURRENT_ADDRESS));
     }

     @Test
    void shouldThrowExceptionIfShipmentCommandAlreadyCanceled() {

        ShipmentCancelledEvent cancelledEvent = new ShipmentCancelledEvent(
                "shipment127",
                ShipmentStatus.CANCELLED
        );

        CancelShipmentCommand command = new CancelShipmentCommand("shipment127");
        fixture
                .given(cancelledEvent)
                .when(command)
                .expectException(CancelShipmentException.class)
                .expectExceptionMessage("Shipment is already cancelled.");
    }

    @Test
    void shouldThrowExceptionIfShipmentCommandDelivered(){
        ShipmentCreatedEvent event= new ShipmentCreatedEvent(
                "shipment127",
                "abdelilah",
                "mohamed",
                "khemisset",
                "+212612131415",
                ShipmentStatus.DELIVERED,
                10,
                Location.WAREHOUSE
        );
        ShipmentCancelledEvent cancelledEvent= new ShipmentCancelledEvent(
                "shipment127",
                ShipmentStatus.DELIVERED);
        CancelShipmentCommand cancelShipmentCommand=new CancelShipmentCommand("shipment127");
        fixture.given(event)
                .when(cancelShipmentCommand)
                .expectException(CancelShipmentException.class)
                .expectExceptionMessage("Cannot cancel a delivered shipment.");
    }

    @Test
    void shouldCancelShipment(){
        ShipmentCreatedEvent event= new ShipmentCreatedEvent(
                "shipment127",
                "abdelilah",
                "mohamed",
                "khemisset",
                "+212612131415",
                ShipmentStatus.IN_PROGRESS,
                10,
                Location.WAREHOUSE
        );
        ShipmentCancelledEvent cancelledEvent= new ShipmentCancelledEvent(
                "shipment127",
                ShipmentStatus.CANCELLED);
        CancelShipmentCommand cancelShipmentCommand=new CancelShipmentCommand("shipment127");
        fixture.given(event)
                .when(cancelShipmentCommand)
                .expectEvents(cancelledEvent);

    }


}