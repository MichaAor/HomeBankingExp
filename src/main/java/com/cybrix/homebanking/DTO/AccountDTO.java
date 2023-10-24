package com.cybrix.homebanking.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private String alias;
    private Long accN;
    private Long idUser;
    private Long idAccountType;
    private Long idCurrency;
}
