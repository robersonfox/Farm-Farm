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

import br.com.cleancode.usecases.farm.FarmRequest;
import br.com.cleancode.usecases.farm.FarmResponse;
import br.com.cleancode.usecases.farm.IFarm;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/farm")
@RequiredArgsConstructor
public class FarmController {

    private final IFarm iFarm;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getFarmlById(@PathVariable(required = true) Long id) {

        try {
            var farm = iFarm.getById(id);
            return ResponseEntity.ok().body(farm);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(id);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> insertFarm(@RequestBody(required = true) FarmRequest request) {
        try {
            var farm = iFarm.insert(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(farm);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFarm(@RequestBody(required = true) FarmRequest request) {
        try {
            iFarm.delete(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }

    @GetMapping(path = "/all/page/{page}", produces = "application/json")
    public ResponseEntity<List<FarmResponse>> getAll(@PathVariable(required = true) int page) {

        try {
            var farms = iFarm.all(page);

            return ResponseEntity.status(HttpStatus.OK).body(farms);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
