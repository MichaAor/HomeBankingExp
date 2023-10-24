package com.cybrix.homebanking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybrix.homebanking.Model.Transference;
import com.cybrix.homebanking.Repository.TransferenceRepo;

@Service
public class IMPTransferenceService implements TransferenceService{
    @Autowired
    private TransferenceRepo tr;


    /*
        ? ADMIN SERVICES
    */

    @Override
    public List<Transference> findAll() {
        return tr.findAll();
    }

    @Override
    public Optional<Transference> findById(Long idTr) {
        return tr.findById(idTr);
    }

    @Override
    public Transference saveOrUpd(Transference transference) {
        Transference transToUpd = null;
        if(transference.getIdT() != null){
            Optional<Transference> transExist = tr.findById(transference.getIdT());
            if(transExist.isPresent()){
                transToUpd = transExist.map(transf -> {
                                                    transf.setIdT(transference.getIdT());
                                                    transf.setTransN(transference.getTransN());
                                                    transf.setAmount(transference.getAmount());
                                                    transf.setOrigin(transference.getOrigin());

                                                    transf.setTransferN(transference.getTransferN());
                                                    transf.setReference(transference.getReference());
                                                    transf.setDestiny(transference.getDestiny());
                                                return transf;
                                                    }).get();
            }
        }
        return (transToUpd == null)? tr.save(transference) : tr.save(transToUpd);
    }

    @Override
    public boolean deleteById(Long idTr) {
        tr.deleteById(idTr);
    return tr.existsById(idTr);    
    }
    
}
