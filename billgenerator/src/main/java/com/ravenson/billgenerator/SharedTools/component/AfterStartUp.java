package com.ravenson.billgenerator.SharedTools.component;

import com.ravenson.billgenerator.administration.repository.ChildProgramRepository;
import com.ravenson.billgenerator.administration.services.PriceCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AfterStartUp {

    private final ChildProgramRepository childProgramRepository;
    private final PriceCalculationService priceCalculationService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartUp() {
//        System.out.println(priceCalculationService.calculatePrice(2));
//        System.out.println(childProgramRepository.sumAllProgramPricesByChildId(2));

//                String random = RandomStringUtils.secure().nextAlphabetic(1);
//                System.out.println(random);}

        System.out.println(LocalDate.now());
    }
}

