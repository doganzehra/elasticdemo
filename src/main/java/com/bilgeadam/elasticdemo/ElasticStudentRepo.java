package com.bilgeadam.elasticdemo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticStudentRepo extends ElasticsearchRepository<Student, String>
{
	public Student getByName(String name);
}