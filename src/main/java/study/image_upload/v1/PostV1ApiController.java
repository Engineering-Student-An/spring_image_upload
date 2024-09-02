package study.image_upload.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/posts")
public class PostV1ApiController {

    private final PostV1Service postV1Service;

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostV1>> findAllPosts() {
        return ResponseEntity.ok(postV1Service.findAll());
    }

    // 특정 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostV1> findPost(@PathVariable("postId") Long postId) {
        PostV1 post = postV1Service.findById(postId);
        return post == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) : ResponseEntity.ok(post);
    }

    // 게시글 작성
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> savePost(@ModelAttribute PostV1Dto postDto) {

        postV1Service.save(postDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}