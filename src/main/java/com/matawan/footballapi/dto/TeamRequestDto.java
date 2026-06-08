package com.matawan.footballapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class TeamRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String acronym;

    @NotNull
    @Positive
    private BigDecimal budget;

    @Valid
    private List<PlayerDto> players = new ArrayList<>();
}