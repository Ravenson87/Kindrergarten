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
@Table(name = "bill")
@Audited
public class Bill extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "year can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("year")
    @Column(name = "year")
    private Integer year;

    @NotEmpty(message = "month can not be null or empty")
    @NotBlank(message = "month name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("month")
    @Column(name = "month")
    private String month;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("deadline")
    @Column(name = "deadline")
    private String deadline;

    @NotNull(message = "kindergarten id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("kindergarten_id")
    @Column(name = "kindergarten_id")
    private Integer kindergartenId;

    @NotNull(message = "child id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("child_id")
    @Column(name = "child_id")
    private Integer childId;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    // Kaze chatGPT da je pri OneToMany bolje koristiti LAZY
    // Isto tako kaze da se onda lista ucitava samo kad se pozove,
    // ali onda metoda koja je koristi mora biti TRANSACTIONAL
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "kindergarten_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Kindergarten kindergarten;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "child_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Child child;
}
