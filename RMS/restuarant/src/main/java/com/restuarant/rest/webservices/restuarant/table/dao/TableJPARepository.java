package com.restuarant.rest.webservices.restuarant.table.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restuarant.rest.webservices.restuarant.modal.Table;

@Repository
public interface TableJPARepository extends JpaRepository<Table, Integer> {

	public Optional<Table> findByRestId(int restId);
}
