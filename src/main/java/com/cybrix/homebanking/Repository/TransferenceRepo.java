package com.cybrix.homebanking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybrix.homebanking.Model.Transference;

@Repository
public interface TransferenceRepo extends JpaRepository<Transference, Long> {

}
