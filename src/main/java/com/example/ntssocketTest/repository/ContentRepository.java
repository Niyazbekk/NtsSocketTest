package com.example.ntssocketTest.repository;

import com.example.ntssocketTest.model.ContentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<ContentEntity,Long> {
    List<ContentEntity> getContentEntitiesBy();
}
