package com.bilgeadam.elasticdemo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(indexName = "mystudent")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student
{
	@Id
	private String id;

	@Field(name = "stdName")
	private String name;

	private int number;
}