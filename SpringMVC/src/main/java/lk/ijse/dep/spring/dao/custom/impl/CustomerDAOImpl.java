package lk.ijse.dep.spring.dao.custom.impl;


import lk.ijse.dep.spring.dao.CrudDAOImpl;
import lk.ijse.dep.spring.dao.custom.CustomerDAO;
import lk.ijse.dep.spring.entity.Customer;
import org.springframework.stereotype.Repository;

//@Component venuvata @Repository annotation eka use kala yuthui..
@Repository
public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {

    @Override
    public String getLastCustomerId()  {

        return (String) getSession().createNativeQuery("SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1").uniqueResult();
    }

//    @Override
//    public List<Customer> findAll() throws Exception {
//
//        return session.createQuery("from Customer c",Customer.class).list();
//    }
//
//    @Override
//    public Customer find(String s) throws Exception {
//
//        return session.get(Customer.class,s);
//    }
//
//    @Override
//    public void save(Customer entity) throws Exception {
//         session.save(entity);
//    }
//
//    @Override
//    public void update(Customer customer) throws Exception {
//        session.merge(customer);
//          }
//
//    @Override
//    public void delete(String s) throws Exception {
//        session.delete(session.load(Customer.class,s));
//    }
//
//    @Override
//    public void setSession(Session session) {
//        this.session =session;
//    }
}
