package services;

import dao.OrderDao;
import entities.Album;
import entities.Order;
import entities.Track;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderService {

    OrderDao orderDao = new OrderDao();

    public Order createOrder(Order order) {

        if (order.getCustomer() != null) {
            orderDao.createInstance(order);
        } else throw new IllegalArgumentException("The customer is not authorized");
        return order;
    }

    public Order addTracksToOrder(Order order, List<Track> newTracks) {

        List<Track> tracks = order.getTracks();
        newTracks.forEach(track -> {
            if (tracks.contains(track)) newTracks.remove(track);
        });
        return orderDao.addTracksToOrder(order, newTracks);
    }

    public Order addAlbumsToOrder(@NotNull Order order, @NotNull List<Album> newAlbums) {

        List<Album> albums = order.getAlbums();
        newAlbums.forEach(album -> {
            if (albums.contains(album)){
                newAlbums.remove(album);
            }});
        return orderDao.addAlbumsToOrder(order, newAlbums);
    }
}
