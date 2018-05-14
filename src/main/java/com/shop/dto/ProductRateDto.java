package com.shop.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class ProductRateDto {

    public ProductRateDto(@NotNull @Range(min = 1, max = 5) int mark, @Length(max = 255) String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    @NotNull
    @Range(min = 1, max = 5)
    private int mark;

    @Length(max = 255)
    private String comment;
}
