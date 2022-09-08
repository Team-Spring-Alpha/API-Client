package br.com.compass.filmes.user.dto.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseUserDTO {
    private String id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    @ApiModelProperty(example = "23/02/1978")
    private LocalDate birthDate;
    @JsonProperty("is_blocked")
    @ApiModelProperty(example = "true")
    private boolean isBlocked;
    private List<ResponseCreditCardDTO> cards;
}
