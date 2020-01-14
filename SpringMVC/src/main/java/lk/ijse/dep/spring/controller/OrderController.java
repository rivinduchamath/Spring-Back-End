package lk.ijse.dep.spring.controller;

import lk.ijse.dep.spring.business.custom.ItemBO;
import lk.ijse.dep.spring.business.custom.OrderBO;
import lk.ijse.dep.spring.dto.ItemDTO;
import lk.ijse.dep.spring.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RequestMapping("/api/v1/orders")
@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    private OrderBO orderBO;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  public Integer saveOrder(@RequestBody OrderDTO orderDTO){
        orderBO.placeOrder(orderDTO);
        return orderDTO.getId();
    }
}
