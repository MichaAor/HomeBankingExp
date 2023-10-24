package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.Model.Currency;
import com.cybrix.homebanking.Repository.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class IMPCurrService implements CurrencyService{
    @Autowired
    private CurrencyRepo cr;

    
    /*
        ? ADMIN SERVICES
    */

    @Override
    public List<Currency> findAll() {
        return cr.findAll();
    }

    @Override
    public Optional<Currency> findById(Long idC) {
        return cr.findById(idC);
    }

    @Override
    public Currency saveOrUpd(Currency currency) {
        if(currency.getIdC() != null){
            Optional<Currency> currencyExist = cr.findById(currency.getIdC());
            if(currencyExist.isPresent()){
                Currency currencyToUpdate = currencyExist.map(curr -> {
                    curr.setIdC(currency.getIdC());
                    curr.setName(currency.getName());
                    return curr;
                }).get();
                return cr.save(currencyToUpdate);
            }else {
                return cr.save(currency);
            }
        }
        return cr.save(currency);
    }

    @Override
    public boolean deleteById(Long idC) {
        cr.deleteById(idC);
        return cr.existsById(idC);
    }
}
