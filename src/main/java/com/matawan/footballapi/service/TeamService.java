package com.matawan.footballapi.service;

import com.matawan.footballapi.dto.TeamRequestDto;
import com.matawan.footballapi.dto.TeamResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {

    TeamResponseDto createTeam(TeamRequestDto request);

    Page<TeamResponseDto> getTeams(Pageable pageable);
}