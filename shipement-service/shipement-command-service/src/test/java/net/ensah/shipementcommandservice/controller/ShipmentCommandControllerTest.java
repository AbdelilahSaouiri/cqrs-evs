package net.ensah.shipementcommandservice.controller;

import net.ensah.commands.CancelShipmentCommand;
import net.ensah.commands.CreateShipmentCommand;
import net.ensah.commands.UpdateShipmentCommand;
import net.ensah.dtos.ShipmentRequestDto;
import net.ensah.enums.Location;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ShipmentCommandControllerTest {

    @Mock
    private CommandGateway commandGateway;

    @InjectMocks
    private ShipmentCommandController shipmentCommandController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shipmentCommandController).build();
    }

    @Test
    void shouldCreateShipment() throws Exception {

        ShipmentRequestDto requestDto = new ShipmentRequestDto(
                "SenderName", "RecipientName", "Address", "123456789", Location.WAREHOUSE, 10);
        String shipmentId = "some-shipment-id";
        when(commandGateway.send(any(CreateShipmentCommand.class)))
                .thenReturn(CompletableFuture.completedFuture(shipmentId));
        mockMvc.perform(post("/api/v1/command/ship")
                        .contentType("application/json")
                        .content("{ \"senderName\": \"SenderName\", \"recipientName\": \"RecipientName\", \"recipientAddress\": \"Address\", \"recipientPhone\": \"123456789\", \"location\": \"WAREHOUSE\", \"weight\": 10 }"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    verify(commandGateway, times(1)).send(any(CreateShipmentCommand.class));
                });
    }

    @Test
    void shouldUpdateShipment() throws Exception {
        ShipmentRequestDto requestDto = new ShipmentRequestDto(
                "SenderName", "RecipientName", "Address", "123456789", Location.WAREHOUSE, 10);
        String shipmentId = "some-shipment-id";
        when(commandGateway.send(any(UpdateShipmentCommand.class)))
                .thenReturn(CompletableFuture.completedFuture(shipmentId));
        mockMvc.perform(put("/api/v1/command/ship/{id}",shipmentId)
                .contentType("application/json")
                .content("{ \"senderName\": \"SenderName\", \"recipientName\": \"RecipientName\", \"recipientAddress\": \"Address\", \"recipientPhone\": \"123456789\", \"location\": \"WAREHOUSE\", \"weight\": 10.0 }"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    verify(commandGateway, times(1)).send(any(UpdateShipmentCommand.class));
                });
    }

    @Test
    void ShouldCancelShipment()throws  Exception {
        String shipmentId = "some-shipment-id";
        when(commandGateway.send(any(UpdateShipmentCommand.class)))
                .thenReturn(CompletableFuture.completedFuture(shipmentId));

        mockMvc.perform(patch("/api/v1/command/ship/{id}", shipmentId))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    verify(commandGateway, times(1)).send(any(CancelShipmentCommand.class));
                });
    }

}