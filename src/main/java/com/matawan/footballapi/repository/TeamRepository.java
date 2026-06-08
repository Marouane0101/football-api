package com.matawan.footballapi.repository;

import com.matawan.footballapi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}