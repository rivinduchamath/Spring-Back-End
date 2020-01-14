package lk.ijse.dep.spring.dao.custom.impl;

import lk.ijse.dep.spring.dao.CrudDAOImpl;
import lk.ijse.dep.spring.dao.custom.OrderDAO;
import lk.ijse.dep.spring.entity.Order;
import org.springframework.stereotype.Repository;

//@Component venuvata @Repository annotation eka use kala yuthui..
@Repository
public class OrderDAOImpl extends CrudDAOImpl<Order, Integer> implements OrderDAO {

    @Override
    public int getLastOrderId()  {
        Integer i= (Integer) getSession().createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1").uniqueResult();


        if(i==null)
            return 0;
        else
            return i;
    }

    @Override
    public boolean existsByCustomerId(String customerId)  {
      return getSession().createNativeQuery("SELECT * FROM `Order` o WHERE o.customer_id=?1").setParameter(1,customerId).uniqueResult()!= null;

    }


//
//    @Override
//    public List<Order> findAll() throws Exception {
//        return session.createQuery("from Order o", Order.class).list();
//
//    }
//
//    @Override
//    public Order find(Integer orderId) throws Exception {
//       return session.get(Order.class,orderId);
//    }
//
//    @Override
//    public void save(Order order) throws Exception {
//        session.save(order);
//    }
//
//    @Override
//    public void update(Order order) throws Exception {
//       session.merge(order);
//    }
//
//    @Override
//    public void delete(Integer orderId) throws Exception {
//        session.delete(session.load(Order.class,orderId));
//    }
//
//    @Override
//    public void setSession(Session session) {
//        this.session =session;
//    }
}
