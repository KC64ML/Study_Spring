package csjpabook.csjpashop.repository;


import csjpabook.csjpashop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 값이 없을 시 새로 엔티티에 삽입
        if (item.getId() == null) {
            em.persist(item);
        }else{
            // item 값이 있을 시, DB에서 데이터 조회하여 가져옴
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // 여러 개, 복수로 찾을 때 jpql 작성해야 한다.
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}