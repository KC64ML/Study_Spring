package hellojpa;

import hellojpa.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();

// 임베디드 값 타입
            member.setHomeAddress(new Address("통영", "몽돌해수욕장", "660-123"));

// 기본값 타입 컬렉션
            member.getFavoriteFoods().add("짬뽕");
            member.getFavoriteFoods().add("짜장");
            member.getFavoriteFoods().add("탕수육");

// 임베디드 값 타입 컬렉션
            member.getAddressHistory().add(new Address("서울", "강남", "123-123"));
            member.getAddressHistory().add(new Address("부산", "강북", "000-000"));

            em.persist(member);

            em.flush();
            em.clear();

            //
            System.out.println("========START============");
            Member findMember = em.find(Member.class, member.getId());

            // homeCity --> newCity
            // findMember.getHomeAddress().setCity("newCity"); 이는 잘못된 것이다.

            // 1
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(),a.getZipcode()));

            // 2
            // 치칸 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("치킨");

            // 3
            // 업데이트
            findMember.getAddressHistory().remove(new Address("서울", "강남", "123-123"));
            findMember.getAddressHistory().add(new Address("newCity1", "street", "1000"));

            System.out.println("========end============");

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
