package com.cybrix.homebanking.Service;

import java.util.List;
import java.util.Optional;

import com.cybrix.homebanking.Model.Transaction;

public interface TransactionService {
    List<Transaction> findAll();
    Optional<Transaction> findById(Long idT);
    Transaction saveOrUpd(Transaction transaction);
    boolean deleteById(Long idT);
}
