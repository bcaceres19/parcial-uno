package com.parcial.primero.service.impl;

import com.parcial.primero.dto.ClientDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.mapper.ClientMapper;
import com.parcial.primero.repository.ClientRepository;
import com.parcial.primero.service.IClientService;
import com.parcial.primero.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing clients.
 * <p>
 * This class handles client creation, updating, deletion, and retrieval.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public GeneralMessageDto createClient(ClientDto clientDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            message.setMessage("Client successfully created.");
            message.setObject(clientMapper.mapToDto(clientRepository.save(clientMapper.mapToEntity(clientDto))));
        } catch (Exception ex) {
            log.error("Error while saving client: {}", ex.getMessage());
            message.setMessage("Error saving the client.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto updateClient(ClientDto clientDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            ClientDto existingClient = clientMapper.mapToDto(clientRepository.findById(clientDto.getId()).orElse(null));
            if (existingClient != null) {
                message.setMessage("Client successfully updated.");
                ClientDto updatedClient = Utils.compareOldNewObject(existingClient, clientDto);
                message.setObject(clientMapper.mapToDto(clientRepository.save(clientMapper.mapToEntity(updatedClient))));
            } else {
                message.setMessage("Client not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error updating client: {}", ex.getMessage());
            message.setMessage("Error updating the client.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto deleteClient(Long clientId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            ClientDto client = clientMapper.mapToDto(clientRepository.findById(clientId).orElse(null));
            if (client != null) {
                message.setMessage("Client successfully deleted.");
                clientRepository.delete(clientMapper.mapToEntity(client));
            } else {
                message.setMessage("Client not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error deleting client: {}", ex.getMessage());
            message.setMessage("Error deleting the client.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto getClient(Long clientId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            ClientDto client = clientMapper.mapToDto(clientRepository.findById(clientId).orElse(null));
            message.setMessage("Client retrieved successfully.");
            message.setObject(client);
        } catch (Exception ex) {
            log.error("Error retrieving client: {}", ex.getMessage());
            message.setMessage("Error retrieving the client.");
            message.setError(true);
        }
        return message;
    }
}
