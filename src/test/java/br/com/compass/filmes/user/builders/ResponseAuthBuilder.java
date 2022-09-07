package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.payment.response.ResponseAuthDTO;

public class ResponseAuthBuilder {

    private ResponseAuthDTO responseAuthDTO;

    public ResponseAuthBuilder() {
    }

    public static ResponseAuthBuilder one() {
        ResponseAuthBuilder builder = new ResponseAuthBuilder();
        builder.responseAuthDTO = new ResponseAuthDTO();

        builder.responseAuthDTO.setToken("test token");
        builder.responseAuthDTO.setExpiresIn("180");
        builder.responseAuthDTO.setTokenType("bearer");

        return builder;
    }

    public ResponseAuthBuilder withToken(String token) {
        this.responseAuthDTO.setToken(token);
        return this;
    }

    public ResponseAuthBuilder withExpiresIn(String expiresIn) {
        this.responseAuthDTO.setExpiresIn(expiresIn);
        return this;
    }

    public ResponseAuthBuilder withTokenType(String tokenType) {
        this.responseAuthDTO.setTokenType(tokenType);
        return this;
    }

    public ResponseAuthDTO now() {
        return this.responseAuthDTO;
    }

}
