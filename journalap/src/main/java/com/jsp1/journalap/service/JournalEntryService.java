package com.jsp1.journalap.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsp1.journalap.entity.JournalEntry;
import com.jsp1.journalap.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	public void saveEntry(JournalEntry entries) {
		journalEntryRepository.save(entries);
	}

	public List<JournalEntry> getAll() {
		
		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return journalEntryRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		journalEntryRepository.deleteById(id);
	}

	public void deleteAll() {
		journalEntryRepository.deleteAll();
		
	}

}