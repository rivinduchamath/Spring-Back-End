package lk.ijse.dep.spring.dao.custom.impl;


import lk.ijse.dep.spring.dao.CrudDAOImpl;
import lk.ijse.dep.spring.dao.custom.OrderDetailDAO;
import lk.ijse.dep.spring.entity.OrderDetail;
import lk.ijse.dep.spring.entity.OrderDetailPK;
import org.springframework.stereotype.Repository;

//@Component venuvata @Repository annotation eka use kala yuthui..
@Repository
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {


    @Override
    public boolean existsByItemCode(String itemCode)  {
        return getSession().createNativeQuery("SELECT * FROM OrderDetail od WHERE od.item_code=?1").setParameter(1,itemCode).uniqueResult()!=null;

    }

//    @Override
//    public List<OrderDetail> findAll() throws Exception {
//        return session.createQuery("FROM OrderDetail od ",OrderDetail.class).list();
//    }
//
//    @Override
//    public OrderDetail find(OrderDetailPK orderDetailPK) throws Exception {
//             return session.get(OrderDetail.class,orderDetailPK);
//    }
//
//    @Override
//    public void save(OrderDetail orderDetail) throws Exception {
//        session.save(orderDetail);
//    }
//
//    @Override
//    public void update(OrderDetail orderDetail) throws Exception {
//        session.merge(orderDetail);
//    }
//
//    @Override
//    public void delete(OrderDetailPK orderDetailPK) throws Exception {
//        session.delete(session.load(OrderDetail.class,orderDetailPK));  }
//
//    @Override
//    public void setSession(Session session) {
//        this.session =session;
//    }
}
