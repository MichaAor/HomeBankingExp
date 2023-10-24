package com.cybrix.homebanking.Controller;

import com.cybrix.homebanking.Model.Currency;
import com.cybrix.homebanking.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/currencies")
public class CurrencyController {
    @Autowired
    private CurrencyService cs;

    /*
        *ADMIN CONTROLLERS
    */

    @GetMapping
    ResponseEntity<List<Currency>> findAll(){
        return ResponseEntity.ok(cs.findAll());
    }

    @GetMapping("/{idC}")
    ResponseEntity<Currency> findById(@PathVariable("idC")Long idU){
        Optional<Currency> optCurr = cs.findById(idU);
        return (optCurr.isPresent())? ResponseEntity.ok(optCurr.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<Currency> save(@RequestBody Currency currency){
        Currency currencySaved = cs.saveOrUpd(currency);
        if(currencySaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(currencySaved);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idC}")
    ResponseEntity<Currency> update(@RequestBody Currency currency,@PathVariable("idC")Long idC){
        currency.setIdC(idC);
        Currency currencyUpd = cs.saveOrUpd(currency);
        if(! currency.equals(currencyUpd)){
            return ResponseEntity.ok(currencyUpd);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idU}")
    ResponseEntity<?> deleteById(@PathVariable("idU")Long idU){
        return (cs.deleteById(idU))?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
