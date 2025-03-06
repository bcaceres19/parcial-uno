package com.parcial.primero.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a client.
 * <p>
 * This entity maps to the "client" table in the database.
 * </p>
 */
@Entity
@Table(name = "client")
@Data
public class ClientEntity {

    /**
     * The unique identifier of the client.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the client.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * The email address of the client.
     */
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    /**
     * The phone number of the client.
     */
    @Column(nullable = false, length = 15)
    private String phone;

    /**
     * The physical address of the client.
     */
    @Column(nullable = false, length = 200)
    private String address;
}
