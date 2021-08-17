package services;

import dao.ArtistDao;
import entities.Artist;

import java.util.List;

public class ArtistService {

    ArtistDao artistDao = new ArtistDao();

    public void createArtist(Artist artist) {

        artistDao.createInstance(artist);
    }

    public List<Artist> getAllArtists() {

        return artistDao.getAllInstance();
    }
}
