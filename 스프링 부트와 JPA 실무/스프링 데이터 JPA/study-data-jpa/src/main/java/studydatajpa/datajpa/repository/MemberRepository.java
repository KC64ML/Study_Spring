package studydatajpa.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studydatajpa.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}