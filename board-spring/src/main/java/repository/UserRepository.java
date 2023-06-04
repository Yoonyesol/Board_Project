package repository;

import entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //해당 클래스를 레파지토리로 사용하겠다.
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
