package com.eaglebank.controller;

import com.eaglebank.pojo.CreateClientRequest;
import com.eaglebank.pojo.UpdateClientRequest;
import com.eaglebank.pojo.ClientResponse;
import com.eaglebank.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientUserService) {
        this.clientService = clientUserService;
    }

    /**
     * Create new user
     */
    @PostMapping
    public ResponseEntity<ClientResponse> createUser(@RequestBody CreateClientRequest request) {
        return ResponseEntity.status(201).body(clientService.createClient(request));
    }

    /**
     * Get a specific user by ID
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ClientResponse> fetchUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(clientService.getClientById(userId));
    }

    /**
     * Update user by ID
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<ClientResponse> updateUser(@PathVariable Long userId, @RequestBody UpdateClientRequest request) {
        return ResponseEntity.ok(clientService.updateClient(userId, request));
    }

    /**
     * Delete User
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        clientService.deleteClient(userId);
        return ResponseEntity.noContent().build();
    }
}
