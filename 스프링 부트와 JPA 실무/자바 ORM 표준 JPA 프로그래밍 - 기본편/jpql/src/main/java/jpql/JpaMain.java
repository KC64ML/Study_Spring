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

            Member member1 = new Member("member1", "회원1");
            em.persist(member1);

            Member member2 = new Member("member2", "회원2");
            em.persist(member2);

            Team team1 = new Team("team1", "팀1");
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);

            em.persist(team1);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
