package com.matawan.footballapi.service;

import com.matawan.footballapi.dto.PlayerDto;
import com.matawan.footballapi.dto.TeamRequestDto;
import com.matawan.footballapi.dto.TeamResponseDto;
import com.matawan.footballapi.entity.Player;
import com.matawan.footballapi.entity.Team;
import com.matawan.footballapi.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public TeamResponseDto createTeam(TeamRequestDto request) {
        log.info("Creating team with name={}, acronym={}", request.getName(), request.getAcronym());

        Team team = Team.builder()
                .name(request.getName())
                .acronym(request.getAcronym())
                .budget(request.getBudget())
                .players(request.getPlayers().stream()
                        .map(this::toPlayerEntity)
                        .toList())
                .build();

        Team savedTeam = teamRepository.save(team);
        return toResponseDto(savedTeam);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TeamResponseDto> getTeams(Pageable pageable) {
        log.info("Fetching teams with pagination={}", pageable);
        return teamRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    private Player toPlayerEntity(PlayerDto dto) {
        return Player.builder()
                .name(dto.getName())
                .position(dto.getPosition())
                .build();
    }

    private TeamResponseDto toResponseDto(Team team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setAcronym(team.getAcronym());
        dto.setBudget(team.getBudget());
        dto.setPlayers(team.getPlayers().stream()
                .map(this::toPlayerDto)
                .toList());
        return dto;
    }

    private PlayerDto toPlayerDto(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setName(player.getName());
        dto.setPosition(player.getPosition());
        return dto;
    }
}