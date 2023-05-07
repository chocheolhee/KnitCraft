package Toy.KnitCraft.controller;

import Toy.KnitCraft.config.data.UserSession;
import Toy.KnitCraft.request.PostCreate;
import Toy.KnitCraft.request.PostEdit;
import Toy.KnitCraft.response.PostResponse;
import Toy.KnitCraft.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/test")
    public Long authResolver(UserSession userSession) {
        log.info(">>>{}", userSession.id);
        return userSession.id;
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request, @RequestParam String authorization) {
        if (authorization.equals("cheol")) {
            request.validate();
            postService.write(request);
        }
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

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
