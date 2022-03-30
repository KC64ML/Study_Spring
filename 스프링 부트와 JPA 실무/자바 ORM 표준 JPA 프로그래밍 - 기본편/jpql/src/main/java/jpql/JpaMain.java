package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            // 회원1 저장
            Member member1 = new Member(1L, "회원1");
            member1.setTeam(null);  // 연관관계 설정 team1 -> team1
            em.persist(member1);

            // 팀1 저장
            Team teamA = em.find(Team.class, 2L);
            List<Member> members = teamA.getMembers();

            members.get(0).setTeam(null);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
