package study.image_upload.v3;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostImagesV3Service {

    private final PostImagesV3Repository postImagesV3Repository;

    @Transactional
    public void saveImage(List<MultipartFile> imageList, PostV3 post) {
        // 업로드한 파일이 없다면 리턴
        if(imageList == null) return;

        // 사진 업로드 개수 <= 5 인지 체크
        if(imageList.size() > 5) throw new IllegalArgumentException("최대 사진 업로드 개수는 5개 입니다!");

        // 업로드 파일의 타입이 사진인지 체크
        for (MultipartFile image : imageList) {
            if(!isImageFile(image)) throw new IllegalArgumentException("사진 이외의 파일은 업로드 불가능합니다!");
        }

        // 각 사진에 대해 업로드와 db에 저장
        for (MultipartFile image : imageList) {

            // 파일이 비어있는 경우 패스
            if(Objects.requireNonNull(image.getOriginalFilename()).isEmpty()) continue;

            // 이미지 이름 앞에 랜덤값 추가 (중복 안되게)
            String imageName = addUUID(image.getOriginalFilename());

            // 이미지 업로드
            try {
                uploadImage(image, imageName);
            } catch (IOException e) {
                throw new RuntimeException("이미지 업로드를 실패했습니다!");
            }

            // PostImages 엔티티 저장
            savePostImages(imageName, post);
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

    // 이미지를 외부경로에 업로드하는 메서드
    private static void uploadImage(MultipartFile image, String imageName) throws IOException {
        // 이미지를 실제 업로드 할 경로 (Users/anchangmin/Desktop/image_upload/ 에 imageName으로 업로드)
        String uploadPath = "/Users/anchangmin/Desktop/image_upload/" + imageName;

        // 업로드 경로를 Path 객체로 변환
        Path path = Paths.get(uploadPath);

        // 경로의 부모 디렉토리가 존재하지 않으면 생성
        Files.createDirectories(path.getParent());

        // 이미지 바이트를 파일에 기록
        Files.write(path, image.getBytes());
    }

    // PostImages 엔티티 생성 및 저장하는 메서드
    private void savePostImages(String imageName, PostV3 post) {

        // PostImages 이미지 이름 설정함
        PostImagesV3 postImages = new PostImagesV3(imageName);

        // 연관관계 편의 메서드 통해서 Post 를 설정
        postImages.setPost(post);

        // 레파지토리에 엔티티 저장
        postImagesV3Repository.save(postImages);
    }
}
