package com.jsp1.journalap.entity;



import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
//import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {
	@Id
	@JsonProperty("id")
	private ObjectId id;
	@JsonProperty("Title")
	private String Title;
	@JsonProperty("Content")
	private String Content;
	@JsonProperty("date")
    private LocalDateTime date;
	
	
	

}
