package com.cybrix.homebanking.Controller;

import com.cybrix.homebanking.Model.AccountType;
import com.cybrix.homebanking.Service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/accTypes")
public class AccountTypeController {
    @Autowired
    private AccountTypeService atS;

    /*
        *ADMIN CONTROLLERS
    */

    @GetMapping
    ResponseEntity<List<AccountType>> findAll(){
        return ResponseEntity.ok(atS.findAll());
    }

    @GetMapping("/{idAT}")
    ResponseEntity<AccountType> findById(@PathVariable("idAT")Long idAT){
        Optional<AccountType> accTypeOpt = atS.findById(idAT);
        return (accTypeOpt.isPresent())? ResponseEntity.ok(accTypeOpt.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<AccountType> save(@RequestBody AccountType accType){
        AccountType accTypeSaved = atS.saveOrUpd(accType);
        if(accTypeSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(accTypeSaved);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idAT}")
    ResponseEntity<AccountType> update(@RequestBody AccountType accType,@PathVariable("idAT")Long idAT){
        accType.setIdAT(idAT);
        AccountType accTypeUpd = atS.saveOrUpd(accType);
        if(! accType.equals(accTypeUpd)){
            return ResponseEntity.ok(accTypeUpd);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAT}")
    ResponseEntity<?> deleteById(@PathVariable("idAT")Long idAT){
        if(atS.deleteById(idAT)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
