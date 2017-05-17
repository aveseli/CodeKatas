package de.av.appointment;

/**
 * Created by adnan on 08.05.17.
 */
public class Appointment {

    private final String id;

    private final AppointmentTimeSlot timeSlot;


    private Appointment(String id, AppointmentTimeSlot timeSlot) {
        this.id = id;
        this.timeSlot = timeSlot;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public AppointmentTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public static class Builder {
        private String id;
        private AppointmentTimeSlot timeSlot;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTimeSlot(AppointmentTimeSlot timeSlot) {
            this.timeSlot = timeSlot;
            return this;
        }

        public Appointment build() {
            return new Appointment(this.id, this.timeSlot);
        }
    }
}
