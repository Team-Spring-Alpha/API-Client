package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.entities.ClientEntity;
import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import br.com.compass.filmes.cliente.enums.ClientCategoryEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientEntityBuilder {

    private ClientEntity clientEntity;

    public ClientEntityBuilder() {
    }

    public static ClientEntityBuilder one() {
        ClientEntityBuilder builder = new ClientEntityBuilder();
        builder.clientEntity = new ClientEntity();

        builder.clientEntity.setId("1");
        builder.clientEntity.setClientName("Jetosvaldo");
        builder.clientEntity.setClientCpf("686.751.800-12");
        builder.clientEntity.setClientEmail("yosope1626@wnpop.com");
        builder.clientEntity.setClientPassword("123");
        builder.clientEntity.setClientIsBlocked(false);
        builder.clientEntity.setClientBirthDate(LocalDate.now());

        List<ClientCategoryEnum> categoryEnumList = new ArrayList<>();
        categoryEnumList.add(ClientCategoryEnum.ACAO);
        builder.clientEntity.setClientCategory(categoryEnumList);

        List<CreditCardEntity> creditCardEntityList = CreditCardEntityBuilder.one().list();
        builder.clientEntity.setCreditCards(creditCardEntityList);

        return builder;
    }

    public ClientEntityBuilder withId(String id) {
        this.clientEntity.setId(id);
        return this;
    }

    public ClientEntityBuilder withClientName(String clientName) {
        this.clientEntity.setClientName(clientName);
        return this;
    }

    public ClientEntityBuilder withClientCpf(String clientCpf) {
        this.clientEntity.setClientCpf(clientCpf);
        return this;
    }

    public ClientEntityBuilder withClientEmail(String clientEmail) {
        this.clientEntity.setClientEmail(clientEmail);
        return this;
    }

    public ClientEntityBuilder withClientPassword(String clientPassword) {
        this.clientEntity.setClientPassword(clientPassword);
        return this;
    }

    public ClientEntityBuilder withClientIsBlocked(boolean clientIsBlocked) {
        this.clientEntity.setClientIsBlocked(clientIsBlocked);
        return this;
    }

    public ClientEntityBuilder withClientBirthDate(LocalDate clientBirthDate) {
        this.clientEntity.setClientBirthDate(clientBirthDate);
        return this;
    }

    public ClientEntityBuilder withClientCategory(List<ClientCategoryEnum> categoryEnumList) {
        this.clientEntity.setClientCategory(categoryEnumList);
        return this;
    }

    public ClientEntityBuilder withCreditCard(List<CreditCardEntity> creditCardEntity) {
        this.clientEntity.setCreditCards(creditCardEntity);
        return this;
    }

    public ClientEntityBuilder withRequestClient(RequestClient requestClient) {
        this.clientEntity.setId("idTeste");
        this.clientEntity.setClientName(requestClient.getClientName());
        this.clientEntity.setClientCpf(requestClient.getClientCpf());
        this.clientEntity.setClientEmail(requestClient.getClientEmail());
        this.clientEntity.setClientPassword(requestClient.getClientPassword());
        this.clientEntity.setClientIsBlocked(false);
        this.clientEntity.setClientBirthDate(requestClient.getClientBirthDate());

        this.clientEntity.setClientCategory(requestClient.getClientCategory().stream().map(
                ClientCategoryEnum::valueOf
        ).collect(Collectors.toList()));


        this.clientEntity.setCreditCards(requestClient.getCreditCards().stream().map(
                requestCreditCard -> CreditCardEntityBuilder.one().withRequestCreditCard(requestCreditCard).now()
                ).collect(Collectors.toList()));

        return this;
    }

    public ClientEntity now() {
        return this.clientEntity;
    }

}
