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

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("회원1");
            member.setTeam(teamA);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            // FLUSH 자동 호출
            // flush는 commit, query를 호출할 때 자동 호출된다.
            // flush 되기 전까지 Member의 age는 0이 저장되어 있다.
            // 이로인해 영속성 컨텍스트에는 member의 age는 0이 저장되고 DB도 0이 저장된다.

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            // 이때 createQuery update문으로 Database에 나이를 20으로 업데이트 하는 문장이 있다.
            // 이로써, Database는 20으로 업데이트 되지만, 영속성 컨텍스트에는 여전히 Member의 age는 0으로 저장되어 있다.

            System.out.println("resultCount = " + resultCount);

            System.out.println("member = " +member.getAge());
            System.out.println("member2 = " +member2.getAge());
            System.out.println("member3 = " +member3.getAge());

            // 그리고 영속성 컨텍스트 find 메서드를 호출해서 조회해도 여전히 0으로 저장된다.
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("findMember = " + findMember);


            // 그냥 벌크 연산을 할 경우, db에만 반영된다.
            // 그러므로 벌크 연산을 사용한 후 바로, clear()메서드를 호출해야 한다.
            em.clear();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
