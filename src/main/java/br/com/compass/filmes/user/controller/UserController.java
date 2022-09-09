package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.user.request.RequestSetStatusUserAccountDTO;
import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;
import br.com.compass.filmes.user.dto.user.request.RequestUserUpdateDTO;
import br.com.compass.filmes.user.dto.user.response.ResponseUserDTO;
import br.com.compass.filmes.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @ApiOperation(value = "create a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseUserDTO> post(@Valid @RequestBody RequestUserDTO requestUserDTO, UriComponentsBuilder uriComponentsBuilder){
        ResponseUserDTO responseUserDTO = userService.post(requestUserDTO);
        URI uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(responseUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseUserDTO);
    }

    @ApiOperation(value = "return all users created")
    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> returnAllUsers() {
        List<ResponseUserDTO> responseUserDTOList = userService.returnAllUsers();
        return ResponseEntity.ok(responseUserDTOList);
    }

    @ApiOperation(value = "return user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "Ok")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> returnUserById(@PathVariable String id) {
        ResponseUserDTO responseUserDTO = userService.returnClientById(id);
        return ResponseEntity.ok(responseUserDTO);
    }

    @ApiOperation(value = "update user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "Ok")
    })
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> patch(@PathVariable String id,
                                                 @RequestBody RequestUserUpdateDTO requestUserUpdateDTO) {
        ResponseUserDTO responseUserDTO = userService.patch(id, requestUserUpdateDTO);
        return ResponseEntity.ok(responseUserDTO);
    }

    @ApiOperation(value = "update user status account by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "Ok")
    })
    @PatchMapping("/account/{id}")
    public ResponseEntity<ResponseUserDTO> setStatusUserAccount(@PathVariable String id,
                                                                @RequestBody @Valid RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO){
        ResponseUserDTO responseUserDTO = userService.setStatusUserAccount(id, requestSetStatusUserAccountDTO);
        return ResponseEntity.ok(responseUserDTO);

    }
}
