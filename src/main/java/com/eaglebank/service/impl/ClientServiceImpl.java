package com.eaglebank.service.impl;

import com.eaglebank.dto.ClientResponse;
import com.eaglebank.dto.CreateClientRequest;
import com.eaglebank.dto.UpdateClientRequest;
import com.eaglebank.model.Client;
import com.eaglebank.repository.ClientRepository;
import com.eaglebank.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepo;

    public ClientServiceImpl(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public ClientResponse createClient(CreateClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());

        Client saved = clientRepo.save(client);
        return map(saved);
    }

    @Override
    public ClientResponse getClientById(Long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return map(client);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepo.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse updateClient(Long id, UpdateClientRequest request) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        client.setName(request.getName());
        client.setEmail(request.getEmail());

        Client updated = clientRepo.save(client);
        return map(updated);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepo.existsById(id)) {
            throw new IllegalArgumentException("Client not found");
        }
        clientRepo.deleteById(id);
    }

    private ClientResponse map(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail()
        );
    }
}
