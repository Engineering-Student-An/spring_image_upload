package study.image_upload.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/api/posts")
public class PostV3ApiController {

    private final PostV3Service postV3Service;

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostV3>> findAllPosts() {
        return ResponseEntity.ok(postV3Service.findAll());
    }

    // 특정 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostV3> findPost(@PathVariable("postId") Long postId) {
        PostV3 post = postV3Service.findById(postId);
        return post == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) : ResponseEntity.ok(post);
    }

    // 게시글 작성
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> savePost(@ModelAttribute PostV3Dto postDto) {

        postV3Service.save(postDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}