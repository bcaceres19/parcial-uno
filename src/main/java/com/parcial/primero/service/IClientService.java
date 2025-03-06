package com.parcial.primero.service;

import com.parcial.primero.dto.ClientDto;
import com.parcial.primero.dto.GeneralMessageDto;

/**
 * Service interface for managing clients.
 * <p>
 * Provides methods for creating, updating, deleting, and retrieving clients.
 * </p>
 */
public interface IClientService {

    /**
     * Creates a new client.
     *
     * @param clientDto The client DTO containing the client details.
     * @return A response message with status information.
     */
    GeneralMessageDto createClient(ClientDto clientDto);

    /**
     * Updates an existing client.
     *
     * @param clientDto The client DTO with updated details.
     * @return A response message with status information.
     */
    GeneralMessageDto updateClient(ClientDto clientDto);

    /**
     * Deletes a client by its ID.
     *
     * @param clientId The ID of the client to be deleted.
     * @return A response message with status information.
     */
    GeneralMessageDto deleteClient(Long clientId);

    /**
     * Retrieves a client by its ID.
     *
     * @param clientId The ID of the client to retrieve.
     * @return A response message containing the client details or an error message.
     */
    GeneralMessageDto getClient(Long clientId);
}
