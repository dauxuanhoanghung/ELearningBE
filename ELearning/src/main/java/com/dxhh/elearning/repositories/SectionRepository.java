package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer>, JpaSpecificationExecutor<Section> {
}
