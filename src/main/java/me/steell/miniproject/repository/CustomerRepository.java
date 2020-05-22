package me.steell.miniproject.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.Customer;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    
    @PersistenceContext
    private final EntityManager em;

    public void save(Customer customer){
        if (customer.getId() == null) {
            em.persist(customer);
        } else{
            em.merge(customer);
        }
    }

    public Customer findOne(Long id){
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(){
        return em.createQuery("select m from Customer m", Customer.class)
                 .getResultList();
    }

    // public List<Customer> findByName(String name){
    //     return em.createQuery("select m from customer m where m.name = :name", Customer.class)
    //         .setParameter("name", name)
    //         .getResultList();
    // }

    // public List<Customer> findByType(String string){
    //     return em.createQuery("select m from customer m where m.customertype = :customertype", Customer.class)
    //         .setParameter("customertype", string)
    //         .getResultList();
    // }
    
    // public List<Customer> findByRegnumber(String string){
    //     return em.createQuery("select m from customer m where m.regnumber = :regnumber", Customer.class)
    //         .setParameter("regnumber", string)
    //         .getResultList();
    // }

    // public List<Customer> findByBizregnumber(String string){
    //     return em.createQuery("select m from customer m where m.bizregnumber = :bizregnumber", Customer.class)
    //         .setParameter("bizregnumber", string)
    //         .getResultList();
    // }
}