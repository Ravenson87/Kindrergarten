package com.ravenson.billgenerator.administration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ravenson.billgenerator.SharedTools.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@Table(name = "kindergarten")
@Audited
public class Kindergarten extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "name can not be null or empty")
    @NotBlank(message = "name name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "name can not be null or empty")
    @NotBlank(message = " name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("address")
    @Column(name = "address")
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("price")
    @Column(name = "price")
    private Double price;

    //TODO Zavrsi ovo sutra... dakle, dodaj kolonu, stavi da je not null (al prvo stavi da moze, jer imas podatke)
    // i stavi da je unique. Razmisli koji ce tip podatka da ti bude, jer je bankovni racun veliki (dakle ili BIG INT ili VARCHAR)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("account_number")
    @Column(name = "account_number")
    private String accountNumber;

    @NotNull(message = "pib can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("pib")
    @Column(name = "pib")
    private Integer pib;


}
