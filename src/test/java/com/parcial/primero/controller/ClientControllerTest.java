package com.parcial.primero.controller;

import com.parcial.primero.dto.ClientDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.service.IClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private IClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testCreateClientSuccess() {
        ClientDto clientDto = new ClientDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Client created successfully");

        when(clientService.createClient(any(ClientDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = clientController.createClient(clientDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client created successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testUpdateClientSuccess() {
        ClientDto clientDto = new ClientDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Client updated successfully");

        when(clientService.updateClient(any(ClientDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = clientController.updateClient(clientDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client updated successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testDeleteClientSuccess() {
        Long clientId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Client deleted successfully");

        when(clientService.deleteClient(clientId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = clientController.deleteClient(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client deleted successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testGetClientSuccess() {
        Long clientId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Client retrieved successfully");

        when(clientService.getClient(clientId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = clientController.getClient(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client retrieved successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }
}
