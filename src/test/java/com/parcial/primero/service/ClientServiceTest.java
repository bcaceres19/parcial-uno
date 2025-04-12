package com.parcial.primero.service;

import com.parcial.primero.dto.ClientDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.entity.ClientEntity;
import com.parcial.primero.mapper.ClientMapper;
import com.parcial.primero.repository.ClientRepository;
import com.parcial.primero.service.impl.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testCreateClientSuccess() {
        ClientDto inputDto = new ClientDto();
        inputDto.setId(1L);
        ClientEntity dummyEntity = new ClientEntity();

        when(clientMapper.mapToEntity(inputDto)).thenReturn(dummyEntity);
        when(clientRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(clientMapper.mapToDto(dummyEntity)).thenReturn(inputDto);

        GeneralMessageDto result = clientService.createClient(inputDto);

        assertFalse(result.isError());
        assertEquals("Client successfully created.", result.getMessage());
        assertEquals(inputDto, result.getObject());
    }

    @Test
    public void testUpdateClientSuccess() {
        ClientDto inputDto = new ClientDto();
        inputDto.setId(1L);
        ClientEntity dummyEntity = new ClientEntity();
        ClientDto existingDto = new ClientDto();
        existingDto.setId(1L);

        when(clientRepository.findById(inputDto.getId())).thenReturn(Optional.of(dummyEntity));
        when(clientMapper.mapToDto(dummyEntity)).thenReturn(existingDto);
        // Suponemos que compareOldNewObject devuelve el mismo objeto para efectos de test
        ClientDto comparedDto = inputDto;
        when(clientMapper.mapToEntity(comparedDto)).thenReturn(dummyEntity);
        when(clientRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(clientMapper.mapToDto(dummyEntity)).thenReturn(comparedDto);

        GeneralMessageDto result = clientService.updateClient(inputDto);

        assertFalse(result.isError());
        assertEquals("Client successfully updated.", result.getMessage());
        assertEquals(comparedDto, result.getObject());
    }

    @Test
    public void testUpdateClientNotFound() {
        ClientDto inputDto = new ClientDto();
        inputDto.setId(1L);
        when(clientRepository.findById(inputDto.getId())).thenReturn(Optional.empty());

        GeneralMessageDto result = clientService.updateClient(inputDto);

        assertTrue(result.isError());
        assertEquals("Client not found.", result.getMessage());
        assertNull(result.getObject());
    }

    @Test
    public void testDeleteClientSuccess() {
        Long clientId = 1L;
        ClientEntity dummyEntity = new ClientEntity();
        ClientDto existingDto = new ClientDto();
        existingDto.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(dummyEntity));
        when(clientMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = clientService.deleteClient(clientId);

        assertFalse(result.isError());
        assertEquals("Client successfully deleted.", result.getMessage());
    }

    @Test
    public void testDeleteClientNotFound() {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        GeneralMessageDto result = clientService.deleteClient(clientId);

        assertTrue(result.isError());
        assertEquals("Client not found.", result.getMessage());
    }

    @Test
    public void testGetClientSuccess() {
        Long clientId = 1L;
        ClientEntity dummyEntity = new ClientEntity();
        ClientDto existingDto = new ClientDto();
        existingDto.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(dummyEntity));
        when(clientMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = clientService.getClient(clientId);

        assertFalse(result.isError());
        assertEquals("Client retrieved successfully.", result.getMessage());
        assertEquals(existingDto, result.getObject());
    }
}
