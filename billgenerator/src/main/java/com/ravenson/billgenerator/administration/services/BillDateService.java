package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.administration.model.Child;
import com.ravenson.billgenerator.administration.model.Kindergarten;
import com.ravenson.billgenerator.administration.repository.ChildRepository;
import com.ravenson.billgenerator.administration.repository.KindergartenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BillDateService {

    private final KindergartenRepository kindergartenRepository;
    private final KindergartenService kindergartenService;
    private final ChildRepository childRepository;
    private final ChildService childService;

    /**
     * Find kindergarten account number by child id
     * @param childId Integer
     * @return String
     */
    public String getKindergartenAccountNumber(Integer childId){
    Child child = checkAndFindChildById(childId);

        Kindergarten kindergarten = kindergartenRepository.findById(child.getKindergartenId()).orElse(null);
        if(kindergarten == null){
            throw new CustomException("kindergarten not found");
        }
        return kindergarten.getAccountNumber();
    }

    /**
     * Get sender name (parent name) by child id
     * @param childId Integer
     * @return String
     */
    public String getSenderFullName(Integer childId){
       Child child = checkAndFindChildById(childId);
       return child.getFullName();

    }

    /**
     * Get sender (parent) address by child id
     * @param childId Integer
     * @return String
     */
    public String getSenderAddress(Integer childId){
        Child child = checkAndFindChildById(childId);
        return child.getParentAddress();
    }

    //TODO napravi BillCode srvis...

    /**
     * Return kindergarten name from child id
     * @param childId Integer
     * @return String
     */
    //Razmisli da li je bolje ovako, ili da se pravi "JOIN QUERY"
    public String getKindergartenName(Integer childId){
        Child child = checkAndFindChildById(childId);
        Kindergarten kindergarten =  kindergartenRepository.findById(child.getKindergartenId()).orElseThrow(() -> new CustomException("kindergarten not found"));
        return kindergarten.getName();

    }

    /**
     * Return kindergarten address from child id
     * @param childId Integer
     * @return String
     */
    public String getKindergartenAddress(Integer childId){
        Child child = checkAndFindChildById(childId);
        Kindergarten kindergarten = kindergartenRepository.findById(child.getKindergartenId()).orElseThrow(() -> new CustomException("kindergarten not found"));
        return kindergarten.getAddress();
    }

    //TODO napravi servise vezane za datume

    /**
     * Return kindergarten pib from child id
     * @param childId Integer
     * @return Integer
     */
    public Integer getKindergartenPib(Integer childId){
        Child child = checkAndFindChildById(childId);
        Kindergarten kindergarten = kindergartenRepository.findById(child.getKindergartenId()).orElseThrow(() -> new CustomException("kindergarten not found"));
        return kindergarten.getPib();
    }

    /**
     * Return kindergarten phone from child id
     * @param childId Integer
     * @return String
     */
    public String getKindergartenPhone(Integer childId){
        Child child = checkAndFindChildById(childId);
        Kindergarten kindergarten = kindergartenRepository.findById(child.getKindergartenId()).orElseThrow(() -> new CustomException("kindergarten not found"));
        return kindergarten.getKindergartenPhone();
    }



    //Pitaj da li je bolje da ovo stoji ovde ili da ga stavim u Helpere
    private Child checkAndFindChildById(Integer childId){
        if(childId == null){
            throw new CustomException("childId can not be null");
        }
        Optional<Child> child = childRepository.findById(childId);
        if(child.isEmpty()){
            throw new CustomException("child not found");
        }
        return child.get();
    }
}

