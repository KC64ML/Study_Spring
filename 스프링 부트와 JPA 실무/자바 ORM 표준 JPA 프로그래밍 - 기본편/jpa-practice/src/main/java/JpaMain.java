import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("TeamB");
            em.persist(team2);
//회원 저장
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            member.setTeam(team2);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setTeam(team);
            member2.setTeam(team2);
            em.persist(member2);



            em.flush();
            em.clear();

            //조회
            Team team3 = em.find(Team.class, 1L);
            Team team4 = em.find(Team.class, 2L);
            System.out.println("team3 = " + team3);
            System.out.println("team3 = " + team4);
            List<Member> members = team3.getMembers(); // (팀 -> 회원) 객체 그래프 탐색
            List<Member> members2 = team4.getMembers(); // (팀 -> 회원) 객체 그래프 탐색

            for (Member  m : members) {
                System.out.println("member.username = " + m.getName());
            }

            for (Member  m : members2) {
                System.out.println("member2.username = " + m.getName());
            }

            tx.commit();
//            HelloAb
        } catch (Exception e) {
            System.out.println("예외 발생");
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
