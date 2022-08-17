package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.entities.enums.CreditCardBrandEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CreditCardBrandEnum clientCreditCardBrand;
    private String clientCreditCardNumber;
    private String clientCreditCardSecurityCode;
    private String clientCreditCardYearExpiration;
    private String clientCreditCardMonthExpiration;
    private String clientCreditCardHolderName;
}
