package de.av.appointment;

import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by adnan on 08.05.17.
 */
class AppointmentTimeSlotTest {

    private final LocalDateTime now = LocalDateTime.now();

    @Test
    void testCreationValidRange() throws Exception {
        final AppointmentTimeSlot timeSlot = AppointmentTimeSlot.newBuilder()
                .withStartDateTime(now)
                .withEndDateTime(now.plusHours(1))
                .build();
    }

    @Test
    void testNonOverlappingSlots() {
        AppointmentTimeSlot one = AppointmentTimeSlot.newBuilder()
                .withStartDateTime(now)
                .withEndDateTime(now.plusHours(4))
                .build();

        AppointmentTimeSlot two = AppointmentTimeSlot.newBuilder()
                .withStartDateTime(now.plusHours(5))
                .withEndDateTime(now.plusHours(10))
                .build();

        assertFalse(one.overlaps(two));
        assertFalse(two.overlaps(one));
    }

    @Test
    void testOverlappingSlots() {
        AppointmentTimeSlot one = AppointmentTimeSlot.newBuilder()
                .withStartDateTime(now)
                .withEndDateTime(now.plusHours(4))
                .build();

        AppointmentTimeSlot two = AppointmentTimeSlot.newBuilder()
                .withStartDateTime(now.plusHours(1))
                .withEndDateTime(now.plusHours(4))
                .build();

        assertTrue(one.overlaps(two));
        assertTrue(two.overlaps(one));
    }
}