package services;

import dao.AlbumDao;
import entities.Album;
import entities.Track;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlbumService {

    AlbumDao albumDao = new AlbumDao();

    public boolean createAlbum(Album album) {

        boolean state;
        List<Album> albums = albumDao.getAllInstance();
        List<String> albumNames = new ArrayList<>();

        if (albums.isEmpty()) {
            albumDao.createInstance(album);
            state = true;
        }else {

            albums.forEach(album1 -> albumNames.add(album1.getTitle()));
            if (albumNames.contains(album.getTitle())){
                System.out.println("Such an album already exists");
                state = false;
            }else {

                albumDao.createInstance(album);
                state = true;
            }
        }

        return state;
    }

    public Album addTracksToAlbum(@NotNull Album album, List<Track> newTracks) {

        List<Track> tracks = new ArrayList<>(album.getTracks());

        if (tracks.isEmpty()) {
            albumDao.addTracksToAlbum(album, newTracks);
        } else {
            for (Track track : tracks) {
                if (tracks.contains(track)) newTracks.remove(track);
            }
            albumDao.addTracksToAlbum(album, newTracks);
        }
        return album;
    }

    public Album getAlbumById(int albumId) {

        return albumDao.getInstanceById(albumId);
    }

    public void readAllAlbums() {

        List<Album> albums = albumDao.getAllInstance();
        for (Album album:albums) {
            System.out.println("\nAlbum " + album.getTitle() + ", id = " + album.getId() + ", price = " + album.getPrice());
            album.getTracks().forEach(System.out::println);
        }
    }
}
