package lk.ijse.dep.spring.dao.custom.impl;


import lk.ijse.dep.spring.dao.custom.QueryDAO;
import lk.ijse.dep.spring.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Component venuvata @Repository annotation eka use kala yuthui..
@Repository
public class QueryDAOImpl implements QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public CustomEntity getOrderInfo(int orderId)  {
       /* return (CustomEntity) session.createQuery("SELECT NEW lk.ijse.dep.pos.entity.CustomEntity(o.id, c.id,c.name,o.date) FROM Customer c INNER JOIN c.orders o WHERE o.id=?1")
                .setParameter(1, orderId)
                .uniqueResult();*/
       return null;
    }

    @Override
    public CustomEntity getOrderInfo2(int orderId)  {
       /* ResultSet rst = CrudUtil.execute("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId\" +\n" +
                "                \" INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id=? GROUP BY orderId", orderId);
        if (rst.next()){
            return new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5));
        }else{
            return null;
        }*/
       return null;
    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query)  {

        NativeQuery nativeQuery = getSession().createNativeQuery("SELECT o.id as orderId, c.customerId as customerId, c.name as customerName, o.date as orderDate,\n" +
                "SUM(od.qty * od.unitPrice) AS orderTotal " +
                "FROM customer C INNER JOIN `Order` O ON C.customerId = O.customer_id INNER JOIN orderdetail OD on O.id = OD.order_id where o.id LIKE ?1 OR c.customerId LIKE ?2" +
                " OR c.name LIKE ?3 GROUP BY O.id");

        nativeQuery.setParameter(1,query);
        nativeQuery.setParameter(2,query);
        nativeQuery.setParameter(3,query);
        Query<CustomEntity> query1 = nativeQuery.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));


        List<CustomEntity> list = query1.list();


        return list;
    }
}
