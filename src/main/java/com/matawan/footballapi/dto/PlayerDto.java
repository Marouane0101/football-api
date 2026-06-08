package com.matawan.footballapi.dto;

import com.matawan.footballapi.entity.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerDto {

    @NotBlank
    private String name;

    @NotNull
    private Position position;
}