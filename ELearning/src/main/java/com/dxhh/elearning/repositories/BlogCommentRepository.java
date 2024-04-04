package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer>, JpaSpecificationExecutor<BlogComment> {
}
