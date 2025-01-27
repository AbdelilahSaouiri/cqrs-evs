package net.ensah.shipementcommandservice.aggregate;

import net.ensah.commands.CreateShipmentCommand;
import net.ensah.commands.UpdateShipmentCommand;
import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;
import net.ensah.events.ShipmentCreatedEvent;
import net.ensah.events.ShipmentUpdatedEvent;
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
                 .expectException(IllegalArgumentException.class)
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
                 .expectException(IllegalArgumentException.class)
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
}