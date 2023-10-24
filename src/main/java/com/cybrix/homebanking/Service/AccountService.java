package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.DTO.AccountDTO;
import com.cybrix.homebanking.Model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(Long idA);
    Account saveOrUpd(Account account);
    boolean createOrUpdateAccount(AccountDTO accDTO);
    boolean deleteById(Long idA);

    public Account newUserAccountGen();
}
