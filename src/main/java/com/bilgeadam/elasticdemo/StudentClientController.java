package com.bilgeadam.elasticdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest.Builder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.util.ObjectBuilder;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "student")
public class StudentClientController
{
	private ElasticsearchClient elasticClient;

	// http://localhost:8080/student/getAll
	@GetMapping(path = "getAll")
	public ResponseEntity<List<Student>> getAll()
	{
		System.err.println("asdsad");
		List<Student> list = new ArrayList<Student>();
//		Function<Builder, ObjectBuilder<SearchRequest>> func = new Function<SearchRequest.Builder, ObjectBuilder<SearchRequest>>()
//		{
//			@Override
//			public ObjectBuilder<SearchRequest> apply(Builder t)
//			{
//				return t.index("mystudent");
//			}
//		};
		Function<Builder, ObjectBuilder<SearchRequest>> func = new Function<SearchRequest.Builder, ObjectBuilder<SearchRequest>>()
		{
			@Override
			public ObjectBuilder<SearchRequest> apply(Builder bldr)
			{
				return bldr.index("mystudent").query(q -> q.bool(b -> b.must(m -> m.term(t -> t.field("stdName").value(FieldValue.of("hasan"))))));
			}
		};
		SearchRequest req = SearchRequest.of(func);
		try
		{
			SearchResponse<Student> res = elasticClient.search(req, Student.class);
			List<Hit<Student>> hitList = res.hits().hits();
			for (Hit<Student> hit : hitList)
			{
				list.add(hit.source());
			}
		}
		catch (ElasticsearchException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(list);
	}

//	// http://localhost:8080/student/getById/1
//	@GetMapping(path = "getById/{id}")
//	public ResponseEntity<Student> getById(@PathVariable(name = "id") String id)
//	{
//		return ResponseEntity.ok(studentService.findById(id).get());
//	}
//
//	// http://localhost:8080/student/getByName/ada
//	@GetMapping(path = "getByName/{name}")
//	public ResponseEntity<Student> getByName(@PathVariable(name = "name") String name)
//	{
//		return ResponseEntity.ok(studentService.getByName(name));
//	}
//
//	// http://localhost:8080/student/deleteAll
//	@GetMapping(path = "deleteAll")
//	public ResponseEntity<String> deleteAll()
//	{
//		studentService.deleteAll();
//		return ResponseEntity.ok("OK");
//	}

//	// http://localhost:8080/student/save
//	@PostMapping(path = "save")
//	public ResponseEntity<String> save(@RequestBody Student student)
//	{
//		// {"name":"numan", "number":"123"}
//		return ResponseEntity.ok("Başarılı -> " + studentService.save(student));
//	}
}