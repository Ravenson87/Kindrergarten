package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.SharedTools.helpers.Helper;
import com.ravenson.billgenerator.administration.model.Programs;
import com.ravenson.billgenerator.administration.repository.ProgramsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramsService {

    private final ProgramsRepository programsRepository;

    /**
     * Create Program in database
     * @param model Programs
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> create(Programs model){
        if(model == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Programs check = programsRepository.findByName(model.getName()).orElse(null);
        if(check != null) {
            throw new CustomException("Program with name " + model.getName() + " already exists");
        }

        try{
            programsRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Read all Programs from database
     * @return ResponseEntity<List<Programs>>
     */
    public ResponseEntity<List<Programs>> read(){
        List<Programs> result = Helper.ListConverter(programsRepository.findAll());
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Read Program from database by id
     * @param id Integer
     * @return ResponseEntity<Programs>
     */
    public ResponseEntity<Programs> readById(Integer id){
        if(id==null || id <= 0) {
            return ResponseEntity.noContent().build();
        }
        Programs result = programsRepository.findById(id).orElse(null);
        return result == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Read Program from database by name
     * @param name String
     * @return ResponseEntity<Programs>
     */
    public ResponseEntity<Programs> readByName(String name){
        if(name==null|| name.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Programs result = programsRepository.findByName(name).orElse(null);
        return result == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Update Program in database by id
     * @param programsId Integer
     * @param model Programs
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> update(Integer programsId, Programs model){
        //Mozda ovako staviti svuda - da pise ovo u CustomException-u... razmisliti o tome
        if(programsId==null || programsId <= 0) {
            throw new CustomException("Programs id is null or less than zero");
        }
        if(!programsRepository.existsById(programsId)) {
            throw new CustomException("Program with id " + programsId + " does not exist");
        }
        Programs check = programsRepository.findByName(model.getName()).orElse(null);
        if(check != null && !check.getId().equals(programsId)) {
            throw new CustomException("Program with name " + model.getName() + " already exists");
        }
        try{
            model.setId(programsId);
            programsRepository.save(model);
            return  ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Delete Program from database by id
     * @param programsId Integer
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Integer programsId){
        if(programsId==null || programsId <= 0) {
            throw new CustomException("Programs id is null or less than zero");
        }
        try{
            programsRepository.deleteById(programsId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
