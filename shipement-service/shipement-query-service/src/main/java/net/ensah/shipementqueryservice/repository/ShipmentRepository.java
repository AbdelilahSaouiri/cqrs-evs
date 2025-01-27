package net.ensah.shipementqueryservice.repository;

import net.ensah.shipementqueryservice.entity.Shipment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository  extends JpaRepository<Shipment,String> {


     @NotNull Optional<Shipment> findById(String id);

     Optional<Shipment>  findByRecipientPhoneNumber(String phoneNumber);
}
