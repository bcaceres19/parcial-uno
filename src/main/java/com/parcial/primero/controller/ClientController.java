package com.parcial.primero.controller;

import com.parcial.primero.dto.ClientDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that manages operations related to clients.
 * <p>
 * This controller provides endpoints to create, update, delete, and retrieve clients.
 * </p>
 *
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    /**
     * Creates a new client.
     *
     * @param clientDto The client data transfer object containing the client's details.
     * @return A response entity containing a general message with status information.
     */
    @PostMapping
    public ResponseEntity<GeneralMessageDto> createClient(@RequestBody ClientDto clientDto) {
        GeneralMessageDto response = clientService.createClient(clientDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing client.
     *
     * @param clientDto The client data transfer object containing updated details.
     * @return A response entity containing a general message with status information.
     */
    @PutMapping
    public ResponseEntity<GeneralMessageDto> updateClient(@RequestBody ClientDto clientDto) {
        GeneralMessageDto response = clientService.updateClient(clientDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Deletes a client by its ID.
     *
     * @param clientId The ID of the client to be deleted.
     * @return A response entity containing a general message with status information.
     */
    @DeleteMapping("/{clientId}")
    public ResponseEntity<GeneralMessageDto> deleteClient(@PathVariable Long clientId) {
        GeneralMessageDto response = clientService.deleteClient(clientId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Retrieves a client by its ID.
     *
     * @param clientId The ID of the client to retrieve.
     * @return A response entity containing the client details or an error message.
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<GeneralMessageDto> getClient(@PathVariable Long clientId) {
        GeneralMessageDto response = clientService.getClient(clientId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }
}
