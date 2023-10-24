package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long idU);
    Optional<User> findByDNI(String dni);
    User saveOrUpd(User user);
    boolean deleteById(Long id);
    boolean deleteByDNI(String dni);

    User registerOrUpdate(User user);
}
