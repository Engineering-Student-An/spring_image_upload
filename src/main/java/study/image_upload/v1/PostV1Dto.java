package study.image_upload.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostV1Dto {
    private String title;
    private String content;

    // 다수의 이미지 (리스트) 입력받기 위해 추가
    private List<MultipartFile> imageList;
}