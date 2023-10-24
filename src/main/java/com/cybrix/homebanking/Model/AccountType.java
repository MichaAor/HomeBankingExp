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
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAT;
    private String name;

    @OneToMany(mappedBy = "accType",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Account> accounts;
}
