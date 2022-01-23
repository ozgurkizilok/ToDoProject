package com.todo.study.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	public String id;
	
	@Field
	public String userId;
	
	@Field
	private String content;
	
	@Field
	private Boolean completed = Boolean.FALSE;

}
