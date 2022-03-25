import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("user");

            em.persist(member);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId()); //Proxy
            System.out.println("refMember = " + refMember.getClass());

//            em.detach(refMember); // 영속성 컨텍스트에서 끄집어 낸다. refMember를 영속성 컨텍스트에서 관리안하겠다.

            em.close();  // 영속성 컨텍스트를 종료해버린다. 똑같이 refMember를 호출해봐야 조회돼지 않는다.
            System.out.println("refMember.getUsername() = " + refMember.getUsername()); //


            //  could not initialize proxy [Member#1] - no Session  proxy를 초기화 할 수 없다.
            // 영속성 컨텍스트에 해당 refMember이 없어요!
            tx.commit();
        } catch (Exception e) {
            System.out.println("예외 발생");
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }

        emf.close();
    }
}
