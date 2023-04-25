package Toy.KnitCraft.controller;

import Toy.KnitCraft.request.PostCreate;
import Toy.KnitCraft.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public String post(@RequestBody @Valid PostCreate request) {
        postService.write(request);
        return "true";
    }
}
