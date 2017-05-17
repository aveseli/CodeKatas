package de.av.reservation;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by adnan on 13.05.17.
 */
public class ReservationTest {

    @Test
    public void testThatOneRequestIsOutputted() {
        Reservation request = Reservation.createKataLagsRequest("A01", 0, 2, 10);

        ReservationSystem reservation = new ReservationSystem();
        reservation.addRequest(request);

        final List<Reservation> bestRequests = reservation.calculateBestCombination();
        assertTrue(bestRequests.contains(request));
    }

    private static class Reservation {

        private final String id;
        private final int start;
        private final int duration;
        private final int price;

        private Reservation(String id, int start, int duration, int price) {
            this.id = id;
            this.start = start;
            this.duration = duration;
            this.price = price;
        }

        public static Reservation createKataLagsRequest(String id, int start, int duration, int price) {
            return new Reservation(id, start, duration, price);
        }

        @Override
        public String toString() {
            return "KataLagsRequest{" +
                    "id='" + id + '\'' +
                    ", start=" + start +
                    ", duration=" + duration +
                    ", price=" + price +
                    '}';
        }
    }

    private class ReservationSystem {
        private final List<Reservation> requests;

        private ReservationSystem() {
            this.requests = new ArrayList<>();
        }

        private void addRequest(Reservation request) {
            requests.add(request);
        }

        private List<Reservation> calculateBestCombination() {
            return requests;
        }
    }
}
