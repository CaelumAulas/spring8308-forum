package br.com.alura.forum.controller.dto.input;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;

public class TopicSearchInputDto {
	private TopicStatus status;
	private String categoryName;
	
	public TopicStatus getStatus() {
		return status;
	}
	public void setStatus(TopicStatus status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Specification<Topic> buildSpecification() {
		Specification<Topic> specification = new Specification<Topic>() {
			@Override
			public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				ArrayList<Predicate> predicates = new ArrayList<>();
				if (getStatus() != null) {
					Predicate equalStatus = criteriaBuilder.equal(root.get("status"), getStatus());
					predicates.add(equalStatus);
				}
				
				if (getCategoryName() != null) {
					Predicate equalCategoryName = 
							criteriaBuilder.equal(root.get("course").get("subcategory").get("category").get("name"),
									getCategoryName());
					predicates.add(equalCategoryName);
				}
				Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
				return predicate;
			}
			
		};
		return specification;
	}
}
