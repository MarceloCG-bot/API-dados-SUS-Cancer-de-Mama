package com.dio_class.devweek.Controller;

import com.dio_class.devweek.Entity.Incidencia;
import com.dio_class.devweek.Repo.IncidenciaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerIncidencia {
    private final IncidenciaRepo ocRepository;

    public ControllerIncidencia(IncidenciaRepo ocRepository){
        this.ocRepository = ocRepository;
    }

    @GetMapping("/incidencia")
    public ResponseEntity<?> findAllIncidencia(){
        try{
            List<Incidencia> lista = ocRepository.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/incidencia/{id}")
    public ResponseEntity<Incidencia> findOcorrenciasById(@PathVariable Long id) {
        Optional<Incidencia> ocorrenciaOptional = ocRepository.findById(id);
        if (ocorrenciaOptional.isPresent()) {
            Incidencia ocorrenciaUnid = ocorrenciaOptional.get();
            return new ResponseEntity<>(ocorrenciaUnid, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/incidencia/novo")
    public Incidencia newIncidencia(@RequestBody Incidencia newIncidencia){
        return ocRepository.save(newIncidencia);
    }

    @DeleteMapping("/incidencia/remover/{id}")
    public void deleteIncidencia (@PathVariable Long id){
        ocRepository.deleteById(id);
    }
}
