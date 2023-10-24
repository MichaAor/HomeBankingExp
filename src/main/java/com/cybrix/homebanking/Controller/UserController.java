package com.cybrix.homebanking.Controller;

import com.cybrix.homebanking.Model.User;
import com.cybrix.homebanking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/users")
public class UserController {
    @Autowired
    private UserService us;

    /*
        *ADMIN CONTROLLERS
    */

    @GetMapping
    ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(us.findAll());
    }

    @GetMapping("/{idU}")
    ResponseEntity<User> findById(@PathVariable("idU")Long idU){
        Optional<User> optUs = us.findById(idU);
        if(optUs.isPresent()){
            return ResponseEntity.ok(optUs.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

   @GetMapping("/dni/{dni}")
   ResponseEntity<User> findByDni(@PathVariable("dni")String dni){
       Optional<User> optUs = us.findByDNI(dni);
       if(optUs.isPresent()){
           return ResponseEntity.ok(optUs.get());
       }else{
           return ResponseEntity.notFound().build();
       }
   }

    @PostMapping
    ResponseEntity<User> save(@RequestBody User user){
        User userSaved = us.saveOrUpd(user);
        if(userSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idU}")
    ResponseEntity<User> update(@RequestBody User user,@PathVariable("idU")Long idU){
        user.setIdU(idU);
        User userUpd = us.saveOrUpd(user);
        if(! user.equals(userUpd)){
            return ResponseEntity.ok(userUpd);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idU}")
    ResponseEntity<?> deleteById(@PathVariable("idU")Long idU){
        return (us.deleteById(idU))?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

   @DeleteMapping("/{dni}")
   ResponseEntity<?> deleteByDni(@PathVariable("dni")String dni){
       return (us.deleteByDNI(dni))?
               ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
   }

   /*
        *PUBLIC CONTROLLERS
   */
    
   @PostMapping("/register")
   ResponseEntity<User> register(@RequestBody User user){
    User userSaved = us.registerOrUpdate(user);
        if(userSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
        }else{
            return ResponseEntity.badRequest().build();
        }
   }
}
