package lk.ijse.dep.spring.business.custom.impl;


import lk.ijse.dep.spring.business.custom.OrderBO;
import lk.ijse.dep.spring.dao.custom.*;
import lk.ijse.dep.spring.dto.OrderDTO;
import lk.ijse.dep.spring.dto.OrderDTO2;
import lk.ijse.dep.spring.dto.OrderDetailDTO;
import lk.ijse.dep.spring.entity.CustomEntity;
import lk.ijse.dep.spring.entity.Item;
import lk.ijse.dep.spring.entity.Order;
import lk.ijse.dep.spring.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private QueryDAO queryDAO;
    @Autowired
    private CustomerDAO customerDAO;

    @Transactional(readOnly = true)
    @Override
    public int getLastOrderId() {

            int lastOrderId = orderDAO.getLastOrderId();
 //           session.getTransaction().commit();

            return lastOrderId;

 //       }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean placeOrder(OrderDTO order) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            orderDAO.setSession(session);
//            customerDAO.setSession(session);
//            orderDetailDAO.setSession(session);
//            session.beginTransaction();

            int oId = order.getId();
//            orderDAO.save(new Order(oId, new java.sql.Date(new Date().getTime()),
//                    session.load(Customer.class, order.getCustomerId())));
        orderDAO.save(new Order(oId, new java.sql.Date(new Date().getTime()),
                customerDAO.find(order.getCustomerId())));

            for (OrderDetailDTO orderDetail : order.getOrderDetails()) {
                orderDetailDAO.save(new OrderDetail(oId, orderDetail.getCode(),
                        orderDetail.getQty(), orderDetail.getUnitPrice()));


                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                itemDAO.update(item);
            //    session.getTransaction().commit();

            }

            return true;

      //  }
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO2> getOrderInfo(String query)  {

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//            queryDAO.setSession(session);
//
//            session.beginTransaction();

            List<CustomEntity> ordersInfo = queryDAO.getOrdersInfo(query);

            List<OrderDTO2> al = new ArrayList<>();

            for (CustomEntity customEntity : ordersInfo) {
                al.add(new OrderDTO2(customEntity.getOrderId(), customEntity.getOrderDate(), customEntity.getCustomerId(), customEntity.getCustomerName(), customEntity.getOrderTotal()));
            }

     //       session.getTransaction().commit();

            return al;
      //  }
    }
}
