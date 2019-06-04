package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.topic.domain.Topic;

public interface TopicRepository extends Repository<Topic, Long> {	
	public List<Topic> findAll();
}
