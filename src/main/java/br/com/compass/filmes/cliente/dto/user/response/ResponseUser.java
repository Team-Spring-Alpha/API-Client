package br.com.compass.filmes.cliente.dto.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseUser {
    private String id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private boolean clientIsBlocked;
    private List<ResponseCreditCard> cards;
}
