package com.lab5;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class timeClass {
    public timeClass(String entry)
    {
        Zone = entry;
    };

    String Zone;
    public String getZone(){
        return this.Zone;
    }

    String getTime(){

        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.of(Zone);
        ZonedDateTime dateAndTime = ZonedDateTime.ofInstant(now, zoneId);

        return DateTimeFormatter.ofPattern("HH:mm:ss Z").format(dateAndTime);
    };
}
