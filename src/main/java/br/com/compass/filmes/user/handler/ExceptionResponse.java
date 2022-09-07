package br.com.compass.filmes.user.handler;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ExceptionResponse {
    private Date date;
    private String message;
    private String type;
}
