package com.cybrix.homebanking.Repository;

import com.cybrix.homebanking.Model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency,Long> {
}
