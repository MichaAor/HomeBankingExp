package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.DTO.AccountDTO;
import com.cybrix.homebanking.Model.Account;
import com.cybrix.homebanking.Repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class IMPAccountService implements AccountService {
    @Autowired
    private AccountRepo ar;

    
    /*
        ? ADMIN SERVICES
    */

    @Override
    public List<Account> findAll() {
        return ar.findAll();
    }

    @Override
    public Optional<Account> findById(Long idA) {
        return ar.findById(idA);
    }

    @Override
    public Account saveOrUpd(Account account) {
        if(account.getIdA() != null){
            Optional<Account> accountExist = ar.findById(account.getIdA());
            if(accountExist.isPresent()){
                Account accountToUpd = accountExist.map(acc -> {
                    acc.setIdA(account.getIdA());
                    acc.setAccountN(account.getAccountN());
                    acc.setCbu(account.getCbu());
                    acc.setAlias(account.getAlias());
                    acc.setBalance(account.getBalance());
                        return acc;
                }).get();
                return ar.save(accountToUpd);
            }else {
                return ar.save(account);
            }
        }
        return ar.save(account);
    }

   
    @Override
    public boolean deleteById(Long idA) {
        ar.deleteById(idA);
        return ar.existsById(idA);
    }


    /*
        ? PUBLIC SERVICES
    */

    @Override
    public boolean createOrUpdateAccount(AccountDTO accDTO){
        Account accSavedOrUpdated = new Account();

        if(accDTO.getAlias() != null && accDTO.getAccN() != null){
            Optional<Account> accFound = ar.findByAccountN(accDTO.getAccN());
            if(accFound.isPresent()){
                accFound.get().setAlias(accDTO.getAlias());
                accSavedOrUpdated = ar.save(accFound.get());
            return (accSavedOrUpdated != null);
            }
        }
        
        Account accToSave = accountGen(accDTO);
        accSavedOrUpdated = ar.save(accToSave);
    return (accSavedOrUpdated != null);    
    }

    public Account newUserAccountGen(){
        AccountDTO dto = new AccountDTO();
        dto.setIdAccountType(1L);
        dto.setIdCurrency(1L);
    return accountGen(dto);    
    }


    /*
        ? SUPPORT SERVICE METHODS
    */

    private Account accountGen(AccountDTO accDTO){
        Account account = new Account();
        account.setAccountN(accountNumberGen());
        account.setCbu(cbuGen());
        account.setAlias(aliasGen());
        if(accDTO.getIdUser() != null){
            account.addUser(accDTO.getIdUser());
        }
        account.addAccType(accDTO.getIdAccountType());
        account.addCurrency(accDTO.getIdCurrency());
        account.setBalance(0f);

    return account;    
    }

    private Long accountNumberGen(){
        Long accountNumber = 0L;
        Random random = new Random();
        do{
            accountNumber = Math.abs(random.nextLong() % (Long.MAX_VALUE + 1));  
        }while(ar.existsByAccountN(accountNumber)); 

    return accountNumber;    
    }

    private Long cbuGen(){
        Long cbu = 0L;
        Random random = new Random();
        do{
            cbu = Math.abs(random.nextLong() % (Long.MAX_VALUE + 1));       
        }while(ar.existsByCbu(cbu)); 

    return cbu;
    }

    private String aliasGen(){
        String[] words = {
            "azul", "verde", "rojo", "amarillo", "naranja", "blanco",
            "montana", "playa", "ciudad", "campo", "bosque", "desierto",
            "flor", "sol", "luna", "estrella", "mariposa", "puma",
            "guitarra", "piano", "flauta", "violin", "trompeta", "barco"
        };

        String alias = null;
        Random random = new Random();

        do{
            alias = IntStream.range(0, 3)
                                .mapToObj(word -> words[random.nextInt(words.length)])
                                .collect(Collectors.joining("."));
        }while(ar.existsByAlias(alias)); 

    return alias;               
    }
}
