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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@Table(name = "user")
public class User extends Auditable implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "kindergarten name can not be empty")
    @NotBlank(message = "kindergarten name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("kindergarten_name")
    @Column(name = "kindergarten_name")
    private String kindergartenName;

    @NotEmpty(message = "username can not be empty")
    @NotBlank(message = "kindergarten name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("username")
    @Column(name = "username")
    private String username;

    //U Gaia Leave ovde nema not empty and not null
    @NotEmpty(message = "kindergarten name can not be empty")
    @NotBlank(message = "kindergarten name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("password")
    @Column(name = "password")
    @Size(min = 8, message = "Incorrect password")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Incorrect password"
    )
    private String password;

    //U Gaia Leave ovde nema not empty and not null
    @NotEmpty(message = "email name can not be empty")
    @NotBlank(message = "email name can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    //U Gaia Leave ovde nema Json Property-ja
    @JsonProperty("email")
    @Column(name = "email")
    @Email(message = "Invalid email format")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @JsonProperty("status")
    @Column(name = "status")
    private Boolean status;

}
