package de.av.appointment;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by adnan on 08.05.17.
 */
class AppointmentServiceTest {


    @Test
    public void testEmpty() throws Exception {
        AppointmentService appointmentService = new AppointmentServiceImpl();

        final List<Appointment> result = appointmentService.evaluateFreeSlots(Collections.emptyList());
        assertTrue(result.size() == 0);
    }


    @Test
    public void testNonOverlappingAppointments() throws Exception {
        AppointmentService appointmentService = new AppointmentServiceImpl();

        Appointment one = Appointment.newBuilder()
                .withId("1")
                .withTimeSlot(AppointmentTimeSlot.newBuilder()
                        .withStartDateTime(LocalDateTime.of(2017, 1, 1, 8, 0))
                        .withEndDateTime(LocalDateTime.of(2017, 1, 1, 12, 0))
                        .build())
                .build();


        Appointment two = Appointment.newBuilder()
                .withId("2")
                .withTimeSlot(AppointmentTimeSlot.newBuilder()
                        .withStartDateTime(LocalDateTime.of(2017, 1, 1, 13, 0))
                        .withEndDateTime(LocalDateTime.of(2017, 1, 1, 17, 0))
                        .build())
                .build();

        final List<Appointment> result = appointmentService.evaluateFreeSlots(Arrays.asList(one, two));
        assertTrue(result.contains(one));
        assertTrue(result.contains(two));
    }

    @Test
    public void testOverlappingAppointments() throws Exception {
        AppointmentService appointmentService = new AppointmentServiceImpl();

        Appointment one = Appointment.newBuilder()
                .withId("1")
                .withTimeSlot(AppointmentTimeSlot.newBuilder()
                        .withStartDateTime(LocalDateTime.of(2017, 1, 1, 8, 0))
                        .withEndDateTime(LocalDateTime.of(2017, 1, 1, 12, 0))
                        .build())
                .build();


        Appointment two = Appointment.newBuilder()
                .withId("2")
                .withTimeSlot(AppointmentTimeSlot.newBuilder()
                        .withStartDateTime(LocalDateTime.of(2017, 1, 1, 9, 0))
                        .withEndDateTime(LocalDateTime.of(2017, 1, 1, 13, 0))
                        .build())
                .build();

        final List<Appointment> result = appointmentService.evaluateFreeSlots(Arrays.asList(one, two));
        assertTrue(result.contains(one));
        assertFalse(result.contains(two));
    }

}