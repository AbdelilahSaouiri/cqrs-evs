package net.ensah.shipementqueryservice.service;

import net.ensah.queries.GetAllShipmentsQuery;
import net.ensah.queries.GetShipmentById;
import net.ensah.queries.GetShipmentByRecipientPhoneNumber;
import net.ensah.shipementqueryservice.entity.Shipment;
import net.ensah.shipementqueryservice.repository.ShipmentRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentQueryHandler {

    private final ShipmentRepository shipmentRepository;

    public ShipmentQueryHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @QueryHandler
    public List<Shipment> getAllShipment(GetAllShipmentsQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<Shipment> shipmentsPage = shipmentRepository.findAll(pageable);
        return shipmentsPage.getContent();
    }


    @QueryHandler
    public Shipment getShipmentById(GetShipmentById query){
        return shipmentRepository.findById(query.getId()).orElseThrow(()-> new RuntimeException("shipment not found !"));
    }

    @QueryHandler
    public Shipment getChipmentByRecipientPhoneNumber(GetShipmentByRecipientPhoneNumber query){
        return shipmentRepository.findByRecipientPhoneNumber(query.getPhoneNumber()).orElseThrow(()-> new RuntimeException("shipment not found !"));
    }

}
