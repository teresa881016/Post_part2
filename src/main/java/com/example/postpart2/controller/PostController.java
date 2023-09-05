package com.example.postpart2.controller;


import com.example.postpart2.dto.PostRequestDto;
import com.example.postpart2.dto.PostResponseDto;
import com.example.postpart2.jwt.JwtUtil;
import com.example.postpart2.repository.UserRepository;
import com.example.postpart2.security.UserDetailsImpl;
import com.example.postpart2.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //
@RequiredArgsConstructor
@RequestMapping("/api/users/login")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 게시글 생성하기
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    //게시글 조회하기
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // id로 조회
    @GetMapping("/posts/{id}")
    public PostResponseDto getPostsById(@PathVariable Long id) {
        return postService.getPostsById(id);
    }


    // 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto, @PathVariable Long id) {
        if (!userDetails.getUsername().equals(requestDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        PostResponseDto updatedPost = postService.updatePost(id, requestDto);

        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long deletedPostId = postService.deletePost(id, userDetails.getUsername());
        if (deletedPostId != null) {
            return ResponseEntity.ok(deletedPostId);
        } else {
            return ResponseEntity.notFound().build();
        }

//        return ResponseEntity.ok()
//                .body(new SuccessMessage("게시물 삭제 성공 Post ID: " + deletedPostId));
    }
}




