package study.image_upload.v1;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostV1Service {

    private final PostV1Repository postV1Repository;
    private final PostImagesV1Service postImagesV1Service;

    // 모든 게시글 리턴
    public List<PostV1> findAll() {
        return postV1Repository.findAll();
    }

    // 특정 게시글 리턴
    public PostV1 findById(Long postId) {
        return postV1Repository.findById(postId).orElse(null);
    }

    // 게시글 저장
    @Transactional
    public void save(PostV1Dto postDto) {
        PostV1 post = new PostV1(postDto.getTitle(), postDto.getContent());

        // 이미지 저장 로직 추가
        postImagesV1Service.saveImage(postDto.getImageList(), post);
        postV1Repository.save(post);
    }
}