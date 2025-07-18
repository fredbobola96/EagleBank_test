package com.eaglebank.service;

import com.eaglebank.pojo.*;

import java.util.List;

public interface ClientService {

    /**
     * Creates a client.
     *
     * @param request the createClient request POJO
     * @return the created transaction response
     */
    ClientResponse createClient(CreateClientRequest request);

    /**
     * Retrieves a specific client by ID.
     *
     * @param id the ID of the client
     * @return client response
     */
    ClientResponse getClientById(Long id);


    /**
     * Retrieves a specific client by ID.
     *
     * @return all clients
     */
    List<ClientResponse> getAllClients();


    /**
     * Updates client details by id
     * @param id the ID of the client
     * @param request the updateClient request POJO
     * @return updated client
     */
    ClientResponse updateClient(Long id, UpdateClientRequest request);

    /**
     * Deletes client details by id
     * @param id the ID of the client
     */
    void deleteClient(Long id);
}
