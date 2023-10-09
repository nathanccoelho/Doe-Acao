package com.doeacao.doeacao.controller;

import com.doeacao.doeacao.model.Post;
import com.doeacao.doeacao.repository.PostRepository;
import com.doeacao.doeacao.repository.ThemeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Post>> getByTitle (@PathVariable String title){
        return ResponseEntity.ok(postRepository.findAllByTitleContainingIgnoreCase(title));
    }

    @PostMapping
    public ResponseEntity<Post> post (@Valid @RequestBody Post post) {
        if (themeRepository.existsById(post.getTheme().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Theme do not exist!", null);
    }

    @PutMapping
    public ResponseEntity<Post> put(@Valid @RequestBody Post post) {
        if (postRepository.existsById(post.getId())) {

            if (themeRepository.existsById(post.getTheme().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(postRepository.save(post));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Theme do not exist!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        postRepository.deleteById(id);

    }
}
