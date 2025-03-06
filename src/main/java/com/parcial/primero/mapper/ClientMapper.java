package com.parcial.primero.mapper;

import com.parcial.primero.dto.ClientDto;
import com.parcial.primero.entity.ClientEntity;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Mapper interface for converting between {@link ClientEntity} and {@link ClientDto}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto mapToDto(ClientEntity entity);

    ClientEntity mapToEntity(ClientDto dto);

    List<ClientEntity> mapToEntity(List<ClientDto> dto);

    List<ClientDto> mapToDto(List<ClientEntity> entity);
}
