package com.company.enroller.controllers;

import java.util.Collection;

import com.company.enroller.model.Participant;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.*;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

    @Autowired
    MeetingService meetingService;
    @Autowired
    ParticipantService participantService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getTitles() {
        Collection<Meeting> meetings = meetingService.getAll();
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTitle(@PathVariable("id") long index) {
        Meeting meeting = meetingService.findByTitle(index);
        if (meeting == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meeting, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addMeeting(@RequestBody Meeting meeting) {
        meetingService.add(meeting);
        return new ResponseEntity<>(meeting, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> addPaticipiantToMeeting(@PathVariable("id") long meetingId, @RequestBody String userLogin) {
        Participant participant = participantService.findByLogin(userLogin);

        if(participant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Meeting meeting = meetingService.findByTitle(meetingId);
        meeting.addParticipant(participant);
        meetingService.update(meeting);
        return new ResponseEntity<>(meeting, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/{users}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMeetingUser(@PathVariable("id") long meetingId) {
        Meeting meeting = meetingService.findByTitle(meetingId);

        if(meeting == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return meeting.getParticipants().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                                                     new ResponseEntity<>(meeting.getParticipants(), HttpStatus.OK);
    }
}
