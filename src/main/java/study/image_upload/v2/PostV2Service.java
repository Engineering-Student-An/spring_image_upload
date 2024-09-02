package study.image_upload.v2;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostV2Service {

    private final PostV2Repository postV2Repository;
    private final PostImagesV2Service postImagesV2Service;

    // 모든 게시글 리턴
    public List<PostV2> findAll() {
        return postV2Repository.findAll();
    }

    // 특정 게시글 리턴
    public PostV2 findById(Long postId) {
        return postV2Repository.findById(postId).orElse(null);
    }

    // 게시글 저장
    @Transactional
    public void save(PostV2Dto postDto) {
        PostV2 post = new PostV2(postDto.getTitle(), postDto.getContent());

        // 이미지 저장 로직 추가
        postImagesV2Service.saveImage(postDto.getImageList(), post);
        postV2Repository.save(post);
    }
}