package com.example.ejercicio1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(Laptop.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }
    @GetMapping("/laptops")
    public List<Laptop> BuscarLaptops (){return laptopRepository.findAll();}
@GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> BucarPorId (@PathVariable Long id){
    Optional<Laptop> laptopOpt = laptopRepository.findAllById(id);
    if (laptopOpt.isPresent())
        return ResponseEntity.ok(laptopOpt.get());
    else
        return ResponseEntity.notFound().build();
}
@PostMapping("/laptops")
public Laptop create(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
}

    @PutMapping("/laptops")
    public ResponseEntity<Object> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("trying to create a new laptop with update metod");
            System.out.println("trying to create a new laptop with update metod");
            return ResponseEntity.badRequest().build();
        } if (!laptopRepository.existsById(laptop.getId())){
            log.warn("trying to create a new laptop with update metod");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);

        }
    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if (!laptopRepository.existsById(id)){
            log.warn("trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

       laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request for delete all laptops");
        laptopRepository.deleteAll();
        return  ResponseEntity.noContent().build();
    }


}
