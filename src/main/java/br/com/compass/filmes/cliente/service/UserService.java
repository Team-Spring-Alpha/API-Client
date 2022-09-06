package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import br.com.compass.filmes.cliente.dto.user.request.RequestUserUpdate;
import br.com.compass.filmes.cliente.dto.user.request.RequestSetStatusUserAccount;
import br.com.compass.filmes.cliente.dto.user.response.ResponseUser;
import br.com.compass.filmes.cliente.entities.UserEntity;
import br.com.compass.filmes.cliente.repository.UserRepository;
import br.com.compass.filmes.cliente.util.Md5;
import br.com.compass.filmes.cliente.util.ValidRequestUser;
import br.com.compass.filmes.cliente.util.ValidRequestCreditCard;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final Md5 md5;

    private final ValidRequestUser validRequestUser;

    private final ValidRequestCreditCard validRequestCreditCard;

    public ResponseUser post(RequestUser requestCLient){
        validRequestUser.validRequestClient(requestCLient);
        validListOfRequestCreditCards(requestCLient);

        UserEntity client = modelMapper.map(requestCLient, UserEntity.class);
        client.setPassword(md5.ToMd5(client.getPassword()));

        UserEntity saveClient = userRepository.save(client);
        return modelMapper.map(saveClient, ResponseUser.class);
    }

    private void validListOfRequestCreditCards(RequestUser requestCLient) {
        for (int i = 0; i < requestCLient.getCards().size(); i++) {
            validRequestCreditCard.validRequestCreditCard(requestCLient.getCards().get(i));
        }
    }

    public List<ResponseUser> returnAllClients() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream().map(clientEntity -> modelMapper.map(clientEntity, ResponseUser.class)).collect(Collectors.toList());
    }

    public ResponseUser returnClientById(String id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(userEntity, ResponseUser.class);
    }


    public ResponseUser patch(String id, RequestUserUpdate requestUserUpdate) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(requestUserUpdate, userEntity);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return modelMapper.map(savedUserEntity, ResponseUser.class);
    }

    public ResponseUser setStatusClientAccount(String id, RequestSetStatusUserAccount requestSetStatusUserAccount){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        boolean clientIsBlocked = requestSetStatusUserAccount.isClientIsBlocked();
        userEntity.setClientIsBlocked(clientIsBlocked);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, ResponseUser.class);
    }
}
