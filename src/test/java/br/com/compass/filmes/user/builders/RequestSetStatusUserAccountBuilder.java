package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.user.request.RequestSetStatusUserAccountDTO;

public class RequestSetStatusUserAccountBuilder {

    private RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO;

    public RequestSetStatusUserAccountBuilder(){
    }

    public static RequestSetStatusUserAccountBuilder blocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccountDTO = new RequestSetStatusUserAccountDTO();

        builder.requestSetStatusUserAccountDTO.setUserIsBlocked(true);

        return builder;
    }

    public static RequestSetStatusUserAccountBuilder unlocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccountDTO = new RequestSetStatusUserAccountDTO();

        builder.requestSetStatusUserAccountDTO.setUserIsBlocked(false);

        return builder;
    }

    public RequestSetStatusUserAccountDTO now(){
        return this.requestSetStatusUserAccountDTO;
    }
}
