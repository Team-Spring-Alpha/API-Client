package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import br.com.compass.filmes.cliente.dto.user.request.RequestUserUpdate;
import br.com.compass.filmes.cliente.dto.user.request.RequestSetStatusUserAccount;
import br.com.compass.filmes.cliente.dto.user.response.ResponseUser;
import br.com.compass.filmes.cliente.service.UserService;
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
public class UserController {
    private final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUser> post(@Valid @RequestBody RequestUser requestUser, UriComponentsBuilder uriComponentsBuilder){
        ResponseUser responseUser = userService.post(requestUser);
        URI uri = uriComponentsBuilder.path("/api/client/{id}").buildAndExpand(responseUser.getId()).toUri();
        return ResponseEntity.created(uri).body(responseUser);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUser>> returnAllClients() {
        List<ResponseUser> responseUserList = userService.returnAllClients();
        return ResponseEntity.ok(responseUserList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> returnClientById(@PathVariable String id) {
        ResponseUser responseUser = userService.returnClientById(id);
        return ResponseEntity.ok(responseUser);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseUser> patch(@PathVariable String id,
                                              @RequestBody RequestUserUpdate requestUserUpdate) {
        ResponseUser responseDto = userService.patch(id, requestUserUpdate);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/account/{id}")
    public ResponseEntity<ResponseUser> setStatusClientAccount(@PathVariable String id,
                                                               @RequestBody @Valid RequestSetStatusUserAccount requestSetStatusUserAccount){
        ResponseUser responseUser = userService.setStatusClientAccount(id, requestSetStatusUserAccount);
        return ResponseEntity.ok(responseUser);

    }
}
