package de.av.appointment;

import java.util.List;

/**
 * Created by adnan on 08.05.17.
 */
public interface AppointmentService {

    List<Appointment> evaluateFreeSlots(List<Appointment> appointments);
}
