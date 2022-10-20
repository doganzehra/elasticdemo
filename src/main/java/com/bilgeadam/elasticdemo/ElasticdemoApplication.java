package com.bilgeadam.elasticdemo;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@SpringBootApplication
public class ElasticdemoApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ElasticdemoApplication.class, args);
	}

	@Bean
	public ElasticsearchClient getElasticsearchClient(@Autowired RestClient restClient)
	{
		ElasticsearchClient client = new ElasticsearchClient(new RestClientTransport(restClient, new JacksonJsonpMapper()));
		return client;
	}
}
