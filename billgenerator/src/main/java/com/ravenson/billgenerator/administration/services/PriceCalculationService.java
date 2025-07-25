package com.ravenson.billgenerator.administration.services;

import com.ravenson.billgenerator.SharedTools.configuration.AppProperties;
import com.ravenson.billgenerator.SharedTools.exceptions.CustomException;
import com.ravenson.billgenerator.administration.model.Child;
import com.ravenson.billgenerator.administration.model.Kindergarten;
import com.ravenson.billgenerator.administration.repository.ChildProgramRepository;
import com.ravenson.billgenerator.administration.repository.ChildRepository;
import com.ravenson.billgenerator.administration.repository.KindergartenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class PriceCalculationService {

    private final AppProperties appProperties;
    private final ChildRepository childRepository;
    private final KindergartenRepository kindergartenRepository;
    private final ChildProgramRepository childProgramRepository;

    public BigDecimal calculatePrice(Integer childId) {

        int pdv = appProperties.getPdv();

        Child child = childRepository.findById(childId).orElse(null);
        if(child == null) {
            throw new CustomException("There is no child with id: " + childId);
        }

        Kindergarten kindergarten = kindergartenRepository.findById(child.getKindergartenId()).orElse(null);
        if(kindergarten == null) {
            throw new CustomException("There is no kindergarten with id: " + childId);
        }

        BigDecimal kindergartenPrice = BigDecimal.valueOf(kindergarten.getPrice());

        if(!childProgramRepository.existsByChildId(childId)) {
            throw new CustomException("There is no child with id: " + childId + " in ChildProgramRepository");
        }
        BigDecimal programsSum = BigDecimal.valueOf(childProgramRepository.sumAllProgramPricesByChildId(childId));

        BigDecimal discount = BigDecimal
                .valueOf(100.0 - child.getDiscount())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal siblingDiscount = calculateSiblingsDiscount(child.getSiblingPosition());



        return calculateTotalPrice(kindergartenPrice, programsSum, discount, siblingDiscount, pdv);

    }

    private BigDecimal calculateTotalPrice(BigDecimal kindergartenPrice, BigDecimal programsSum, BigDecimal discount, BigDecimal siblingDiscount, Integer pdv) {
        BigDecimal pdvMultiplier = BigDecimal
                .valueOf(100 + pdv)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return kindergartenPrice
                .add(programsSum)
                .multiply(discount)
                .multiply(siblingDiscount)
                .multiply(pdvMultiplier)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateSiblingsDiscount(Integer siblingPosition){
       int discountByPosition = 0;
        switch (siblingPosition) {
            case 2 -> discountByPosition += appProperties.getDiscountByChild();
            case 3 -> discountByPosition += appProperties.getDiscountByChild() * 2;
            case 4 -> discountByPosition += appProperties.getDiscountByChild() * 3;
            default -> {
                if(siblingPosition > 4) {
                    discountByPosition += appProperties.getDiscountByChild()*4;
                }
            }
        }

        return BigDecimal
                .valueOf(100 - discountByPosition)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

    }
}
