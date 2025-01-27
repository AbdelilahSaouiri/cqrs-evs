package net.ensah.shipementqueryservice.repository;

import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;
import net.ensah.shipementqueryservice.entity.Shipment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.Optional;


@DataJpaTest
class ShipmentRepositoryTest {

    @Autowired
    private  ShipmentRepository shipmentRepository;

    @BeforeEach
    void setUp() {
        Shipment sh1=new Shipment(
                "shipment127",
                "abdelilah",
                "mohamed",
                "khemisset",
                "0612345678",
                13,
                Location.WAREHOUSE,
                LocalDate.now(),
                ShipmentStatus.IN_PROGRESS);
        Shipment sh2=new Shipment(
                "shipment128",
                "abdelilah",
                "mohamed",
                "khemisset",
                "+212631489875",
                13,
                Location.WAREHOUSE,
                LocalDate.now(),
                ShipmentStatus.IN_PROGRESS);
        shipmentRepository.save(sh1);
        shipmentRepository.save(sh2);
    }


    @Test
    void ShouldFindShipmentById() {
      String shipmentId="shipment128";
        Shipment expected=new Shipment(
                "shipment128",
                "abdelilah",
                "mohamed",
                "khemisset",
                "+212631489875",
                13,
                Location.WAREHOUSE,
                LocalDate.now(),
                ShipmentStatus.IN_PROGRESS);
        Optional<Shipment> result = shipmentRepository.findById(shipmentId);
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get())
                .usingRecursiveComparison()
                .ignoringFields("createAt")
                .isEqualTo(expected);

    }

    @Test
    void  ShouldNotFindShipmentById(){
        String shipmentId="shipment124";
        Shipment expected=new Shipment(
                "shipment128",
                "abdelilah",
                "mohamed",
                "khemisset",
                "0631489875",
                13,
                Location.WAREHOUSE,
                LocalDate.now(),
                ShipmentStatus.IN_PROGRESS);
        Optional<Shipment> result = shipmentRepository.findById(shipmentId);
        Assertions.assertThat(result).isEmpty();

    }

    @Test
    void ShouldFindShipmentByRecipientPhoneNumber() {
        String recipientPhoneNumber="+212631489875";
        Shipment expected=new Shipment(
                "shipment128",
                "abdelilah",
                "mohamed",
                "khemisset",
                "+212631489875",
                13,
                Location.WAREHOUSE,
                LocalDate.now(),
                ShipmentStatus.IN_PROGRESS);
        Optional<Shipment> result = shipmentRepository.findByRecipientPhoneNumber(recipientPhoneNumber);
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get())
                .usingRecursiveComparison()
                .ignoringFields("createAt")
                .isEqualTo(expected);
    }

    @Test
    public void ShouldNotFindShipmentByRecipientPhoneNumber(){
        String recipientPhoneNumber="+212631489800";
        Shipment expected=new Shipment(
                "shipment128",
                "abdelilah",
                "mohamed",
                "khemisset",
                "+212631489875",
                13,
                Location.WAREHOUSE,
                LocalDate.now(),
                ShipmentStatus.IN_PROGRESS);
        Optional<Shipment> result = shipmentRepository.findByRecipientPhoneNumber(recipientPhoneNumber);
        Assertions.assertThat(result).isEmpty();
    }
}