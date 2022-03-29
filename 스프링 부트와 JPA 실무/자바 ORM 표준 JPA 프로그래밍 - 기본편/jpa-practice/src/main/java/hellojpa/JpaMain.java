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
            member.getAddressHistory().add(new AddressEntity("서울", "강남", "123-123"));
            member.getAddressHistory().add(new AddressEntity("부산", "강북", "000-000"));

            // 일대다 member 하나에 Address 2개 추가됨
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========START============");

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
