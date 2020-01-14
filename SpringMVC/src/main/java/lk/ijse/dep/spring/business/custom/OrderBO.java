package lk.ijse.dep.spring.business.custom;



import lk.ijse.dep.spring.business.SuperBO;
import lk.ijse.dep.spring.dto.OrderDTO;
import lk.ijse.dep.spring.dto.OrderDTO2;

import java.util.List;

public interface OrderBO extends SuperBO {

    int getLastOrderId() ;

    boolean placeOrder(OrderDTO orderDTO) ;

    List<OrderDTO2> getOrderInfo(String query) ;

}
