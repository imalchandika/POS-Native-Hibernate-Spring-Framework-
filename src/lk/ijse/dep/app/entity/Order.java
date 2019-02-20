package lk.ijse.dep.app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order extends SuperEntity {
    @Id
    private String id;
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "id")
    private Customer customer;

    public Order() {
    }

    public Order(String id, java.sql.Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

//    public <T extends SuperEntity> Order(String id, java.sql.Date date, T entity) {
//        super();
//    }

//    public Order(String id, Date date, CustomerDTO customer) {
//        this.id = id;
//        this.date = date;
//        this.customer = customer;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                "id='" + id + '\'' +
                ", date=" + date +
                ", customer='" + customer + '\'' +
                '}';
    }
}
