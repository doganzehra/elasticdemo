package com.bilgeadam.elasticdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "studentJPA")
public class StudentController
{
	private ElasticStudentRepo studentService;

	// http://localhost:8080/student/getAll
	@GetMapping(path = "getAll")
	public ResponseEntity<List<Student>> getAll()
	{
		List<Student> list = new ArrayList<Student>();
		studentService.findAll().iterator().forEachRemaining(std -> list.add(std));
		return ResponseEntity.ok(list);
	}

	// http://localhost:8080/student/getById/1
	@GetMapping(path = "getById/{id}")
	public ResponseEntity<Student> getById(@PathVariable(name = "id") String id)
	{
		return ResponseEntity.ok(studentService.findById(id).get());
	}

	// http://localhost:8080/student/getByName/ada
	@GetMapping(path = "getByName/{name}")
	public ResponseEntity<Student> getByName(@PathVariable(name = "name") String name)
	{
		return ResponseEntity.ok(studentService.getByName(name));
	}

	// http://localhost:8080/student/deleteAll
	@GetMapping(path = "deleteAll")
	public ResponseEntity<String> deleteAll()
	{
		studentService.deleteAll();
		return ResponseEntity.ok("OK");
	}

	// http://localhost:8080/student/save
	@PostMapping(path = "save")
	public ResponseEntity<String> save(@RequestBody Student student)
	{
		// {"name":"numan", "number":"123"}
		return ResponseEntity.ok("Başarılı -> " + studentService.save(student));
	}
}