package com.thehecklers.sburrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SburRestDemoApplication.class, args);
	}

}

@RestController
@RequestMapping("/livros")
class RestApiDemoController {
	private List<Livro> livros = new ArrayList<>();

	public RestApiDemoController() {
		livros.addAll(List.of(
				new Livro("Game of Thrones"),
				new Livro("Senhor dos Aneis"),
				new Livro("Harry Potter"),
				new Livro("Percy Jackson")
		));
	}

	@GetMapping
	Iterable<Livro> getLivros() {
		return livros;
	}

	@GetMapping("/{id}")
	Optional<Livro> getCoffeeById(@PathVariable String id) {
		for (Livro c: livros) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}

		return Optional.empty();
	}

	@PostMapping
    Livro postCoffee(@RequestBody Livro livro) {
		livros.add(livro);
		return livro;
	}

	@PutMapping("/{id}")
	ResponseEntity<Livro> putCoffee(@PathVariable String id,
                                    @RequestBody Livro livro) {
		int coffeeIndex = -1;

		for (Livro c: livros) {
			if (c.getId().equals(id)) {
				coffeeIndex = livros.indexOf(c);
                livros.set(coffeeIndex, livro);
			}
		}

		return (coffeeIndex == -1) ?
				new ResponseEntity<>(postCoffee(livro), HttpStatus.CREATED) :
				new ResponseEntity<>(livro, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
        livros.removeIf(c -> c.getId().equals(id));
	}
}

class Livro {
	private final String id;
	private String name;

	public Livro(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Livro(String name) {
		this(UUID.randomUUID().toString(), name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}