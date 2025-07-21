package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.SharedTools.helpers.Helper;
import com.ravenson.billgenerator.administration.model.Bill;
import com.ravenson.billgenerator.administration.repository.BillRepository;
import com.ravenson.billgenerator.administration.repository.ChildRepository;
import com.ravenson.billgenerator.administration.repository.KindergartenRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BillService {

    private final BillRepository billRepository;
    private final KindergartenRepository kindergartenRepository;
    private final ChildRepository childRepository;

    /**
     * Create Bills in database
     * @param model Bill
     * @return ResponseEntity<String>
     */

    //Proveri ovaj problem, ako vec postoji model koji hoces da kreiras...
    //Mozda bi Child_id i Kindergarten_id morali da budu unique
    // Razmisli da li treba lista, odnosno kad treba lista a kada ne...
    public ResponseEntity<String> create(Bill model) {
        if (model == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if(!kindergartenRepository.existsById(model.getKindergartenId())) {
            throw new CustomException("Kindergarten with id " + model.getKindergartenId() + " does not exist");
        }
        if(!childRepository.existsById(model.getChildId())) {
            throw new CustomException("Child with id " + model.getChildId() + " does not exist");
        }

        try {
            billRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Read all Bills in database
     * @return ResponseEntity<List<Bill>>
     */
    public ResponseEntity<List<Bill>> read() {
        List<Bill> bills = Helper.ListConverter(billRepository.findAll());
        return bills.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(bills);
    }

    /**
     * Read Bill by id
     * @param billId Integer
     * @return ResponseEntity<Bill>
     */
    public ResponseEntity<Bill> readById(Integer billId) {
        if (billId == null || billId <= 0) {
            return ResponseEntity.noContent().build();
        }
        Bill result = billRepository.findById(billId).orElse(null);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Obavezno pitaj sta je tacno body, i zasto u njemu vracamo bill (verovatno u header-u vracamo "ok")
        // Takodje pitaj ima li razlike izmedju ovoga i onoga sto je u childService-u
        return ResponseEntity.ok().body(result);
    }

    /**
     * Update Bill in database
     * @param billId Integer
     * @param model Bill
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> update(Integer billId, Bill model) {
        //Mislim da mi ovo "manje od nule" ne treba zbog validacije na kontroleru
        if(billId == null || billId <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (!billRepository.existsById(billId)) {
            throw new CustomException("Bill with id " + billId + " does not exist");
        }

        if(!kindergartenRepository.existsById(model.getKindergartenId())) {
            throw new CustomException("Kindergarten with id " + model.getKindergartenId() + " does not exist");
        }
        if(!childRepository.existsById(model.getChildId())) {
            throw new CustomException("Child with id " + model.getChildId() + " does not exist");
        }
        try{
            model.setId(billId);
            billRepository.save(model);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        }catch(CustomException e){
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Delete Bill from database
     * @param billId Integer
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Integer billId) {
        if (!billRepository.existsById(billId)) {
            throw new CustomException("Bill with id " + billId + " does not exist");
        }
        try{
            billRepository.deleteById(billId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }catch(CustomException e){
            throw new CustomException(e.getMessage());
        }
    }


}
