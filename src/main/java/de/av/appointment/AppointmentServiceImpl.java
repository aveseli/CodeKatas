package de.av.appointment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adnan on 08.05.17.
 */
public class AppointmentServiceImpl implements AppointmentService {


    @Override
    public List<Appointment> evaluateFreeSlots(List<Appointment> appointments) {
        final List<Appointment> result = new ArrayList<>();

        for (Appointment candidate : appointments) {
            if (result.stream().noneMatch(e -> e.getTimeSlot().overlaps(candidate.getTimeSlot()))) {
                result.add(candidate);
            }
        }
        return result;
    }
}
