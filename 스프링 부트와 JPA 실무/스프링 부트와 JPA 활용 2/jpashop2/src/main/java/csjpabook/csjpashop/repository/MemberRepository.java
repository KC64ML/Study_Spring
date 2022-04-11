package csjpabook.csjpashop.repository;

import csjpabook.csjpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    // spring이 엔티티 매니저를 만들어서 엔티티를 주입해준다.
    @PersistenceContext
    private EntityManager em;


    public void save(Member member) { em.persist(member); }

    // 단일
    // jpa가 제공하는 find(id)를 통해 member를 찾아 반환
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 여러가지
    // 여러가지 데이터 조회할 때는 List!
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}