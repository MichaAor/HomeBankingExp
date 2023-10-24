package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.Model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAll();
    Optional<Currency> findById(Long idC);
    Currency saveOrUpd(Currency curr);
    boolean deleteById(Long idC);
}
