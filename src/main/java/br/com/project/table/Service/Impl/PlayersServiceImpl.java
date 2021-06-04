package br.com.project.table.Service.Impl;

import br.com.project.table.DTO.PlayerDTO;
import br.com.project.table.Entity.PlayerEntity;
import br.com.project.table.Repository.PlayerRepository;
import br.com.project.table.Service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlayersServiceImpl implements PlayersService {

    @Autowired
    private PlayerRepository repository;

    public List getFixedPlayers() {
        List<PlayerEntity> players = repository.findAll();

        Collections.sort(players);
        List<PlayerDTO> playersAll = orderRanking(players);
        showConsole(playersAll);

        return players;
    }

    public List postPlayer(PlayerEntity newPlayer) {
        List<PlayerEntity> players = repository.findAll();

        if(players.size() <= 7) {
            repository.save(newPlayer);
        } else {
            return null;
        }

        players = repository.findAll();

        Collections.sort(players);
        List<PlayerDTO> playersAll = orderRanking(players);
        showConsole(playersAll);

        return playersAll;
    }

    public ResponseEntity putPointsPlayer(long id, PlayerEntity playerData) {
        return repository.findById(id)
                .map(player -> {
                    player.setName(playerData.getName());
                    player.setPoints(player.getPoints() + playerData.getPoints());
                    repository.save(player);
                    return ResponseEntity.ok().body(getFixedPlayers());
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity deletePlayer(long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.deleteById(id);
                    getFixedPlayers();
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public void showConsole(List<PlayerDTO> players) {
        System.out.println("------------------------------------------------------");
        System.out.println("|   RANKING    |     NOME          |    PONTUAÇÃO    |");

        players.forEach(element -> {
            System.out.println("|      " +  element.getPosition() + "       |     " + element.getName() + "     |    " + element.getPoints() + "          |");
        });
    }

    public List<PlayerDTO> orderRanking(List<PlayerEntity> player) {
        int positionPlayer = 1;

        List<Integer> arrayPositions = new ArrayList<>();

        for(int x = 0; x < player.size(); x++) {
            if(x == player.size()-1) {
                if(player.get(x-1).getPoints() > player.get(x).getPoints()) {
                    arrayPositions.add(positionPlayer);

                } else if(player.get(x-1).getPoints() == player.get(x).getPoints()) {
                    arrayPositions.add(positionPlayer);
                }
            }

            if(x+1 < player.size()) {
                if(player.get(x).getPoints() > player.get(x+1).getPoints()) {
                    arrayPositions.add(positionPlayer);
                    positionPlayer++;
                } else if (player.get(x).getPoints() == player.get(x+1).getPoints()) {
                    arrayPositions.add(positionPlayer);
                }
            }
        }

        List<PlayerDTO> dataAll = new ArrayList<>();

        for(int i = 0; i < player.size(); i++) {
            PlayerDTO playerDTOS = new PlayerDTO(
                    arrayPositions.get(i),
                    player.get(i).getName(),
                    player.get(i).getPoints()
            );

            dataAll.add(playerDTOS);
        }


        return dataAll;
    }


}
