package com.ravenson.billgenerator.administration.services;


import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.SharedTools.helpers.Helper;
import com.ravenson.billgenerator.administration.model.ChildProgram;
import com.ravenson.billgenerator.administration.repository.ChildProgramRepository;
import com.ravenson.billgenerator.administration.repository.ChildRepository;
import com.ravenson.billgenerator.administration.repository.ProgramsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ChildProgramService {

    private final ChildProgramRepository childProgramRepository;
    private final ChildRepository childRepository;
    private final ProgramsRepository programsRepository;

    /**
     * Create ChildProgram in database
     * @param model ChildProgram
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> create(ChildProgram model) {
        if(model == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Proveri sa Milicom da li si ovo dobro uradio...
        ChildProgram check = childProgramRepository.findByChildIdAndProgramId(model.getChildId(), model.getProgramId())
                .orElse(null);
        if(check != null) {
            throw new CustomException("Child program already exists");
        }

        if(!childRepository.existsById(model.getChildId())) {
            throw new CustomException("Child with id " + model.getChildId() + " does not exist");
        }

        if(!programsRepository.existsById(model.getProgramId())) {
            throw new CustomException("Program with id " + model.getProgramId() + " does not exist");
        }

        try {
            childProgramRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(CustomException c) {
            throw new CustomException(c.getMessage());
        }

    }

    /**
     * Return all ChildPrograms from database
     * @return ResponseEntity<List<ChildProgram>>
     */
    public ResponseEntity<List<ChildProgram>> read() {
        List<ChildProgram> result = Helper.ListConverter(childProgramRepository.findAll());
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Return ChildProgram from database
     * @param id Integer
     * @return ResponseEntity<ChildProgram>
     */
    public ResponseEntity<ChildProgram> readById(Integer id) {
        if(id == null || id <= 0) {
            return ResponseEntity.noContent().build();
        }
        ChildProgram result = childProgramRepository.findById(id).orElse(null);
        return result == null ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     *
     * @param childId Integer
     * @param programId Integer
     * @return ResponseEntity<ChildProgram>
     */
    public ResponseEntity<ChildProgram> readByChildIdAndProgramId(Integer childId, Integer programId) {
//        //Pitaj za ovo, ali verovatno ti ne treba jer proveravas u kontroleru
//        if(childId == null || programId == 0) {
//            return ResponseEntity.noContent().build();
//        }
        ChildProgram result = childProgramRepository.findByChildIdAndProgramId(childId, programId).orElse(null);
        if(result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    /**
     * Update ChildProgram in database
     * @param childProgramId Integer
     * @param model ChildProgram
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> update(Integer childProgramId, ChildProgram model) {
        if(childProgramId == null || childProgramId <= 0) {
            throw new CustomException("Child with id " + childProgramId + " does not exist");
        }
        // Posavetovati se sa Milicom da li moram i id i unique constraint da proveravam
        if(!childProgramRepository.existsById(childProgramId)) {
            throw new CustomException("Child program with child id" + childProgramId + " does not exist");
        }
        ChildProgram check = childProgramRepository.findByChildIdAndProgramId(model.getChildId(), model.getProgramId()).orElse(null);
        //Pitaj za ovaj uslov obavezno
        if(check != null && !check.getId().equals(childProgramId)) {
            throw new CustomException("Child program with child id" + model.getChildId() + "and program id" + model.getProgramId() + " already exists");
        }

        if(!childRepository.existsById(model.getChildId())) {
            throw new CustomException("Child with id " + model.getChildId() + " does not exist");
        }

        if(!programsRepository.existsById(model.getProgramId())) {
            throw new CustomException("Program with id " + model.getProgramId() + " does not exist");
        }
        try{
            model.setId(childProgramId);
            childProgramRepository.save(model);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        }catch(CustomException c) {
            throw new CustomException(c.getMessage());
        }
    }

    /**
     * Delete ChildProgram from database
     * @param id Integer
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Integer id) {
        if(id == null || id <= 0) {
            throw new CustomException("ChildProgram with id " + id + " does not exist");
        }
        try{
            childProgramRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }catch(CustomException c) {
            throw new CustomException(c.getMessage());
        }
    }
}
