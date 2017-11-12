package com.lab5;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeClass {
    public TimeClass(String entry)
    {
        zone = entry;
    };

    String zone;
    public String getZone(){
        return this.zone;
    }

    String getTime(){

        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.of(zone);
        ZonedDateTime dateAndTime = ZonedDateTime.ofInstant(now, zoneId);

        return DateTimeFormatter.ofPattern("HH:mm:ss Z").format(dateAndTime);
    };
}
