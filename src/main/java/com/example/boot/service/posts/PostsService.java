package com.example.boot.service.posts;

import com.example.boot.domain.posts.Posts;
import com.example.boot.domain.posts.PostsRepository;
import com.example.boot.web.dto.PostsListResponseDTO;
import com.example.boot.web.dto.PostsResponseDTO;
import com.example.boot.web.dto.PostsSaveRequestDTO;
import com.example.boot.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO) {
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        //Using Dirty Checking
        posts.update(requestDTO.getTitle(), requestDTO.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

    public PostsResponseDTO findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDTO> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDTO::new).collect(Collectors.toList());
    }

}
