package com.cybrix.homebanking.Controller;

import com.cybrix.homebanking.DTO.AccountDTO;
import com.cybrix.homebanking.Model.Account;
import com.cybrix.homebanking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/accounts")
public class AccountController {
    @Autowired
    private AccountService aS;

    /*
        *ADMIN CONTROLLERS
    */

    @GetMapping
    ResponseEntity<List<Account>> findAll(){
        return ResponseEntity.ok(aS.findAll());
    }

    @GetMapping("/{idA}")
    ResponseEntity<Account> findById(@PathVariable("idA")Long idA){
        Optional<Account> accOpt = aS.findById(idA);
        return (accOpt.isPresent())? ResponseEntity.ok(accOpt.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{idU}/{idAT}/{idC}")
    ResponseEntity<Account> save(@RequestBody Account account
                                ,@PathVariable("idU")Long idU
                                ,@PathVariable("idAT")Long idAT
                                ,@PathVariable("idC")Long idC){
            account.addUser(idU);
            account.addAccType(idAT);
            account.addCurrency(idC);
        Account accSaved = aS.saveOrUpd(account);
        if(accSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(accSaved);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idA}")
    ResponseEntity<Account> update(@RequestBody Account account,@PathVariable("idA")Long idA){
        account.setIdA(idA);
        Account accUpd = aS.saveOrUpd(account);
        if(! account.equals(accUpd)){
            return ResponseEntity.ok(accUpd);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idA}")
    ResponseEntity<?> deleteById(@PathVariable("idA")Long idA){
        if(aS.deleteById(idA)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /*
        *PUBLIC CONTROLLERS
    */

    @PostMapping
    ResponseEntity<?> createAccount(@RequestBody AccountDTO accDTO){
        if(aS.createOrUpdateAccount(accDTO)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    ResponseEntity<?> updateAlias(@RequestBody AccountDTO accDTO){
        if(aS.createOrUpdateAccount(accDTO)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
