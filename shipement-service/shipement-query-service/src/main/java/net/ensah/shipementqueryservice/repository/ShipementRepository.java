package net.ensah.shipementqueryservice.repository;

import net.ensah.shipementqueryservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipementRepository  extends JpaRepository<Shipment,String> {
}
