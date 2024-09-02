package study.image_upload.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostV1Repository extends JpaRepository<PostV1, Long> {
}
