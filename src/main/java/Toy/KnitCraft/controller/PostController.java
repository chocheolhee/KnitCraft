package Toy.KnitCraft.controller;

import Toy.KnitCraft.domain.Post;
import Toy.KnitCraft.request.PostCreate;
import Toy.KnitCraft.response.PostResponse;
import Toy.KnitCraft.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Request 클래스
     * Response 클래스
     * 분리 완료
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {

        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable) {
        return postService.getList(pageable);
    }
}
