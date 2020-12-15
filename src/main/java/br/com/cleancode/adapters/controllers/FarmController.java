package br.com.cleancode.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleancode.usecases.farm.FarmRequest;
import br.com.cleancode.usecases.farm.IFarm;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/farm")
@RequiredArgsConstructor
public class FarmController {

    private final IFarm iFarm;

    @GetMapping
    public ResponseEntity<?> getFarmlById(@RequestBody(required = true) FarmRequest request) {

        try {
            var farm = iFarm.getById(request);
            return ResponseEntity.ok().body(farm);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertFarm(@RequestBody(required = true) FarmRequest request) {
        try {
            var farm = iFarm.insert(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(farm);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(request);
        }
    }
}
