package study.image_upload.v2;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostImagesV2Service {

    private final PostImagesV2Repository postImagesV2Repository;

    @Transactional
    public void saveImage(List<MultipartFile> imageList, PostV2 post) {
        // 업로드한 파일이 없다면 리턴
        if(imageList == null) return;

        // 사진 업로드 개수 <= 5 인지 체크
        if(imageList.size() > 5) throw new IllegalArgumentException("최대 사진 업로드 개수는 5개 입니다!");

        // 업로드 파일의 타입이 사진인지 체크
        for (MultipartFile image : imageList) {
            if(!isImageFile(image)) throw new IllegalArgumentException("사진 이외의 파일은 업로드 불가능합니다!");
        }

        try {
            // 각 사진에 대해 db에 저장
            for (MultipartFile image : imageList) {

                // 파일이 비어있는 경우 패스
                if(Objects.requireNonNull(image.getOriginalFilename()).isEmpty()) continue;

                // 이미지 이름 앞에 랜덤값 추가 (중복 안되게)
                String imageName = addUUID(image.getOriginalFilename());

                // PostImages 엔티티 생성, Post와 연관관계 설정
                PostImagesV2 postImages = new PostImagesV2(imageName, image.getBytes());
                postImages.setPost(post);

                // 생성한 PostImages 엔티티 저장
                postImagesV2Repository.save(postImages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일이 이미지 형식인지 검사하는 메서드
    private static boolean isImageFile(MultipartFile image) {
        String contentType = image.getContentType();
        return contentType != null && (contentType.startsWith("image/"));
    }

    // 이미지 이름 중복 피하기 위해 이미지 이름 앞에 UUID 추가하는 메서드
    private static String addUUID(String originalFilename) {
        return UUID.randomUUID().toString().replace("-", "") + "_" + originalFilename;
    }
}
