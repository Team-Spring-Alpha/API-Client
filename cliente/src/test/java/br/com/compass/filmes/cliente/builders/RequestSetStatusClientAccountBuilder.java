package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.client.request.RequestSetStatusClientAccount;

public class RequestSetStatusClientAccountBuilder {

    private RequestSetStatusClientAccount requestSetStatusClientAccount;

    public RequestSetStatusClientAccountBuilder(){
    }

    public static RequestSetStatusClientAccountBuilder blocked(){
        RequestSetStatusClientAccountBuilder builder = new RequestSetStatusClientAccountBuilder();
        builder.requestSetStatusClientAccount = new RequestSetStatusClientAccount();

        builder.requestSetStatusClientAccount.setClientIsBlocked(true);

        return builder;
    }

    public static RequestSetStatusClientAccountBuilder unlocked(){
        RequestSetStatusClientAccountBuilder builder = new RequestSetStatusClientAccountBuilder();
        builder.requestSetStatusClientAccount = new RequestSetStatusClientAccount();

        builder.requestSetStatusClientAccount.setClientIsBlocked(false);

        return builder;
    }

    public RequestSetStatusClientAccount now(){
        return this.requestSetStatusClientAccount;
    }
}
