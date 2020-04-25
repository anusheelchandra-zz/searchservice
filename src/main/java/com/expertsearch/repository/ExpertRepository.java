package com.expertsearch.repository;

import com.expertsearch.entity.Expert;
import com.expertsearch.model.Constants;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {

    List<Expert> findByName(String name);

    List<Expert> findByLanguage(String language, Sort sort);

    @Query(Constants.FIND_BY_DESCRIPTION_QUERY)
    List<Expert> findByDescriptionContaining(String description);

}

