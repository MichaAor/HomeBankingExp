package com.cybrix.homebanking.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cybrix.homebanking.Model.Transference;
import com.cybrix.homebanking.Service.TransferenceService;

@RestController
@RequestMapping("/apiHB/transferences")
public class TransferenceController {
    @Autowired
    private TransferenceService ts;


    @GetMapping
    public ResponseEntity<List<Transference>> findAll() {
        return ResponseEntity.ok().body(ts.findAll());
    }

    @GetMapping("/{idTr}")
    public ResponseEntity<Transference> findByID(@PathVariable("idTr") Long idTr) {
        Optional<Transference> transOpt = ts.findById(idTr);
        if(transOpt.isPresent()){
            return ResponseEntity.ok().body(transOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Transference> save(@RequestBody Transference transference) {
        Transference transSaved = ts.saveOrUpd(transference);
        if(transSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(transSaved);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idTr}")
    public ResponseEntity<?> update(@RequestBody Transference transference
                                   ,@PathVariable("idTr") Long idTr) {
        transference.setIdT(idTr);
        Transference transUpd = ts.saveOrUpd(transference);
        if(! transference.equals(transUpd)){
            return ResponseEntity.ok().body(transUpd);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idTr}")
    public ResponseEntity<?> delete(@PathVariable("idTr") Long idTr) {
        return (ts.deleteById(idTr))? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
