package study.image_upload.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0/api/posts")
public class PostApiController {

    private final PostService postService;

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    // 특정 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPost(@PathVariable("postId") Long postId) {
        Post post = postService.findById(postId);
        return post == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) : ResponseEntity.ok(post);
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody PostDto postDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.save(postDto));
    }
}