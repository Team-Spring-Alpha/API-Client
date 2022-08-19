package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.dto.client.request.RequestClientUpdate;
import br.com.compass.filmes.cliente.dto.client.request.RequestSetStatusClientAccount;
import br.com.compass.filmes.cliente.dto.client.response.ResponseClient;
import br.com.compass.filmes.cliente.entities.ClientEntity;
import br.com.compass.filmes.cliente.repository.ClientRepository;
import br.com.compass.filmes.cliente.util.Md5;
import br.com.compass.filmes.cliente.util.ValidRequestClient;
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
public class ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    private final Md5 md5;

    private final ValidRequestClient validRequestClient;

    private final ValidRequestCreditCard validRequestCreditCard;

    public ResponseClient post(RequestClient requestCLient){
        validRequestClient.validRequestClient(requestCLient);
        validListOfRequestCreditCards(requestCLient);

        ClientEntity client = modelMapper.map(requestCLient, ClientEntity.class);
        client.setClientPassword(md5.ToMd5(client.getClientPassword()));

        ClientEntity saveClient = clientRepository.save(client);
        return modelMapper.map(saveClient, ResponseClient.class);
    }

    private void validListOfRequestCreditCards(RequestClient requestCLient) {
        for (int i = 0; i < requestCLient.getCreditCards().size(); i++) {
            validRequestCreditCard.validRequestCreditCard(requestCLient.getCreditCards().get(i));
        }
    }

    public List<ResponseClient> returnAllClients() {
        List<ClientEntity> clientEntityList = clientRepository.findAll();
        return clientEntityList.stream().map(clientEntity -> modelMapper.map(clientEntity, ResponseClient.class)).collect(Collectors.toList());
    }

    public ResponseClient returnClientById(String id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(clientEntity, ResponseClient.class);
    }


    public ResponseClient atualiza(String id, RequestClientUpdate requestClientUpdate) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(requestClientUpdate, clientEntity);

    public ResponseClient setStatusClientAccount(String id, RequestSetStatusClientAccount requestSetStatusClientAccount){
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        boolean clientIsBlocked = requestSetStatusClientAccount.isClientIsBlocked();
        clientEntity.setClientIsBlocked(clientIsBlocked);
        clientRepository.save(clientEntity);
        return modelMapper.map(clientEntity, ResponseClient.class);
    }
}
