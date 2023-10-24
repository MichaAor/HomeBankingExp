package com.cybrix.homebanking.Repository;

import com.cybrix.homebanking.Model.Account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    boolean existsByAccountN(Long accountN);
    boolean existsByCbu(Long cbu);
    boolean existsByAlias(String alias);
    Optional<Account> findByAccountN(Long accountN);
}
