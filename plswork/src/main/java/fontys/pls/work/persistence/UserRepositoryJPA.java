package fontys.pls.work.persistence;

import fontys.pls.work.persistence.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
