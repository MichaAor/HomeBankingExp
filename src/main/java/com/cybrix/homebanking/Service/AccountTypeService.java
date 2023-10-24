package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.Model.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {
    List<AccountType> findAll();
    Optional<AccountType> findById(Long idAT);
    AccountType saveOrUpd(AccountType accType);
    boolean deleteById(Long idAT);
}
