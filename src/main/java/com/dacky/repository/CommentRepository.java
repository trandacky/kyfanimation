package com.dacky.repository;

import com.dacky.domain.Comment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CommentRepository extends R2dbcRepository<Comment, Long> {}
