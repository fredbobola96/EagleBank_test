package com.eaglebank.service;

import com.eaglebank.dto.*;

public interface ClientService
{
    ClientResponse createClient(CreateClientRequest request);
    ClientResponse getClientById(Long id);
    ClientResponse updateClient(Long id, UpdateClientRequest request);
    void deleteClient(Long id);
}
