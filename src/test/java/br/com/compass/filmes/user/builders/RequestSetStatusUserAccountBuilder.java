package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.user.request.RequestSetStatusUserAccountDTO;

public class RequestSetStatusUserAccountBuilder {

    private RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO;

    public RequestSetStatusUserAccountBuilder(){
    }

    public static RequestSetStatusUserAccountBuilder blocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccountDTO = new RequestSetStatusUserAccountDTO();

        builder.requestSetStatusUserAccountDTO.setBlocked(true);

        return builder;
    }

    public static RequestSetStatusUserAccountBuilder unlocked(){
        RequestSetStatusUserAccountBuilder builder = new RequestSetStatusUserAccountBuilder();
        builder.requestSetStatusUserAccountDTO = new RequestSetStatusUserAccountDTO();

        builder.requestSetStatusUserAccountDTO.setBlocked(false);

        return builder;
    }

    public RequestSetStatusUserAccountDTO now(){
        return this.requestSetStatusUserAccountDTO;
    }
}
