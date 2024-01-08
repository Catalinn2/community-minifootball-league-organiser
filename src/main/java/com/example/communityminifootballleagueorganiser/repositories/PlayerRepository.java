package com.example.communityminifootballleagueorganiser.repositories;

import com.example.communityminifootballleagueorganiser.models.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
