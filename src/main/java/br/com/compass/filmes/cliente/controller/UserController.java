package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.user.request.RequestUserDTO;
import br.com.compass.filmes.cliente.dto.user.request.RequestUserUpdateDTO;
import br.com.compass.filmes.cliente.dto.user.request.RequestSetStatusUserAccountDTO;
import br.com.compass.filmes.cliente.dto.user.response.ResponseUserDTO;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUserDTO> post(@Valid @RequestBody RequestUserDTO requestUserDTO, UriComponentsBuilder uriComponentsBuilder){
        ResponseUserDTO responseUserDTO = userService.post(requestUserDTO);
        URI uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(responseUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> returnAllUsers() {
        List<ResponseUserDTO> responseUserDTOList = userService.returnAllUsers();
        return ResponseEntity.ok(responseUserDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> returnUserById(@PathVariable String id) {
        ResponseUserDTO responseUserDTO = userService.returnClientById(id);
        return ResponseEntity.ok(responseUserDTO);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> patch(@PathVariable String id,
                                                 @RequestBody RequestUserUpdateDTO requestUserUpdateDTO) {
        ResponseUserDTO responseUserDTO = userService.patch(id, requestUserUpdateDTO);
        return ResponseEntity.ok(responseUserDTO);
    }

    @PatchMapping("/account/{id}")
    public ResponseEntity<ResponseUserDTO> setStatusUserAccount(@PathVariable String id,
                                                                @RequestBody @Valid RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO){
        ResponseUserDTO responseUserDTO = userService.setStatusUserAccount(id, requestSetStatusUserAccountDTO);
        return ResponseEntity.ok(responseUserDTO);

    }
}
