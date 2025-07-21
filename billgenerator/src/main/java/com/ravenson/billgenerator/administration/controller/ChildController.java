package com.ravenson.billgenerator.administration.controller;

import com.ravenson.billgenerator.administration.model.Child;
import com.ravenson.billgenerator.administration.model.Programs;
import com.ravenson.billgenerator.administration.services.ChildService;
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
@RequestMapping("/api/v1/child")
public class ChildController {

    private final ChildService childService;

    @PostMapping("create")
    public ResponseEntity<String> create(
            @Valid
            @RequestBody
            @NotNull(message = "Model can not be null")
            Child model
    ){
        return childService.create(model);
    }

    @GetMapping("read")
    public ResponseEntity<List<Child>> read(){
        return childService.read();
    }
    @GetMapping("/read-by-id")
    public ResponseEntity<Child> readById(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return childService.readById(id);
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
            Child model
    ){
        return childService.update(id, model);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return childService.delete(id);
    }
}
