package me.steell.miniproject.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public void deleteOne(Long id) {
        
        Customer customer = em.find(Customer.class, id);
        em.remove(customer);
    }
        
    public List<Customer> findAll( String customername, String customertype ){

        StringBuilder sb = new StringBuilder();
        sb.append("select m from Customer m where 1 = 1 ");
        if( customername != null && !"".equals(customername) ){
            sb.append("and name like concat('%',:customername,'%') ");
        }

        if( customertype != null && !"".equals(customertype) ){
            sb.append("and customertype = :customertype ");
        }

        TypedQuery<Customer> query = em.createQuery( sb.toString(), Customer.class);
        if( customername != null && !"".equals(customername) ){
            query.setParameter("customername", customername);
        }

        if( customertype != null && !"".equals(customertype) ){
            query.setParameter("customertype", customertype);
        }
        List<Customer> results = query.getResultList();

        return results;
    }
    
    public List<Customer> findByName(String name){
        StringBuilder sb = new StringBuilder();
        sb.append("select m from Customer m where m.name = :name");
        return em.createQuery(sb.toString(), Customer.class)
            .setParameter("name", name)
            .getResultList();
    }
    
    public List<Customer> findByRegnumber(String string){
        StringBuilder sb = new StringBuilder();
        sb.append("select m from Customer m where m.regnumber = :regnumber");
        return em.createQuery(sb.toString(), Customer.class)
            .setParameter("regnumber", string)
            .getResultList();
    }

    public List<Customer> findByBizregnumber(String string){
        StringBuilder sb = new StringBuilder();
        sb.append("select m from Customer m where m.bizregnumber = :bizregnumber");
        return em.createQuery(sb.toString(), Customer.class)
            .setParameter("bizregnumber", string)
            .getResultList();
    }

	
}