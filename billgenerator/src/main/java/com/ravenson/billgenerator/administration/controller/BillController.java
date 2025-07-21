package com.ravenson.billgenerator.administration.controller;

import com.ravenson.billgenerator.administration.model.Bill;
import com.ravenson.billgenerator.administration.services.BillService;
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
@RequestMapping("/api/v1/bill")
public class BillController {

    private final BillService billService;
    //Pitaj za validaciju... ako imas ovde proveru za null, manje od 1 itd, jel treba u servisu...
    //Mislim, sta se prvo proverava (verovatno prvo kontroler, jer sa njim podatci prvo dolaze u kontakt.
    //Al pitaj za svaki slucaj
    //mada, kad malo bolje razmislim, ovo NotNull i nije za to, nego za ovaj kontroler... mada, onda ni tamo ne moze da bude null
    @PostMapping("/create")
    public ResponseEntity<String> create(
            @Valid
            @RequestBody
            @NotNull(message = "Model cen not be null")
            Bill bill
    ){
        //Proveri sa Micom da li ovde treba lista...
        // mislim, ne mogu da rekonstruisem logiku zasto sam stavio listu, a siguran sam da nisam samo "prepisivao"
        return billService.create(bill);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Bill>> read(){
        return billService.read();
    }

    @GetMapping("/read-by-id")
    public ResponseEntity<Bill> readById(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less then one")
            Integer id
    ){
        return billService.readById(id);
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
            Bill model
    ){
        return billService.update(id, model);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @Valid
            @RequestParam("id")
            @NotNull(message = "Id can not be null")
            @Min(value = 1, message = "Id can not be less the one")
            Integer id

    ){
        return billService.delete(id);
    }
}
