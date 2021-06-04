package br.com.project.table.Controller;

import br.com.project.table.Entity.PlayerEntity;
import br.com.project.table.Service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class PlayersController {

    @Autowired
    PlayersService playersService;

    @GetMapping({"/fixed-players"})
    public ResponseEntity<List> getFixedPlayers() {
        return ResponseEntity.status(HttpStatus.OK).body(playersService.getFixedPlayers());
    }

    @PostMapping({"/new-player"})
    public ResponseEntity postPlayer(@RequestBody PlayerEntity newPlayer) {
        List player = playersService.postPlayer(newPlayer);

        if(player != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(player);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity putPointsPlayer(
            @PathVariable("id") long id,
            @RequestBody PlayerEntity playerData
    ) {
        return playersService.putPointsPlayer(id, playerData);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deletePlayer(@PathVariable long id) {
        return playersService.deletePlayer(id);
    }

}
