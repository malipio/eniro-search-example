package com.github.malipio.eniro.search.service;

import org.springframework.data.repository.CrudRepository;

import com.github.malipio.eniro.search.domain.SearchObject;

public interface SearchObjectRepository extends CrudRepository<SearchObject, Long> {

}
