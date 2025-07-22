package com.ravenson.billgenerator.administration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ravenson.billgenerator.SharedTools.models.Auditable;
import jakarta.persistence.*;
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
@Table(name = "kindergarten_bill")
@Audited
public class KindergartenBill extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "kindergarten id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("kindergarten_id")
    @Column(name = "kindergarten_id")
    private Integer kindergartenId;

    @NotNull(message = "bill id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("bill_id")
    @Column(name = "bill_id")
    private Integer billId;


    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "kindergarten_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Kindergarten kindergarten;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "bill_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Bill bill;
}
