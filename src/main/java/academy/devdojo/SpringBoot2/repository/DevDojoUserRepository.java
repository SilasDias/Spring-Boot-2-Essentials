package academy.devdojo.SpringBoot2.repository;

import academy.devdojo.SpringBoot2.domain.Anime;
import academy.devdojo.SpringBoot2.domain.DevDojoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevDojoUserRepository extends JpaRepository<DevDojoUser, Long> {
    DevDojoUser findByUsername(String username);
}
