package study.image_upload.v3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImagesV3Repository extends JpaRepository<PostImagesV3, Long> {
}
