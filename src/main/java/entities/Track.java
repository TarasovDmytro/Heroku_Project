package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@BatchSize(size = 50)
@Table(name = "TRACKS")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRACK_id")
    private int id;

    @Column(name = "TRACK_title")
    private String title;

    @Column(name = "TRACK_price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Artist artist;


    public Track(String title, double price, Artist artist) {
        this.title = title;
        this.price = price;
        this.artist = artist;
    }

    public Track(Track track) {

        this.id = track.getId();
        this.title = track.getTitle();
        this.price = track.getPrice();
        this.artist = track.getArtist();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "price = " + price + ", " +
                "artist = " + artist.getTitle() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Track track = (Track) o;

        return Objects.equals(id, track.id);
    }

    @Override
    public int hashCode() {
        return 752605432;
    }
}
