package services;

import dao.TrackDao;
import entities.Track;
import org.hibernate.Hibernate;

import java.util.List;

public class TrackService {

    TrackDao trackDao = new TrackDao();

    public void createInstance(Track track) {

        trackDao.createInstance(track);
    }

    public Track getTrackById(int trackId) {

        Hibernate.initialize(trackDao.getInstanceById(trackId));
        return trackDao.getInstanceById(trackId);
    }

    public List<Track> getAllTracks() {

        return trackDao.getAllInstance();
    }

    public void readAllTracks() {

        List<Track> tracks = trackDao.getAllInstance();
        System.out.println("\nWe have the following tracks:\n");
        tracks.forEach(System.out::println);
    }
}
