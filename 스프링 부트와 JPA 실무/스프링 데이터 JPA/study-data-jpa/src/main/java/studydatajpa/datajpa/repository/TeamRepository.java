package studydatajpa.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studydatajpa.datajpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
