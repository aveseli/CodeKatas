package de.av;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by adnan on 13.05.17.
 */
public class KataLagsTest {

    @Test
    public void testThatOneRequestIsOutputted() {
        KataLagsRequest request = KataLagsRequest.createKataLagsRequest("A01", 0, 2, 10);

        KataLagsReservationSystem reservation = new KataLagsReservationSystem();
        reservation.addRequest(request);

        final List<KataLagsRequest> bestRequests = reservation.calculateBestCombination();
        Assert.assertTrue(bestRequests.contains(request));
    }

    private static class KataLagsRequest {

        private final String id;
        private final int start;
        private final int duration;
        private final int price;

        private KataLagsRequest(String id, int start, int duration, int price) {
            this.id = id;
            this.start = start;
            this.duration = duration;
            this.price = price;
        }

        public static KataLagsRequest createKataLagsRequest(String id, int start, int duration, int price) {
            return new KataLagsRequest(id, start, duration, price);
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

    private class KataLagsReservationSystem {
        private final List<KataLagsRequest> requests;

        private KataLagsReservationSystem() {
            this.requests = new ArrayList<>();
        }

        private void addRequest(KataLagsRequest request) {
            requests.add(request);
        }

        private List<KataLagsRequest> calculateBestCombination() {
            return requests;
        }
    }
}
