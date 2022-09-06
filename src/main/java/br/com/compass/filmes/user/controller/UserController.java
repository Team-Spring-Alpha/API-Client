package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.user.request.RequestUser;
import br.com.compass.filmes.user.dto.user.request.RequestUserUpdate;
import br.com.compass.filmes.user.dto.user.request.RequestSetStatusUserAccount;
import br.com.compass.filmes.user.dto.user.response.ResponseUser;
import br.com.compass.filmes.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUser> post(@Valid @RequestBody RequestUser requestUser, UriComponentsBuilder uriComponentsBuilder){
        ResponseUser responseUser = userService.post(requestUser);
        URI uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(responseUser.getId()).toUri();
        return ResponseEntity.created(uri).body(responseUser);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUser>> returnAllUsers() {
        List<ResponseUser> responseUserList = userService.returnAllUsers();
        return ResponseEntity.ok(responseUserList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> returnUserById(@PathVariable String id) {
        ResponseUser responseUser = userService.returnUserById(id);
        return ResponseEntity.ok(responseUser);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseUser> patch(@PathVariable String id,
                                              @RequestBody RequestUserUpdate requestUserUpdate) {
        ResponseUser responseUser = userService.patch(id, requestUserUpdate);
        return ResponseEntity.ok(responseUser);
    }

    @PatchMapping("/account/{id}")
    public ResponseEntity<ResponseUser> setStatusUserAccount(@PathVariable String id,
                                                               @RequestBody @Valid RequestSetStatusUserAccount requestSetStatusUserAccount){
        ResponseUser responseUser = userService.setStatusUserAccount(id, requestSetStatusUserAccount);
        return ResponseEntity.ok(responseUser);

    }
}
