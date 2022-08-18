package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.dto.client.response.ResponseClient;
import br.com.compass.filmes.cliente.entities.ClientEntity;
import br.com.compass.filmes.cliente.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    public ResponseClient post(RequestClient requestCLient){
        ClientEntity client = modelMapper.map(requestCLient, ClientEntity.class);
        ClientEntity saveClient = clientRepository.save(client);
        return modelMapper.map(saveClient, ResponseClient.class);
    }

    public List<ResponseClient> returnAllClients() {
        List<ClientEntity> clientEntityList = clientRepository.findAll();
        return clientEntityList.stream().map(clientEntity -> modelMapper.map(clientEntity, ResponseClient.class)).collect(Collectors.toList());
    }

    public ResponseClient returnClientById(String id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(clientEntity, ResponseClient.class);
    }
}
