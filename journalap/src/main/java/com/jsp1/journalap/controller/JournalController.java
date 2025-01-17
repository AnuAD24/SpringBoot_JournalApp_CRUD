package com.jsp1.journalap.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.print.attribute.standard.MediaSize.Other;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp1.journalap.entity.JournalEntry;
import com.jsp1.journalap.service.JournalEntryService;
import com.mongodb.client.model.ReturnDocument;

@RestController
@RequestMapping("/journal")
public class JournalController {

	@Autowired
	private JournalEntryService journalEntryService;

//	private Map<Long, JournalEntry> entries = new HashMap<>();

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<JournalEntry> all = journalEntryService.getAll();
		if (all != null && !all.isEmpty()) {
			return new ResponseEntity<>(all, HttpStatus.OK);

		}
		return new ResponseEntity<JournalEntry>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/save")
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
		try {
			myEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(myEntry);
			return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
		if (journalEntry.isPresent()) {
			return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
		journalEntryService.deleteById(myId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteEntryById() {
		journalEntryService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("id/{myId}")
	public ResponseEntity<JournalEntry> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
		JournalEntry old = journalEntryService.findById(myId).orElse(null);
		if (old != null) {
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
			journalEntryService.saveEntry(old);
			return new ResponseEntity<>(old, HttpStatus.OK);
		}
		
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
        }
      }

