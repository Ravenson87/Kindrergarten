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
@Table(name = "child_program")
@Audited
public class ChildProgram extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "child id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("child_id")
    @Column(name = "child_id")
    private Integer childId;

    @NotNull(message = "program id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("program_id")
    @Column(name = "program_id")
    private Integer programId;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "child_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Child child;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "program_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Programs program;
}
