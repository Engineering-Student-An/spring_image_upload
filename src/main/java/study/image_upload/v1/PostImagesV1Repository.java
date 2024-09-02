package study.image_upload.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImagesV1Repository extends JpaRepository<PostImagesV1, Long> {
}
