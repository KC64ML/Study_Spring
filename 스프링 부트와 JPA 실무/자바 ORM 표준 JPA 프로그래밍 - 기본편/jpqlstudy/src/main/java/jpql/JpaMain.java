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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setType(MemberType.ADMIN);   // type이 ADMIN
            em.persist(member);



            // 영속성 컨텍스트 비우기
            em.flush();
            em.clear();


            // jpql.MemberType.ADMIN 타입의 USER만 조회한다.
            String jpql = "select m.username, 'HELLO', TRUE From Member m "
                    + "where m.type = :userType";

            List<Object[]> resultList = em.createQuery(jpql)
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();

            for (Object[] member1 : resultList) {
                System.out.println("member1[0] = " + member1[0]);
                System.out.println("member1[1] = " + member1[1]);
                System.out.println("member1[2] = " + member1[2]);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
