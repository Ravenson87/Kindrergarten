package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.SharedTools.helpers.Helper;
import com.ravenson.billgenerator.administration.model.KindergartenBill;
import com.ravenson.billgenerator.administration.repository.BillRepository;
import com.ravenson.billgenerator.administration.repository.KindergartenBillRepository;
import com.ravenson.billgenerator.administration.repository.KindergartenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KindergartenBillService {

    private final KindergartenBillRepository kindergartenBillRepository;
    private final KindergartenRepository kindergartenRepository;
    private final BillRepository billRepository;

    /**
     * Create KindergartenBill in database
     * @param model KindergartenBill
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> create(KindergartenBill model) {
        if(model==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if(!kindergartenRepository.existsById(model.getKindergartenId())) {
            throw new CustomException("Kindergarten with id " + model.getId() + " does not exist");
        }
        if(!billRepository.existsById(model.getBillId())) {
            throw new CustomException("Bill with id " + model.getBillId() + " does not exists");
        }
        KindergartenBill check = kindergartenBillRepository.findByBillId(model.getBillId()).orElse(null);
        if(check!=null) {
            throw new CustomException("Kindergarten bill with " + model.getBillId() + " already exists");
        }
        try{
            kindergartenBillRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(CustomException e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Read all KindergartenBills from database
     * @return ResponseEntity<List<KindergartenBill>>
     */
    public ResponseEntity<List<KindergartenBill>> read() {
        List<KindergartenBill> result = Helper.ListConverter(kindergartenBillRepository.findAll());
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Return KindergartenBill from database
     * @param id Integer
     * @return ResponseEntity<KindergartenBill>
     */
    public ResponseEntity<KindergartenBill> readById(Integer id) {
        if(id==null || id <= 0) {
            return ResponseEntity.noContent().build();
        }
        KindergartenBill result = kindergartenBillRepository.findById(id).orElse(null);
        return result == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    /**
     * Read KindergartenBill bu bill id
     * @param billId Integer
     * @return ResponseEntity<KindergartenBill>
     */
    public ResponseEntity<KindergartenBill> readByBillId(Integer billId) {
        if(billId==null || billId <= 0) {
            return ResponseEntity.noContent().build();
        }
        KindergartenBill check = kindergartenBillRepository.findByBillId(billId).orElse(null);
        return check == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(check);
    }

    /**
     * Update KindergartenBill in database
     * @param kindergartenBillId Integer
     * @param model KindergartenBill
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> update(Integer kindergartenBillId, KindergartenBill model) {
        if(kindergartenBillId == null || kindergartenBillId <= 0) {
            throw new CustomException("Kindergarten bill with id " + kindergartenBillId + " does not exist");
        }
       if(!kindergartenBillRepository.existsById(kindergartenBillId)) {
           throw new CustomException("Kindergarten bill with id " + kindergartenBillId + " does not exist");
       }

        if(!kindergartenRepository.existsById(model.getKindergartenId())) {
            throw new CustomException("Kindergarten with id " + model.getId() + " does not exist");
        }
        if(!billRepository.existsById(model.getBillId())) {
            throw new CustomException("Bill with id " + model.getBillId() + " does not exists");
        }

       KindergartenBill check = kindergartenBillRepository.findByBillId(kindergartenBillId).orElse(null);
        if(check != null && !check.getId().equals(kindergartenBillId)) {
            throw new CustomException("Child program with bill id " + model.getBillId() + " already exists");
        }
       try{
           model.setId(kindergartenBillId);
           kindergartenBillRepository.save(model);
           return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
       }catch (CustomException e) {
           throw new CustomException(e.getMessage());
       }
    }

    /**
     * Delete KindergartenBill from database
     * @param kindergartenBillId Integer
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Integer kindergartenBillId) {
        if(kindergartenBillId==null || kindergartenBillId <= 0) {
            throw new CustomException("KindergartenBill with id " + kindergartenBillId + " does not exist");
        }
        try{
            kindergartenBillRepository.deleteById(kindergartenBillId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
