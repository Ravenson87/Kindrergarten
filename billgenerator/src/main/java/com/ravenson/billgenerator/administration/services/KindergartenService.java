package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.SharedTools.helpers.Helper;
import com.ravenson.billgenerator.administration.model.Kindergarten;
import com.ravenson.billgenerator.administration.repository.KindergartenRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KindergartenService {

    private final KindergartenRepository kindergartenRepository;

    /**
     * Create Kindergarten in database
     * @param model Kindergarten
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> create(Kindergarten model) {
        if(model == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Kindergarten check = kindergartenRepository.findByPib(model.getPib()).orElse(null);
        if(check != null) {
            throw new EntityExistsException("Kindergarten with pib" + model.getPib() + "already exists");
        }
        try{
            kindergartenRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(CustomException e){
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Read all Kindergartens from database
     * @return ResponseEntity<List<Kindergarten>>
     */
    public ResponseEntity<List<Kindergarten>> read() {
        List<Kindergarten> result = Helper.ListConverter(kindergartenRepository.findAll());
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Read Kindergarten by id from database
     * @param id Integer
     * @return ResponseEntity<Kindergarten>
     */
    public ResponseEntity<Kindergarten> readById(Integer id) {
        if(id == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Kindergarten result = kindergartenRepository.findById(id).orElse(null);
        return result == null ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Read Kindergarten by pib from database
     * @param pib Integer
     * @return ResponseEntity<Kindergarten>
     */
    public ResponseEntity<Kindergarten> readByPib(Integer pib) {
        if(pib == null || pib <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Kindergarten result = kindergartenRepository.findByPib(pib).orElse(null);
        return result == null ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Update Kindergarten in database
     * @param kindergartenId Integer
     * @param model Kindergarten
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> update(Integer kindergartenId, Kindergarten model) {
        if(kindergartenId == null || kindergartenId <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //TODO Ispraviti ovo po ugledu na BillService, mislim na "findbyid()"
        if(!kindergartenRepository.existsById(kindergartenId)) {
            throw new CustomException("Kindergarten with id " + kindergartenId + " does not exist");
        }
        Kindergarten checkKindergartenPib = kindergartenRepository.findByPib(model.getPib()).orElse(null);

        if(checkKindergartenPib != null && !checkKindergartenPib.getId().equals(kindergartenId)) {
        throw new CustomException("Kindergarten with pib " + model.getPib() + " already exists");
        }

        try{
            model.setId(kindergartenId);
            kindergartenRepository.save(model);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        }catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Delete Kindergarten in database
     * @param kindergartenId Integer
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Integer kindergartenId) {
        if(kindergartenId == null || kindergartenId <= 0) {
            throw new CustomException("Kindergarten id " + kindergartenId + " does not exist");
        }
        try{
            kindergartenRepository.deleteById(kindergartenId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }
    }


}
