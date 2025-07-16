package com.eaglebank.service.impl;

import com.eaglebank.dto.*;
import com.eaglebank.model.Client;
import com.eaglebank.repository.ClientRepository;
import com.eaglebank.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService
{

    private final ClientRepository repo;

    public ClientServiceImpl(ClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public ClientResponse createClient(CreateClientRequest request) {
        Client client = new Client();
        client.setName(request.name);
        client.setEmail(request.email);
        repo.save(client);
        return map(client);
    }

    @Override
    public ClientResponse getClientById(Long id) {
        return repo.findById(id).map(this::map).orElseThrow();
    }

    @Override
    public ClientResponse updateClient(Long id, UpdateClientRequest request) {
        Client client = repo.findById(id).orElseThrow();
        client.setName(request.name);
        client.setEmail(request.email);
        return map(repo.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        repo.deleteById(id);
    }

    private ClientResponse map(Client client) {
        ClientResponse res = new ClientResponse();
        res.id = client.getId();
        res.name = client.getName();
        res.email = client.getEmail();
        return res;
    }

}
