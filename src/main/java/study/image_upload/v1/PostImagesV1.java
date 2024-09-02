package study.image_upload.v1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "post_images_v1")
public class PostImagesV1 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_images_id")
    private Long id;

    // 다대일 양방향 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostV1 post;

    // 저장한 이미지 경로 (UUID + 이미지 이름)
    private String imagePath;

    // 연관관계 편의 메서드
    public void setPost(PostV1 post) {
        this.post = post;
        post.getPostImagesList().add(this);
    }

    public PostImagesV1(String imagePath) {
        this.imagePath = imagePath;
    }
}
