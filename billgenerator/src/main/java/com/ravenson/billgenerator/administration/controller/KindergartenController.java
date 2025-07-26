package com.ravenson.billgenerator.administration.controller;

import com.ravenson.billgenerator.administration.model.Kindergarten;
import com.ravenson.billgenerator.administration.services.KindergartenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/kindergarten")
public class KindergartenController {

    private final KindergartenService kindergartenService;

    @PostMapping("create")
    public ResponseEntity<String> create(
            @Valid
            @RequestBody
            @NotNull(message = "Model can not be null")
            Kindergarten model
    ){
        return kindergartenService.create(model);
    }

    @GetMapping("read")
    public ResponseEntity<List<Kindergarten>> read(){
        return kindergartenService.read();
    }
    @GetMapping("/read-by-id")
    public ResponseEntity<Kindergarten> readById(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return kindergartenService.readById(id);
    }
    @GetMapping("/read-by-pib")
    public ResponseEntity<Kindergarten> readByPib(
            @Valid
            @RequestParam("pib")
            @NotNull(message = "Pib can not be null")
            Integer pib
    ){
        return kindergartenService.readByPib(pib);
    }

    @GetMapping("/read-by-account-name")
    public ResponseEntity<Kindergarten> readByAccountNumber(
            @Valid
            @RequestParam("account_number")
            @NotNull(message = "Account number can not be null")
            String accountNumber
    ){
        return kindergartenService.readByAccountNumber(accountNumber);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id,
            @RequestBody
            @NotNull(message = "Model can not be null")
            Kindergarten model
    ){
        return kindergartenService.update(id, model);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return kindergartenService.delete(id);
    }
}
