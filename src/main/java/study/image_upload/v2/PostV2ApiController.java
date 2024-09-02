package study.image_upload.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/api/posts")
public class PostV2ApiController {

    private final PostV2Service postV2Service;

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostV2>> findAllPosts() {
        return ResponseEntity.ok(postV2Service.findAll());
    }

    // 특정 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostV2> findPost(@PathVariable("postId") Long postId) {
        PostV2 post = postV2Service.findById(postId);
        return post == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) : ResponseEntity.ok(post);
    }

    // 게시글 작성
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> savePost(@ModelAttribute PostV2Dto postDto) {

        postV2Service.save(postDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}