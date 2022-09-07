package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.user.request.RequestSetStatusUserAccount;

public class RequestSetStatusUserAccountBuilder {

    private RequestSetStatusUserAccount requestSetStatusUserAccount;

    public RequestSetStatusUserAccountBuilder(){
    }

    public static RequestSetStatusUserAccountBuilder blocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccount = new RequestSetStatusUserAccount();

        builder.requestSetStatusUserAccount.setUserIsBlocked(true);

        return builder;
    }

    public static RequestSetStatusUserAccountBuilder unlocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccount = new RequestSetStatusUserAccount();

        builder.requestSetStatusUserAccount.setUserIsBlocked(false);

        return builder;
    }

    public RequestSetStatusUserAccount now(){
        return this.requestSetStatusUserAccount;
    }
}
