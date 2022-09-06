package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.apiPayment.response.ResponseAuth;

public class ResponseAuthBuilder {

    private ResponseAuth responseAuth;

    public ResponseAuthBuilder() {
    }

    public static ResponseAuthBuilder one() {
        ResponseAuthBuilder builder = new ResponseAuthBuilder();
        builder.responseAuth = new ResponseAuth();

        builder.responseAuth.setToken("test token");
        builder.responseAuth.setExpiresIn("180");
        builder.responseAuth.setTokenType("bearer");

        return builder;
    }

    public ResponseAuthBuilder withToken(String token) {
        this.responseAuth.setToken(token);
        return this;
    }

    public ResponseAuthBuilder withExpiresIn(String expiresIn) {
        this.responseAuth.setExpiresIn(expiresIn);
        return this;
    }

    public ResponseAuthBuilder withTokenType(String tokenType) {
        this.responseAuth.setTokenType(tokenType);
        return this;
    }

    public ResponseAuth now() {
        return this.responseAuth;
    }

}
