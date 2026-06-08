package com.matawan.footballapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TeamResponseDto {

    private Long id;
    private String name;
    private String acronym;
    private BigDecimal budget;
    private List<PlayerDto> players;
}