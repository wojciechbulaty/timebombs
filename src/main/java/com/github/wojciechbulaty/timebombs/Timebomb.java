package com.github.wojciechbulaty.timebombs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;

import static java.lang.String.format;

public class Timebomb {
    private final Clock clock;

    private Timebomb(Clock clock) {
        this.clock = clock;
    }

    public static Timebomb timebomb() {
        return new Timebomb(Clock.systemUTC());
    }

    public void blowUpOn(int year, int month, int dayOfMonth, String explanation)  {
        try {
            blowUpOn(new SimpleDateFormat("yyyy-MM-dd").parse(format("%s-%s-%s", year, month, dayOfMonth)), explanation);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void blowUpOn(Date dateToBlowUp, String explanation) {
        if (clock.instant().compareTo(dateToBlowUp.toInstant()) > 0) {
            throw new AssertionError("Requested timebomb, " + explanation);
        }
    }
}
