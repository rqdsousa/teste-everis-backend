package br.com.project.table.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players")
public class PlayerEntity implements Serializable, Comparable<PlayerEntity> {

    private static final long serialVersionUID = 3960436649365666213L;

    private Long id;
    private String name;
    private Integer points;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public PlayerEntity() {

    }

    public PlayerEntity(Long id, String name, Integer points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public PlayerEntity(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    @Override
    public int compareTo(PlayerEntity player) {
        return (player.getPoints() - this.points);
    }
}
