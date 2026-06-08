package com.matawan.footballapi.controller;

import com.matawan.footballapi.dto.TeamRequestDto;
import com.matawan.footballapi.dto.TeamResponseDto;
import com.matawan.footballapi.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto createTeam(@Valid @RequestBody TeamRequestDto request) {
        return teamService.createTeam(request);
    }

    @GetMapping
    public Page<TeamResponseDto> getTeams(Pageable pageable) {
        return teamService.getTeams(pageable);
    }
}