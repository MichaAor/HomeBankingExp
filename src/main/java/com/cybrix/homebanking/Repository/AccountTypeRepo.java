package com.cybrix.homebanking.Repository;

import com.cybrix.homebanking.Model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepo extends JpaRepository<AccountType,Long> {
}
