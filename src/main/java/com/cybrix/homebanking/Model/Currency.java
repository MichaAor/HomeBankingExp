package com.cybrix.homebanking.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;
    private String name;

    @OneToMany(mappedBy = "currency",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Account> accounts;
}
