package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.dto.client.request.RequestClientUpdate;
import br.com.compass.filmes.cliente.dto.client.response.ResponseClient;
import br.com.compass.filmes.cliente.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseClient> post(@Valid @RequestBody RequestClient requestClient){
        ResponseClient responseClient = clientService.post(requestClient);
        return ResponseEntity.ok(responseClient);
    }

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

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseClient> patch(@PathVariable String id,
                                                   @RequestBody RequestClientUpdate requestClientUpdate) {
        ResponseClient responseDto = clientService.patch(id, requestClientUpdate);
        return ResponseEntity.ok(responseDto);
    }
}
