package com.dacky.repository;

import com.dacky.domain.Firm;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FirmRepository extends R2dbcRepository<Firm, Long> {}
