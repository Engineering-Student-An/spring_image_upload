package study.image_upload.v2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "post_images_v2")
public class PostImagesV2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_images_id")
    private Long id;

    // 다대일 양방향 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostV2 post;

    // 저장한 이미지 이름
    private String imageName;

    // 이미지 정보
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imageData;

    // Base64 문자열 추가
    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(this.imageData);
    }

    // 연관관계 편의 메서드
    public void setPost(PostV2 post) {
        this.post = post;
        post.getPostImagesList().add(this);
    }

    public PostImagesV2(String imageName, byte[] imageData) {
        this.imageName = imageName;
        this.imageData = imageData;
    }
}
