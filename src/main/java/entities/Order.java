package entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_id")
    private int id;

    @Column(name = "ORDER_title")
    private String title;

    @Column(name = "ORDER_price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_ALBUM",
            joinColumns = @JoinColumn(name = "ORDER_id"),
            inverseJoinColumns = @JoinColumn(name = "ALBUM_id"))
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_TRACK",
            joinColumns = @JoinColumn(name = "ORDER_id"),
            inverseJoinColumns = @JoinColumn(name = "TRACK_id"))
    private List<Track> tracks = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "price = " + price + ", " +
                "customer = " + customer + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return 737800560;
    }
}
