package com.company.enroller.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

    @Autowired
    MeetingService meetingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getTitle() {
        Collection<Meeting> meetings = meetingService.getAll();
        return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getTitle(@PathVariable("id") String title) {
//        Meeting meeting = meetingService.findByTitle(title);
//        if (meeting == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<?> registerParticipant(@RequestBody Meeting meeting) {
//        Meeting foundParticipant = meetingService.findByLogin(meeting.getLogin());
//        if(foundParticipant != null) {
//            return new ResponseEntity<>("Unable to create. A participant with login " +
//                    meeting.getLogin() + " already exist.", HttpStatus.CONFLICT);
//        }
//        meetingService.add(meeting);
//        return new ResponseEntity<>(meeting, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteParticipent(@PathVariable("id") String login) {
//        Meeting foundParticipant = meetingService.findByLogin(login);
//        if (foundParticipant == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        meetingService.delete(foundParticipant);
//        return new ResponseEntity<Participant>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@PathVariable("id") String login, @RequestBody Meeting updatedParticipant) {
//        Meeting participant = meetingService.findByLogin(login);
//        if(participant == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        participant.setPassword(updatedParticipant.getPassword());
//        meetingService.update(participant);
//        return new ResponseEntity<>(participant, HttpStatus.OK);
//    }
}
