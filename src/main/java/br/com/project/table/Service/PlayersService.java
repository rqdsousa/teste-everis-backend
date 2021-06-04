package br.com.project.table.Service;

import br.com.project.table.Entity.PlayerEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayersService {

    List getFixedPlayers();

    List postPlayer(PlayerEntity newPlayer);

    ResponseEntity putPointsPlayer(long id, PlayerEntity playerData);

    ResponseEntity deletePlayer(long id);

}
