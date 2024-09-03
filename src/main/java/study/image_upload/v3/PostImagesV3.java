package study.image_upload.v3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "post_images_v3")
public class PostImagesV3 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_images_id")
    private Long id;

    // 다대일 양방향 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostV3 post;

    // 저장한 이미지 이름 (UUID + 이미지 이름)
    private String imageName;

    // 연관관계 편의 메서드
    public void setPost(PostV3 post) {
        this.post = post;
        post.getPostImagesList().add(this);
    }

    public PostImagesV3(String imageName) {
        this.imageName = imageName;
    }
}
