package lk.ijse.dep.spring.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item implements SuperEntity{

    @Id
    private String code;
    private String description;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column( name = "qty_on_hand")
    private int qtyOnHand;


    @OneToMany(mappedBy = "item",cascade ={ CascadeType.PERSIST ,CascadeType.REFRESH, CascadeType.DETACH,CascadeType.MERGE})
    private
    List<OrderDetail> orderDetails =new ArrayList<>();

    public Item() {
    }

    public Item(String code, String description, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail){
     //   orderDetail.setItem(this);
        this.getOrderDetails().add(orderDetail);

    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
