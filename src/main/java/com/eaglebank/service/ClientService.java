package com.eaglebank.service;

import com.eaglebank.pojo.*;

import java.util.List;

public interface ClientService {
    ClientResponse createClient(CreateClientRequest request);
    ClientResponse getClientById(Long id);

    List<ClientResponse> getAllClients();

    ClientResponse updateClient(Long id, UpdateClientRequest request);
    void deleteClient(Long id);
}
