package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeEntryRepository {

    private InMemoryTimeEntryRepository repo;

    @Autowired
    public TimeEntryRepository(InMemoryTimeEntryRepository repo) {
        this.repo = repo;
    }

    public TimeEntry create(TimeEntry any) {
        return repo.create(any);
    }

    public TimeEntry find(long l) {
        return repo.find(l);
    }

    public TimeEntry update(long eq, TimeEntry any) {
        return repo.update(eq, any);
    }

    public TimeEntry delete(long l) {
        TimeEntry timeEntry = repo.find(l);
        repo.delete(l);
        return timeEntry;
    }

    public List<TimeEntry> list() {
        return repo.list();
    }
}
