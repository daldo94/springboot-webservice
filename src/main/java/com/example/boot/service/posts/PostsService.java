package com.example.boot.service.posts;

import com.example.boot.domain.posts.Posts;
import com.example.boot.domain.posts.PostsRepository;
import com.example.boot.web.dto.PostsResponseDTO;
import com.example.boot.web.dto.PostsSaveRequestDTO;
import com.example.boot.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO){
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDTO.getTitle(),requestDTO.getContent());
        return id;
    }


    public PostsResponseDTO findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDTO(entity);
    }

}
