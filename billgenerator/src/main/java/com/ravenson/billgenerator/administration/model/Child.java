package com.ravenson.billgenerator.administration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ravenson.billgenerator.SharedTools.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "child")
//Razmisli sta bi u bazi trebalo da bude unique, da ne bi mogla da se unesu 2 potpuno ista deteta!!!
@Audited
public class Child extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "full name can not be null or empty")
    @NotBlank(message = "full name name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("full_name")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "parent name can not be null empty")
    @NotBlank(message = "parent name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("parent_name")
    @Column(name = "parent_name")
    private String parentName;

    @NotEmpty(message = "group name can not be null or empty")
    @NotBlank(message = "group name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("group_name")
    @Column(name = "group_name")
    private String groupName;

    @NotNull(message = "kindergarten id can not be null")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("kindergarten_id")
    @Column(name = "kindergarten_id")
    private Integer kindergartenId;

    @NotEmpty(message = "parent email can not be null or empty")
    @NotBlank(message = "parent can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("parent_email")
    @Column(name = "parent_email")
    @Email(message = "Invalid email format")
    private String parentEmail;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("discount")
    @Column(name = "discount")
    @Min(value = 0, message = "Discount can not be lesser then 0")
    private Integer discount = 0;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("sibling_position")
    @Column(name = "sibling_position")
    @Min(value = 1, message = "minimum sibling position can not be lesser then 1")
    @Max(value = 10, message = "maximum sibling position is 10")
    private Integer siblingPosition = 1;

    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @JsonProperty("status")
    @Column(name = "status")
    private Boolean status = true;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "kindergarten_id", referencedColumnName = "id",
            insertable = false, updatable = false
    )
    private Kindergarten kindergarten;
}
