package com.cybrix.homebanking.Service;

import java.util.List;
import java.util.Optional;

import com.cybrix.homebanking.Model.Transference;

public interface TransferenceService {
    List<Transference> findAll();
    Optional<Transference> findById(Long idTr);
    Transference saveOrUpd(Transference tsDTO);
    boolean deleteById(Long idTr);
}
