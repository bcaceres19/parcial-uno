package com.parcial.primero.repository;

import com.parcial.primero.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing client data.
 * <p>
 * Provides CRUD operations for the "client" table.
 * </p>
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
