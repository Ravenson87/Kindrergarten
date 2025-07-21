package com.ravenson.billgenerator.administration.controller;

import com.ravenson.billgenerator.administration.model.KindergartenBill;
import com.ravenson.billgenerator.administration.services.KindergartenBillService;
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
@RequestMapping("/api/v1/kindergarten-bill")
public class KindergartenBillController {

    private final KindergartenBillService kindergartenBillService;

    @PostMapping("/create")
    public ResponseEntity<String> create(
            @Valid
            @RequestBody
            @NotNull(message = "Model can not be null")
            KindergartenBill model
    ){
        return kindergartenBillService.create(model);
    }

    @GetMapping("/read")
    public ResponseEntity<List<KindergartenBill>> read(){
        return kindergartenBillService.read();
    }
    @GetMapping("/read-by-id")
    public ResponseEntity<KindergartenBill> readById(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return kindergartenBillService.readById(id);
    }
    @GetMapping("/read-by-bill-id")
    public ResponseEntity<KindergartenBill> readByBillId(
            @Valid
            @RequestParam("bill_id")
            @NotNull(message = "Bill id can not be null")
            @Min(value = 1, message = "Bill id can not be less then one")
            Integer billId
    ) {
        return kindergartenBillService.readByBillId(billId);
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
            KindergartenBill model
    ){
        return kindergartenBillService.update(id, model);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return kindergartenBillService.delete(id);
    }

}
