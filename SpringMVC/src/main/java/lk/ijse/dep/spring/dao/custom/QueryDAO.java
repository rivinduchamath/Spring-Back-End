package lk.ijse.dep.spring.dao.custom;



import lk.ijse.dep.spring.dao.SuperDAO;
import lk.ijse.dep.spring.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    CustomEntity getOrderInfo(int orderId) ;

    CustomEntity getOrderInfo2(int orderId) ;

    /**
     *
     * @param query customerId, customerName, orderId, orderDate
     * @return
     * @
     */
    List<CustomEntity> getOrdersInfo(String query) ;

}
