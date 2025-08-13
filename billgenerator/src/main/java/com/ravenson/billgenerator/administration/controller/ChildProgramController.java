package com.ravenson.billgenerator.administration.controller;

import com.ravenson.billgenerator.administration.model.ChildProgram;
import com.ravenson.billgenerator.administration.services.ChildProgramService;
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
@RequestMapping("/api/v1/child-program")
public class ChildProgramController {

    private final ChildProgramService childProgramService;

    @PostMapping("create")
    public ResponseEntity<String> create(
            @Valid
            @RequestBody
            @NotNull(message = "Model can not be null")
            ChildProgram model
    ){
        return childProgramService.create(model);
    }

    @GetMapping("read")
    public ResponseEntity<List<ChildProgram>> read(){
        return childProgramService.read();
    }

    @GetMapping("/read-by-id")
    public ResponseEntity<ChildProgram> readById(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return childProgramService.readById(id);
    }
    @GetMapping("/read-by-child-id-and-program-id")
    public ResponseEntity<ChildProgram> readByChildIdAndProgramId(
            @Valid
            @RequestParam("child_id")
            @NotNull(message = "child id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer childId,
            @Valid
            @RequestParam("program_id")
            @NotNull(message = "program id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer programId
    ){
        return childProgramService.readByChildIdAndProgramId(childId, programId);
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
            ChildProgram model
    ){
        return  childProgramService.update(id, model);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return childProgramService.delete(id);
    }
}
