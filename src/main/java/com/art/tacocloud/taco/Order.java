package com.art.tacocloud.taco;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Order {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Name is required")
    private String street;

    @NotBlank(message = "Name is required")
    private String city;

    @NotBlank(message = "Name is required")
    private String state;

    @NotBlank(message = "Name is required")
    private String zip;

    @CreditCardNumber(message = "Not valid cc")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Long id;
    private Date placedAt;
}
