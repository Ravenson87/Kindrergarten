package com.ravenson.billgenerator.administration.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ravenson.billgenerator.SharedTools.configuration.AppProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class BillData {

    private final AppProperties appProperties;

    @NotEmpty(message = "kindergarten account number can not be null or empty")
    @NotBlank(message = "kindergarten account number can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("kindergarten_account_number")
    private String kindergartenAccountNumber;

    @NotEmpty(message = "sender full name can not be null or empty")
    @NotBlank(message = "sender full name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("sender_full_name")
    private String senderFullName;

    @NotEmpty(message = "sender address can not be null or empty")
    @NotBlank(message = "sender address can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("sender_address")
    private String senderAddress;

    @NotEmpty(message = "bill code can not be null or empty")
    @NotBlank(message = "bill code name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("bill_code")
    private String billCode;

    @NotEmpty(message = "kindergarten name can not be null or empty")
    @NotBlank(message = "kindergarten name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("kindergarten_name")
    private String kindergartenName;

    @NotEmpty(message = "kindergarten address can not be null or empty")
    @NotBlank(message = "kindergarten address can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("kindergarten_address")
    private String kindergartenAddress;

    //TODO napravi servis, ali verovatno da generise datum kada se posalje
    @NotNull(message = "issue date  can not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("issue_date")
    private LocalDate issueDate;

    //TODO vidi da li je ovo datum ili period
    @NotNull(message = "transaction date can not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("transaction_date")
    private Period transactionDate;

    @NotNull(message = "payment deadline can not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("payment_deadline")
    private LocalDate paymentDeadline;

    @NotNull(message = "kindergarten pib can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("kindergarten_pib")
    private Integer kindergartenPib;

    @NotEmpty(message = "kindergarten phone can not be null or empty")
    @NotBlank(message = "kindergarten phone can not be null or empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("kindergarten_phone")
    private String kindergartenPhone;

    //TODO ovo je na onom papiru "maticni broj" valjda vrtica, ali nemam pojma sta je to...
    // istraziti i videti da li je to uopste konstanta
    @NotNull(message = "serial number can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("serial_number")
    private Integer serialNumber;
}
