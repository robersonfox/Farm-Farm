package br.com.cleancode.adapters.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleancode.usecases.animal.AnimalRequest;
import br.com.cleancode.usecases.animal.AnimalResponse;
import br.com.cleancode.usecases.animal.IAnimal;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnnimalController {

    private final IAnimal iAnimal;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> insertAnimal(@RequestBody(required = true) AnimalRequest request) {

        try {
            var animal = iAnimal.insert(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(animal);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }

    @PostMapping(path = "alot", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> insertAnimals(@RequestBody(required = true) List<AnimalRequest> request) {

        try {
            var animal = iAnimal.insert(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(animal);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAnimal(@RequestBody(required = true) AnimalRequest request) {
        try {
            iAnimal.delete(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }

    @GetMapping(path = "/all/page/{page}", produces = "application/json")
    public ResponseEntity<List<AnimalResponse>> getAll(@PathVariable(required = true) int page) {

        try {
            var farms = iAnimal.all(page);

            return ResponseEntity.status(HttpStatus.OK).body(farms);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
