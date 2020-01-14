package lk.ijse.dep.spring.dao.custom;



import lk.ijse.dep.spring.dao.CrudDAO;
import lk.ijse.dep.spring.entity.Order;

public interface OrderDAO extends CrudDAO<Order, Integer> {

    int getLastOrderId() ;

    boolean existsByCustomerId(String customerId) ;



}
