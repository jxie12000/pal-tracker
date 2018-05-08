package io.pivotal.pal.tracker;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {
    @JsonProperty
    private long id;
    @JsonProperty
    private long projectId;
    @JsonProperty
    private long userId;
    @JsonProperty
    private LocalDate date;
    @JsonProperty
    private int hours;

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.userId = userId;
        this.projectId = projectId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry() {

    }


    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return id == timeEntry.id &&
                projectId == timeEntry.projectId &&
                userId == timeEntry.userId &&
                hours == timeEntry.hours &&
                Objects.equals(date, timeEntry.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectId, userId, date, hours);
    }
}
