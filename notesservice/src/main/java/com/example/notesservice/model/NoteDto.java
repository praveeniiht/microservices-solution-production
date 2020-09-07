package com.example.notesservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class NoteDto {
	private int noteId;
	private String notesDesc;
	private String author;
	private String publisher;
}
