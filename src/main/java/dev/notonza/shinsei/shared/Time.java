package dev.notonza.shinsei.shared;

import java.util.concurrent.TimeUnit;

/**
 * @author gachakra
 * Created on 2020/12/20.
 */
public class Time {

    private final int value;
    private final TimeUnit timeUnit;

    public Time(int value, TimeUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    public long value() {
        return value;
    }

    public TimeUnit timeUnit() {
        return timeUnit;
    }

    @Override
    public String toString() {
        String unit = timeUnit.toString().toLowerCase();
        unit = value != 1
            ? unit
            : unit.substring(0, unit.length() - 1);

        return value + " " + unit;
    }
}
