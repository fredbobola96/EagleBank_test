package com.eaglebank.service;

import com.eaglebank.model.Client;
import com.eaglebank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService
{

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    public Client updateClient(Long clientId, Client clientDetails) {
        Client existingClient = getClient(clientId);
        if (existingClient != null) {
            existingClient.setName(clientDetails.getName());
            existingClient.setEmail(clientDetails.getEmail());
            existingClient.setPhoneNumber(clientDetails.getPhoneNumber());
            return clientRepository.save(existingClient);
        }
        return null;
    }
}
