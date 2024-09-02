package study.image_upload.v2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostV2Repository extends JpaRepository<PostV2, Long> {
}
