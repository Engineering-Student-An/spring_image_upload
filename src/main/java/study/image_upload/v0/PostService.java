package study.image_upload.v0;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 모든 게시글 리턴
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 특정 게시글 리턴
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    // 게시글 저장
    @Transactional
    public Post save(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getContent());
        return postRepository.save(post);
    }
}