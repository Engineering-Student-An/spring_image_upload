package study.image_upload.v3;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostV3Service {

    private final PostV3Repository postV3Repository;
    private final PostImagesV3Service postImagesV3Service;

    // 모든 게시글 리턴
    public List<PostV3> findAll() {
        return postV3Repository.findAll();
    }

    // 특정 게시글 리턴
    public PostV3 findById(Long postId) {
        return postV3Repository.findById(postId).orElse(null);
    }

    // 게시글 저장
    @Transactional
    public void save(PostV3Dto postDto) {
        PostV3 post = new PostV3(postDto.getTitle(), postDto.getContent());

        // 이미지 저장 로직 추가
        postImagesV3Service.saveImage(postDto.getImageList(), post);
        postV3Repository.save(post);
    }
}