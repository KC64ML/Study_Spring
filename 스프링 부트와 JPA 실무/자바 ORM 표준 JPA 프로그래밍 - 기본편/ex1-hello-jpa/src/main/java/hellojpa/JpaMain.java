package hellojpa;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", " 10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street", "5000"));
            member.getAddressHistory().add(new AddressEntity("old2","street", "7000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("======== START ===========");
            Member findMember = em.find(Member.class, member.getId());

//            // homeCity → newCity
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            // 치킨 → 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("우유");

            // 주소 변경
//            findMember.getAddressHistory().remove(new Address("old1","street", "5000"));
//            findMember.getAddressHistory().add(new Address("newCity1","street", "5000"));

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
