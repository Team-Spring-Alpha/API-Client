package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.client.request.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.cliente.dto.client.response.ResponseMoviePayment;
import br.com.compass.filmes.cliente.entities.ClientEntity;
import br.com.compass.filmes.cliente.entities.ClientPaymentEntity;
import br.com.compass.filmes.cliente.repository.ClientPaymentRepository;
import br.com.compass.filmes.cliente.util.Md5;
import br.com.compass.filmes.cliente.util.ValidRequestCreditCard;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoviePaymentService {
    private final ModelMapper modelMapper;
    private final Md5 md5;
    private final ClientService clientService;
    private final ValidRequestCreditCard validRequestCreditCard;
    private final ClientPaymentRepository paymentRepository;

    public ResponseMoviePayment post(RequestMoviePayment requestMoviePayment){
        ClientPaymentEntity clientPayment = modelMapper.map(requestMoviePayment, ClientPaymentEntity.class);
        ClientPaymentEntity saved = paymentRepository.save(clientPayment);
        return modelMapper.map(saved, ResponseMoviePayment.class);
    }
}
