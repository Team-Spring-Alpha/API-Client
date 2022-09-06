package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;
import br.com.compass.filmes.user.dto.user.request.RequestUserUpdateDTO;
import br.com.compass.filmes.user.dto.user.request.RequestSetStatusUserAccountDTO;
import br.com.compass.filmes.user.dto.user.response.ResponseUserDTO;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.util.Md5;
import br.com.compass.filmes.user.util.ValidRequestUser;
import br.com.compass.filmes.user.util.ValidRequestCreditCard;
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

    public ResponseUserDTO post(RequestUserDTO requestUserDTO){
        validRequestUser.validRequestUser(requestUserDTO);
        validListOfRequestCreditCards(requestUserDTO);

        UserEntity user = modelMapper.map(requestUserDTO, UserEntity.class);
        user.setPassword(md5.ToMd5(user.getPassword()));

        UserEntity saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, ResponseUserDTO.class);
    }

    private void validListOfRequestCreditCards(RequestUserDTO requestUserDTO) {
        for (int i = 0; i < requestUserDTO.getCards().size(); i++) {
            validRequestCreditCard.validRequestCreditCard(requestUserDTO.getCards().get(i));
        }
    }

    public List<ResponseUserDTO> returnAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream().map(userEntity -> modelMapper.map(userEntity, ResponseUserDTO.class)).collect(Collectors.toList());
    }

    public ResponseUserDTO returnUserById(String id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(userEntity, ResponseUserDTO.class);
    }


    public ResponseUserDTO patch(String id, RequestUserUpdateDTO requestUserUpdateDTO) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(requestUserUpdateDTO, userEntity);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return modelMapper.map(savedUserEntity, ResponseUserDTO.class);
    }

    public ResponseUserDTO setStatusUserAccount(String id, RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        boolean clientIsBlocked = requestSetStatusUserAccountDTO.isBlocked();
        userEntity.setBlocked(clientIsBlocked);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, ResponseUserDTO.class);
    }
}
