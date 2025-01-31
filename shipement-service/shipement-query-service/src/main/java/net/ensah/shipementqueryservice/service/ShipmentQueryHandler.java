package net.ensah.shipementqueryservice.service;

import net.ensah.coreapi.queries.GetAllShipmentsQuery;
import net.ensah.coreapi.queries.GetShipmentById;
import net.ensah.coreapi.queries.GetShipmentByRecipientPhoneNumber;
import net.ensah.shipementqueryservice.entity.Shipment;
import net.ensah.shipementqueryservice.repository.ShipmentRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentQueryHandler {

    private final ShipmentRepository shipmentRepository;
    private static final Logger log = LoggerFactory.getLogger(ShipmentQueryHandler.class);
    public ShipmentQueryHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @QueryHandler
    public List<Shipment> getAllShipment(GetAllShipmentsQuery query) {
        log.info("Get all shipments");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<Shipment> shipmentsPage = shipmentRepository.findAll(pageable);
        return shipmentsPage.getContent();
    }


    @QueryHandler
    public Shipment getShipmentById(GetShipmentById query){
        log.info("Get shipment by id");
        return shipmentRepository.findById(query.getId()).orElseThrow(()-> new RuntimeException("shipment not found !"));
    }

    @QueryHandler
    public Shipment getChipmentByRecipientPhoneNumber(GetShipmentByRecipientPhoneNumber query){
        log.info("Get shipment by recipient phone number {}",query.getPhoneNumber());
        return shipmentRepository.findByRecipientPhoneNumber(query.getPhoneNumber()).orElseThrow(()-> new RuntimeException("shipment not found !"));
    }

}
