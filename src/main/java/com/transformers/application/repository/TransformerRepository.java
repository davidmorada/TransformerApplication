package com.transformers.application.repository;

import com.transformers.application.entity.Transformer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerRepository extends CrudRepository<Transformer, Long> {
    Transformer findByTransformerID(Long transformerID);
}
