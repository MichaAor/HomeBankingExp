package com.cybrix.homebanking.Service;

import com.cybrix.homebanking.Model.Account;
import com.cybrix.homebanking.Model.User;
import com.cybrix.homebanking.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMPUserService implements UserService {
    @Autowired
    private UserRepo ur;

    @Autowired
    private AccountService as;

    
    /*
        ? ADMIN SERVICES
    */

    @Override
    public List<User> findAll() {
        return ur.findAll();
    }

    @Override
    public Optional<User> findById(Long idU) {
        return ur.findById(idU);
    }

    @Override
    public Optional<User> findByDNI(String dni) {
        return ur.findByDni(dni);
    }

    @Override
    public User saveOrUpd(User userNew) {
        if(userNew.getIdU() != null){
            Optional<User> userExist = ur.findById(userNew.getIdU());
            if(userExist.isPresent()){
                User userToUpd = userExist.map(us -> {
                                                    us.setIdU(userNew.getIdU());
                                                    us.setName(userNew.getName());
                                                    us.setSurname(userNew.getSurname());
                                                    us.setDni(userNew.getDni());
                                                return us;
                                                    }).get();
                return ur.save(userToUpd);
            }else {
                return ur.save(userNew);
            }
        }
        return ur.save(userNew);
    }

    @Override
    public boolean deleteById(Long id) {
        ur.deleteById(id);
    return ur.existsById(id);
    }

    @Override
    public boolean deleteByDNI(String dni) {
        ur.deleteByDni(dni);
    return ur.existsByDni(dni);
    }


    /*
        ? PUBLIC SERVICES
    */

    @Override
    public User registerOrUpdate(User userNew) {
        User userToUpd = null;
        if(userNew.getIdU() != null){
            Optional<User> userExist = ur.findById(userNew.getIdU());
            if(userExist.isPresent()){
                userToUpd = userExist.map(us -> {
                                                    us.setIdU(userNew.getIdU());
                                                    us.setName(userNew.getName());
                                                    us.setSurname(userNew.getSurname());
                                                    us.setDni(userNew.getDni());
                                                return us;
                                                    }).get();
            }
        }
        Account accountInit = as.newUserAccountGen();
        accountInit.setUser(userNew);
        userNew.getAccounts().add(accountInit);
    return (userToUpd == null)? ur.save(userNew) : ur.save(userToUpd);
    }
}
