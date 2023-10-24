package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.Model.AccountType;
import com.cybrix.homebanking.Repository.AccountTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMPAccTypeService implements AccountTypeService{
    @Autowired
    private AccountTypeRepo accTR;

    
    /*
        ? ADMIN SERVICES
    */

    @Override
    public List<AccountType> findAll() {
        return accTR.findAll();
    }

    @Override
    public Optional<AccountType> findById(Long idAT) {
        return accTR.findById(idAT);
    }

    @Override
    public AccountType saveOrUpd(AccountType accType) {
        if(accType.getIdAT() != null){
            Optional<AccountType> accTypeExist = accTR.findById(accType.getIdAT());
            if(accTypeExist.isPresent()){
                AccountType accTypeToUpd = accTypeExist.map(accT -> {
                    accT.setIdAT(accType.getIdAT());
                    accT.setName(accType.getName());
                    return accT;
                }).get();
                return accTR.save(accTypeToUpd);
            }else {
                return accTR.save(accType);
            }
        }
        return accTR.save(accType);
    }

    @Override
    public boolean deleteById(Long idAT) {
        accTR.deleteById(idAT);
        return accTR.existsById(idAT);
    }
}
