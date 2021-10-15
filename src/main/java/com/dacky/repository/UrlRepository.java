package com.dacky.repository;

import com.dacky.domain.Url;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UrlRepository extends R2dbcRepository<Url, Long> {}
