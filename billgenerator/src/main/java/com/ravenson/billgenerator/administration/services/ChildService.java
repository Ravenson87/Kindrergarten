package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.SharedTools.helpers.Helper;
import com.ravenson.billgenerator.administration.model.Child;
import com.ravenson.billgenerator.administration.repository.ChildRepository;
import com.ravenson.billgenerator.administration.repository.KindergartenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChildService {
    private final ChildRepository childRepository;
    private final KindergartenRepository kindergartenRepository;

    /**
     * Create child in database
     * @param child Child
     * @return ResponseEntity<String>
     */
    //Vidi komentar u klasi child
    public ResponseEntity<String> create(Child child) {
        if(child == null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        if(!kindergartenRepository.existsById(child.getKindergartenId())) {
            throw new CustomException("Kindergarten with id " + child.getKindergartenId() + " does not exist");
        }
        try{
        childRepository.save(child);
        return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(CustomException e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Return all Children from database
     * @return ResponseEntity<List<Child>>
     */
    public ResponseEntity<List<Child>> read(){
        List<Child> result = Helper.ListConverter(childRepository.findAll());
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Read Child by id from database
     * @param id Integer
     * @return ResponseEntity<Child>
     */
    public ResponseEntity<Child> readById(Integer id) {
        if(id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Child result = childRepository.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Update Child in database
     * @param childId Integer
     * @param child Child
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> update(Integer childId, Child child) {
        if(childId == null || childId <= 0) {
            throw new CustomException("Child with id " + childId + " does not exist");
        }
        if(!childRepository.existsById(childId)) {
            throw new CustomException("Child with id " + childId + " does not exist");
        }

        if(!kindergartenRepository.existsById(child.getKindergartenId())) {
            throw new CustomException("Kindergarten with id " + child.getKindergartenId() + " does not exist");
        }
        try{
            child.setId(childId);
            childRepository.save(child);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        }catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Delete Child from database
     * @param id Integer
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Integer id) {
        if(id == null || id <= 0) {
            throw new CustomException("Child with id " + id + " does not exist");
        }
        try {
            childRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
