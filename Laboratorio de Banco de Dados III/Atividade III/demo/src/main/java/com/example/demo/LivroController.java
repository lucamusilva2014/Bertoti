package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/livros")
class LivroController {
    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
        // Inicialização de livros, se o repositório estiver vazio
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    new Livro("Game of Thrones"),
                    new Livro("Senhor dos Anéis"),
                    new Livro("Harry Potter"),
                    new Livro("Percy Jackson")
            ));
        }
    }

    @GetMapping
    public Iterable<Livro> getLivros() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {  // Usando Long como tipo
        Optional<Livro> livro = repository.findById(id);
        return livro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Livro> postLivro(@RequestBody Livro livro) {
        Livro savedLivro = repository.save(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> putLivro(@PathVariable Long id, @RequestBody Livro livro) {
        if (repository.existsById(id)) {
            livro.setId(id);
            Livro updatedLivro = repository.save(livro);
            return ResponseEntity.ok(updatedLivro);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se não encontrar
    }
}
