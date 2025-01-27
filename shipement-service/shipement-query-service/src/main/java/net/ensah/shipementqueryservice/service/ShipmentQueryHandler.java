package net.ensah.shipementqueryservice.service;

import net.ensah.queries.GetAllShipmentsQuery;
import net.ensah.queries.GetShipmentById;
import net.ensah.shipementqueryservice.entity.Shipment;
import net.ensah.shipementqueryservice.repository.ShipmentRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentQueryHandler {

    private final ShipmentRepository shipmentRepository;

    public ShipmentQueryHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @QueryHandler
    public List<Shipment> getAllShipment(GetAllShipmentsQuery query){
        System.out.println("****************");
        System.out.println(shipmentRepository.findAll());
        System.out.println("******************");
        return shipmentRepository.findAll();
    }

    @QueryHandler
    public Shipment getShipmentById(GetShipmentById query){
        return shipmentRepository.findById(query.getId()).orElseThrow(()-> new RuntimeException("shipment not found !"));
    }

}
