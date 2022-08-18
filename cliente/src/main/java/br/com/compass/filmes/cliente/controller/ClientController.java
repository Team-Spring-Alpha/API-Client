package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.client.response.ResponseClient;
import br.com.compass.filmes.cliente.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ResponseClient>> returnAllClients() {
        List<ResponseClient> responseClientList = clientService.returnAllClients();
        return ResponseEntity.ok(responseClientList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClient> returnClientById(@PathVariable String id) {
        ResponseClient responseClient = clientService.returnClientById(id);
        return ResponseEntity.ok(responseClient);
    }
}
