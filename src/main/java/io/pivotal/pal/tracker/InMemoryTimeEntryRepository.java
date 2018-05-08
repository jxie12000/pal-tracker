package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class InMemoryTimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long id = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        // Generate random ID.
        timeEntryMap.put(this.id, timeEntry);
        timeEntry.setId(this.id);
        this.id++;
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntryMap.put(id, timeEntry);
        timeEntry.setId(id);
        return timeEntry;
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<>();
        timeEntryMap.keySet().stream().forEach(x -> timeEntries.add(
                timeEntryMap.get(x)
        ));
        return timeEntries;
    }

    public void delete(long id) {
        timeEntryMap.remove(id);
    }

}
