package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.dto.client.request.RequestClientUpdate;
import br.com.compass.filmes.cliente.dto.client.request.RequestSetStatusClientAccount;
import br.com.compass.filmes.cliente.dto.client.response.ResponseClient;
import br.com.compass.filmes.cliente.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseClient> post(@Valid @RequestBody RequestClient requestClient, UriComponentsBuilder uriComponentsBuilder){
        ResponseClient responseClient = clientService.post(requestClient);
        URI uri = uriComponentsBuilder.path("/api/client/{id}").buildAndExpand(responseClient.getId()).toUri();
        return ResponseEntity.created(uri).body(responseClient);
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
    public ResponseEntity<ResponseClient> atualiza(@PathVariable String id,
                                                   @RequestBody RequestClientUpdate requestClientUpdate) {
        ResponseClient responseDto = clientService.atualiza(id, requestClientUpdate);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/account/{id}")
    public ResponseEntity<ResponseClient> setStatusClientAccount(@PathVariable String id,
                                                                 @RequestBody @Valid RequestSetStatusClientAccount requestSetStatusClientAccount){
        ResponseClient responseClient = clientService.setStatusClientAccount(id, requestSetStatusClientAccount);
        return ResponseEntity.ok(responseClient);

    }
}
