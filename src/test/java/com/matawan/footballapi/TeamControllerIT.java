package com.matawan.footballapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matawan.footballapi.dto.PlayerDto;
import com.matawan.footballapi.dto.TeamRequestDto;
import com.matawan.footballapi.entity.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTeamWithPlayers() throws Exception {
        PlayerDto player = new PlayerDto();
        player.setName("Jean-Clair Todibo");
        player.setPosition(Position.DEFENDER);

        TeamRequestDto request = new TeamRequestDto();
        request.setName("OGC Nice");
        request.setAcronym("OGCN");
        request.setBudget(BigDecimal.valueOf(120_000_000));
        request.setPlayers(List.of(player));

        mockMvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("OGC Nice"))
                .andExpect(jsonPath("$.acronym").value("OGCN"))
                .andExpect(jsonPath("$.players", hasSize(1)))
                .andExpect(jsonPath("$.players[0].position").value("DEFENDER"));
    }

    @Test
    void shouldGetPaginatedTeams() throws Exception {
        mockMvc.perform(get("/api/teams")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "name,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void shouldReturnBadRequestWhenTeamNameIsMissing() throws Exception {
        TeamRequestDto request = new TeamRequestDto();
        request.setAcronym("OGCN");
        request.setBudget(BigDecimal.valueOf(120_000_000));
        request.setPlayers(List.of());

        mockMvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").exists());
    }
}