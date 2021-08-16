package entities;

import entities.Album;
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

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ARTISTS")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTIST_id")
    private int id;

    @Column(name = "ARTIST_name")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ARTIST_ALBUM",
            joinColumns = @JoinColumn(name = "ARTIST_id"),
            inverseJoinColumns = @JoinColumn(name = "ALBUM_id"))
    private List<Album> albums = new ArrayList<>();


    public Artist(String title) {
        this.title = title;
    }

    @Override
    public String toString() {

        ArrayList<String> strAlbums = albums.stream().map(Album::getTitle).collect(Collectors.toCollection(ArrayList::new));
        return "Artist{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", albums=" + strAlbums +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;

        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return 787003919;
    }
}
