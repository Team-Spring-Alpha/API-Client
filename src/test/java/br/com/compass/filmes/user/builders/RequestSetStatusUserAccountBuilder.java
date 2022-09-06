package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.user.request.RequestSetStatusUserAccount;

public class RequestSetStatusUserAccountBuilder {

    private RequestSetStatusUserAccount requestSetStatusUserAccount;

    public RequestSetStatusUserAccountBuilder(){
    }

    public static RequestSetStatusUserAccountBuilder blocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccount = new RequestSetStatusUserAccount();

        builder.requestSetStatusUserAccount.setBlocked(true);

        return builder;
    }

    public static RequestSetStatusUserAccountBuilder unlocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccount = new RequestSetStatusUserAccount();

        builder.requestSetStatusUserAccount.setBlocked(false);

        return builder;
    }

    public RequestSetStatusUserAccount now(){
        return this.requestSetStatusUserAccount;
    }
}
