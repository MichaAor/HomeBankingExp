package com.cybrix.homebanking.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenceDTO {
    private Long transN;
    private float amount;
    private Long idOrigin;

    private Long transferN;
    private String reference;
    private Long idDestiny;
}
