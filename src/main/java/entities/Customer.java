package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_id")
    private int id;

    @Column(name = "CUSTOMER_title")
    private String title;

    @Column(name = "CUSTOMER_email", unique = true)
    private String email;

    @Column(name = "CUSTOMER_address")
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Customer(String title, String email, String address) {
        this.title = title;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {

        ArrayList<Integer> ordersId = orders.stream().map(Order::getId).collect(Collectors.toCollection(ArrayList::new));
        return "Customer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
