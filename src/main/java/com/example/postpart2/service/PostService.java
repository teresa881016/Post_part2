package com.example.postpart2.service;


import com.example.postpart2.dto.PostRequestDto;
import com.example.postpart2.dto.PostResponseDto;
import com.example.postpart2.entity.Post;
import com.example.postpart2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;

    //게시글 만들기
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    // 게시글 전체조회 - 내림차순 정렬
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    //id 게시글 조회
    public PostResponseDto getPostsById(Long id) {
        Optional<Post> findbyId = postRepository.findById(id);
        if(findbyId.isPresent()){
            Post post = findbyId.get();
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        } else{
            throw new IllegalArgumentException("게시글 없음");
        }

    }

    // 게시글 수정하기
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = finePost(id);
        post.update(requestDto);
        Post updatePost = postRepository.save(post);
        return new PostResponseDto(updatePost);
    }

    // 게시글 삭제하기
    public Long deletePost(Long id, String username) {
        Post post = finePost(id);

        if(!post.getUsername().equals(username)){
            throw new IllegalArgumentException("삭제 안됨");
        }

        postRepository.delete(post);
        return id;
    }


    public Post finePost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );


    }


}
