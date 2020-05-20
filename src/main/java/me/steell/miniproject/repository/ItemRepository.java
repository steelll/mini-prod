package me.steell.miniproject.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.item.Item;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    
    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null) {
            em.persist(item);
        } else{
            em.merge(item);
        }
    }

    public Item fidnOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
              .getResultList();
    }

}