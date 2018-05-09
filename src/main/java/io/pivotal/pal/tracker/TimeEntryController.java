package io.pivotal.pal.tracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity<TimeEntry> delete(long l) {
        TimeEntry delete = timeEntryRepository.delete(l);
        return new ResponseEntity<>(delete, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<String> createById(@RequestBody TimeEntry timeEntryToCreate) throws JsonProcessingException {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        String valueAsString = objectMapper.writeValueAsString(timeEntry);
        return new ResponseEntity<>(valueAsString, HttpStatus.CREATED);

    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) throws JsonProcessingException {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);

    }

    public ResponseEntity<TimeEntry> read(long l) {
        TimeEntry timeEntry = timeEntryRepository.find(l);
        if(timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity update(long l, TimeEntry expected) {
        TimeEntry update = timeEntryRepository.update(l, expected);
        if(update != null) {
            return new ResponseEntity<>(update, HttpStatus.OK);
        }
        return new ResponseEntity<>(update, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<String> listAllEntries() throws JsonProcessingException {
        List<TimeEntry> list = timeEntryRepository.list();
        return new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) throws JsonProcessingException {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString(timeEntry), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody TimeEntry timeEntry) throws JsonProcessingException {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
        return new ResponseEntity<>(objectMapper.writeValueAsString(updatedTimeEntry), HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws JsonProcessingException {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        TimeEntry delete = timeEntryRepository.delete(id);
        return new ResponseEntity<>(objectMapper.writeValueAsString(delete), HttpStatus.NO_CONTENT);
    }
}
