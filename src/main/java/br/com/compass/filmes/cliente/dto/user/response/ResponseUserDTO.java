package br.com.compass.filmes.cliente.dto.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseUserDTO {
    private String id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    @JsonProperty("is_blocked")
    private boolean isBlocked;
    private List<ResponseCreditCardDTO> cards;
}
