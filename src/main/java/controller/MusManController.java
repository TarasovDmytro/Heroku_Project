package controller;

import entities.Album;
import entities.Artist;
import entities.Customer;
import entities.Order;
import entities.Track;
import services.AlbumService;
import services.CustomerService;
import services.OrderService;
import services.TrackService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusManController {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    AlbumService albumService = new AlbumService();
    CustomerService customService = new CustomerService();
    TrackService trackService = new TrackService();
    OrderService orderService = new OrderService();

    public void run() {

        musicInit();
        Customer customer = customerInit();

        String action = "";
        while (!action.equals("0")) {

            System.out.println("""

                     Select an action, please:
                     _______________________________________
                     enter 1  if you want to create order
                     enter 2  if you want to create album
                     enter 3  if you want to see your orders
                     enter 4  if you want to see all tracks
                     enter 5  if you want to see all albums
                     _______________________________________
                    """);

            try {
                action = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n");

            switch (action) {
                case "1" -> this.createOrder(customer);
                case "2" -> createAlbum(bufferedReader);
                case "3" -> readOrdersOfCustomer(customer);
                case "4" -> readAllTracks();
                case "5" -> readAllAlbums();
                case "0" -> System.out.println("Thank you, bye");
                default -> System.out.println("Something wrong, please try again");
            }
        }
    }

    private void createOrder(Customer customer) {

        Order order = new Order();
        order.setCustomer(customer);
        orderService.createOrder(order);

        String action = "";
        while (!action.equals("0")) {

            System.out.println("""

                     Select an action, please:
                     _______________________________________
                     enter 1  if you want to add the album to order
                     enter 2  if you want to add track to order
                     enter 0  if you have finished placing your order
                     _______________________________________
                    """);

            try {
                action = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n");

            switch (action) {
                case "1" -> order = addAlbumToOrder(order);
                case "2" -> order = addTrackToOrder(order);
                case "0" -> System.out.println("Thank you, order is ready");
                default -> System.out.println("Something wrong, please try again");
            }
        }
    }

    private Order addAlbumToOrder(Order order) {

        String albumId = "";
        List<Album> newAlbums = new ArrayList<>();
        while (!albumId.equals("0")) {

            System.out.println("""
                                        
                    If you've finished add albums, enter 0,
                    else enter the id of the album you are interested in
                    from the proposed list:
                                        
                    """);
            readAllAlbums();
            try {
                albumId = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!albumId.equals("0")) {
                Album album = getAlbumById(Integer.parseInt(albumId));
                newAlbums.add(album);
            }
        }
        return orderService.addAlbumsToOrder(order, newAlbums);
    }

    private void readAllAlbums() {

        albumService.readAllAlbums();
    }

    private Album getAlbumById(int albumId) {

        return albumService.getAlbumById(albumId);
    }

    private Order addTrackToOrder(Order order) {

        String trackId = "";
        List<Track> newTracks = new ArrayList<>();
        while (!trackId.equals("0")) {

            System.out.println("""
                                        
                    If you've finished add tracks, enter 0,
                    else enter the id of the track you are interested in
                    from the proposed list:
                                        
                    """);
            readAllTracks();
            try {
                trackId = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!trackId.equals("0")) {
                Track track = getTrackById(Integer.parseInt(trackId));
                newTracks.add(track);
            } else {
                order = orderService.addTracksToOrder(order, newTracks);
            }
        }
        return order;
    }

    private void readAllTracks() {

        trackService.readAllTracks();
    }

    private Track getTrackById(int trackId) {

        return trackService.getTrackById(trackId);
    }

    private void createAlbum(BufferedReader bufferedReader) {

        boolean state = false;
        Album album = null;
        while (!state) {
            System.out.println("Please, enter the name for your album");
            String title = null;
            try {
                title = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            album = new Album(title);
            state = albumService.createAlbum(album);
        }

        String trackId = "";
        List<Track> newTracks = new ArrayList<>();

        while (!trackId.equals("0")) {

            System.out.println("""
                                        
                    If you've finished shaping the album, enter 0,
                    else enter the id of the track you are interested in
                    from the proposed list:
                                        
                    """);

            List<Track> tracks = getAllTracks();
            tracks.forEach(System.out::println);

            try {
                trackId = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!trackId.equals("0")) {
                Track newTrack = getTrackById(Integer.parseInt(trackId));
                newTracks.add(newTrack);
            } else {
                System.out.println(newTracks);
                album = albumService.addTracksToAlbum(album, newTracks);
            }
        }
    }

    private List<Track> getAllTracks() {

        return trackService.getAllTracks();
    }


    private void readOrdersOfCustomer(Customer customer) {

        customService.readOrdersOfCustomer(customer);
    }

    private Customer customerInit() {

        System.out.println("\nPlease log in to the system\n");
        System.out.println("Enter your full name:");
        String fullName = null;
        try {
            fullName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nEnter your email:");
        String email = null;
        try {
            email = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nEnter your address:");
        String address = null;
        try {
            address = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(fullName, email, address);
        customer = customService.customerInit(customer);
        System.out.println("\nWelcome to the system " + customer);
        return customer;
    }

    private void musicInit() {

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Freddy Mercury"));
        artists.add(new Artist("Joan Stingray"));
        artists.add(new Artist("Sting"));
        artists.add(new Artist("Elton Jon"));
        artists.add(new Artist("Sher"));

        Artist artist;
        Track track = new Track();
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            artist = artists.get(random.nextInt(5));
            track.setTitle("Track" + (i + 1));
            track.setPrice(50 + random.nextInt(50));
            track.setArtist(artist);
            trackService.createInstance(track);
        }
    }
}
