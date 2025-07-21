package com.ravenson.billgenerator.administration.controller;

import com.ravenson.billgenerator.administration.model.Programs;
import com.ravenson.billgenerator.administration.services.ProgramsService;
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
@RequestMapping("api/v1/programs")
public class ProgramsController {

    private final ProgramsService programsService;


    @PostMapping("create")
    public ResponseEntity<String> create(
            @Valid
            @RequestBody
            @NotNull(message = "Model can not be null")
            Programs model
    ){
        return programsService.create(model);
    }

    @GetMapping("read")
    public ResponseEntity<List<Programs>> read(){
        return programsService.read();
    }
    @GetMapping("/read-by-id")
    public ResponseEntity<Programs> readById(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return programsService.readById(id);
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
            Programs model
    ){
        return programsService.update(id, model);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return programsService.delete(id);
    }
}
