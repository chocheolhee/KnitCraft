package Toy.KnitCraft.service;

import Toy.KnitCraft.repository.PostRepository;
import Toy.KnitCraft.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {


        postRepository.save(postCreate);
    }

}
