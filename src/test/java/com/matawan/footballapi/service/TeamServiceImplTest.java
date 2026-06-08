package com.matawan.footballapi.service;

import com.matawan.footballapi.dto.PlayerDto;
import com.matawan.footballapi.dto.TeamRequestDto;
import com.matawan.footballapi.dto.TeamResponseDto;
import com.matawan.footballapi.entity.Position;
import com.matawan.footballapi.entity.Team;
import com.matawan.footballapi.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void shouldCreateTeamWithPlayers() {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("Jean-Clair Todibo");
        playerDto.setPosition(Position.DEFENDER);

        TeamRequestDto request = new TeamRequestDto();
        request.setName("OGC Nice");
        request.setAcronym("OGCN");
        request.setBudget(BigDecimal.valueOf(120_000_000));
        request.setPlayers(List.of(playerDto));

        when(teamRepository.save(any(Team.class)))
                .thenAnswer(invocation -> {
                    Team team = invocation.getArgument(0);
                    team.setId(1L);
                    return team;
                });

        TeamResponseDto response = teamService.createTeam(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("OGC Nice");
        assertThat(response.getAcronym()).isEqualTo("OGCN");
        assertThat(response.getBudget()).isEqualByComparingTo(BigDecimal.valueOf(120_000_000));
        assertThat(response.getPlayers()).hasSize(1);
        assertThat(response.getPlayers().get(0).getName()).isEqualTo("Jean-Clair Todibo");
        assertThat(response.getPlayers().get(0).getPosition()).isEqualTo(Position.DEFENDER);

        ArgumentCaptor<Team> teamCaptor = ArgumentCaptor.forClass(Team.class);
        verify(teamRepository).save(teamCaptor.capture());

        Team savedTeam = teamCaptor.getValue();
        assertThat(savedTeam.getName()).isEqualTo("OGC Nice");
        assertThat(savedTeam.getPlayers()).hasSize(1);

        verifyNoMoreInteractions(teamRepository);
    }
}