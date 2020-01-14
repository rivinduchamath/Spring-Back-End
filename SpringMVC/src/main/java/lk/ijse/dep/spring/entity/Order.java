package lk.ijse.dep.spring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`Order`")
public class Order implements SuperEntity{

    @Id
    private int id;
    private Date date;

    @ManyToOne(cascade ={ CascadeType.PERSIST ,CascadeType.REFRESH, CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name="customer_id",referencedColumnName = "customerId",nullable = false)
//   @JoinTable(name = "kamal",
//   joinColumns = { @JoinColumn(name = "A",referencedColumnName = "B",insertable = false,updatable = false)},
//           inverseJoinColumns = {@JoinColumn(name = "BB",referencedColumnName = "hh")})
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST ,CascadeType.DETACH,CascadeType.MERGE})
    private
    List<OrderDetail> orderDetails =new ArrayList<>();

    public Order() {
    }

    public Order(int id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail){
       // orderDetail.setOrder(this); oni na ...  remove order daanneth na.. Composite PK 1k nisa
        //this.getOrderDetails().add(orderDetail);
        this.orderDetails.add(orderDetail);

    }

}
