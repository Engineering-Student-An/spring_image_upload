package study.image_upload.v3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostV3Repository extends JpaRepository<PostV3, Long> {
}
