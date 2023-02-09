package com.superkassa.testtask.repository;

import com.superkassa.testtask.entity.EntityDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryInterface extends JpaRepository<EntityDB, Long> {

}
