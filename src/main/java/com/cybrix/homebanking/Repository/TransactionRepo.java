package com.cybrix.homebanking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybrix.homebanking.Model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}
