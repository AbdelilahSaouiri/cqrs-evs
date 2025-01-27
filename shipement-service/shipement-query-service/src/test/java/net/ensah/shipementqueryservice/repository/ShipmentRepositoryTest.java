package net.ensah.shipementqueryservice.repository;

import net.ensah.enums.Location;
import net.ensah.enums.ShipmentStatus;
import net.ensah.shipementqueryservice.entity.Shipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.UUID;

@DataJpaTest
class ShipmentRepositoryTest {

    @Autowired
    private  ShipmentRepository shipmentRepository;

    @BeforeEach
    void setUp() {
        Shipment sh1=new Shipment(UUID.randomUUID().toString(),"abdelilah","mohamed","khemisset","0631489855",13, Location.WAREHOUSE, LocalDate.now(), ShipmentStatus.IN_PROGRESS);
        Shipment sh2=new Shipment(UUID.randomUUID().toString(),"abdelilah","mohamed","khemisset","0631489858",13, Location.WAREHOUSE, LocalDate.now(), ShipmentStatus.IN_PROGRESS);
    }


    @Test
    void findById() {
    }

    @Test
    void findByRecipientPhoneNumber() {
    }
}