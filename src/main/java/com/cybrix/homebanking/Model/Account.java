package com.cybrix.homebanking.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idA")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idA;

    @Column(unique = true)
    private Long accountN;

    @Column(unique = true)
    private Long cbu;

    @Column(unique = true)
    private String alias;
    private float balance;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private AccountType accType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private Currency currency;

    @OneToMany(mappedBy = "origin",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("transactions")
    private List<Transaction> transactions;

    /*
        ?ADD METHODS
    */
    public void addUser(Long idU){
        User userAdd = new User();
        userAdd.setIdU(idU);
        this.setUser(userAdd);
    }

    public void addAccType(Long idAT){
        AccountType accTypeAdd = new AccountType();
        accTypeAdd.setIdAT(idAT);
        this.setAccType(accTypeAdd);
    }

    public void addCurrency(Long idC){
        Currency currAdd = new Currency();
        currAdd.setIdC(idC);
        this.setCurrency(currAdd);
    }
}
