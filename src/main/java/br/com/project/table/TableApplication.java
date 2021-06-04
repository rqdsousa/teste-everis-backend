package br.com.project.table;

import br.com.project.table.Entity.PlayerEntity;
import br.com.project.table.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TableApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new PlayerEntity("Renata   ", 120));
		repository.save(new PlayerEntity("Fernanda ", 120));
		repository.save(new PlayerEntity("Francisco", 120));
		repository.save(new PlayerEntity("Boris    ", 110));
		repository.save(new PlayerEntity("Eduardo  ", 100));
		repository.save(new PlayerEntity("Claudia  ", 100));
		repository.save(new PlayerEntity("Franciele", 90));
	}

	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}

}
