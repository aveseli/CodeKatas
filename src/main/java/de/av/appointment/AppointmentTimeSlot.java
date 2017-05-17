package de.av.appointment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Created by adnan on 08.05.17.
 */
public class AppointmentTimeSlot {

    private final LocalDateTime start;

    private final LocalDateTime end;


    private AppointmentTimeSlot(LocalDateTime start, LocalDateTime end) {

        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        if (start.isAfter(end))
            throw new IllegalArgumentException("Start cannot be after after.");

        final Duration durationBetweenStartAndEnd = Duration.between(start, end);
        if (durationBetweenStartAndEnd.isNegative() || durationBetweenStartAndEnd.isZero())
            throw new IllegalArgumentException("End must be gt start, otherwise we do not have a range.");

        // safe to assign since LocalDateTime is immutable and therefore modification would
        // create new instances anyway.
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(AppointmentTimeSlot other) {

        final long start1 = start.atZone(ZoneId.systemDefault()).toEpochSecond();
        final long start2 = other.getStart().atZone(ZoneId.systemDefault()).toEpochSecond();

        final long end1 = end.atZone(ZoneId.systemDefault()).toEpochSecond();
        final long end2 = other.getEnd().atZone(ZoneId.systemDefault()).toEpochSecond();

        long overlap = Math.max(0, Math.min(end1, end2) - Math.max(start1, start2));
        return overlap > 0;

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }



    public static class Builder {

        private LocalDateTime start;

        private LocalDateTime end;

        public Builder withStartDateTime(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public Builder withEndDateTime(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public AppointmentTimeSlot build() {
            return new AppointmentTimeSlot(this.start, this.end);
        }
    }

    // TODO override toString, equals and hashCode
}
