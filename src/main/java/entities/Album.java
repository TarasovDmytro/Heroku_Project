package entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ALBUMS")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALBUM_id")
    private int id;

    @Column(name = "ALBUM_title")
    private String title;

    @Column
    private double price = 0;

    @ManyToMany(mappedBy = "albums", cascade = CascadeType.ALL)
    private List<Artist> artists = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ALBUM_TRACK",
            joinColumns = @JoinColumn(name = "ALBUM_id"),
            inverseJoinColumns = @JoinColumn(name = "TRACK_id"))
    private List<Track> tracks = new ArrayList<>();

    public Album(String title) {

        this.title = title;
    }

    @Override
    public String toString() {

        ArrayList<String> strTracks = tracks.stream().map(Track::getTitle).collect(Collectors.toCollection(ArrayList::new));
        return "__________________________________________\n" +
                getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "price = " + price + ")" +
                ",\n tracks = " + strTracks +
                "\n------------------------------------------";
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Album album = (Album) o;
//
//        return Objects.equals(id, album.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 113065996;
//    }
}
