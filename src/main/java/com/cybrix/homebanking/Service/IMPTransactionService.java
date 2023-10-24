package com.cybrix.homebanking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybrix.homebanking.Model.Transaction;
import com.cybrix.homebanking.Repository.TransactionRepo;

@Service
public class IMPTransactionService implements TransactionService{
    @Autowired
    private TransactionRepo tr;


    /*
        ? ADMIN SERVICES
    */

    @Override
    public List<Transaction> findAll() {
        return tr.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long idT) {
        return tr.findById(idT);
    }

    @Override
    public Transaction saveOrUpd(Transaction transaction) {
        if(transaction.getIdT() != null){
            Optional<Transaction> transExist = tr.findById(transaction.getIdT());
            if(transExist.isPresent()){
                Transaction transToUpd = transExist.map(trans -> {
                    trans.setIdT(transaction.getIdT());
                    trans.setTransN(transaction.getTransN());
                    trans.setAmount(transaction.getAmount());
                    trans.setOrigin(transaction.getOrigin());

                    return trans;
                }).get();
                return tr.save(transToUpd);
            }else {
                return tr.save(transaction);
            }
        }
        return tr.save(transaction);
    }

    @Override
    public boolean deleteById(Long idT) {
        tr.deleteById(idT);
    return tr.existsById(idT);    
    }
    
}
