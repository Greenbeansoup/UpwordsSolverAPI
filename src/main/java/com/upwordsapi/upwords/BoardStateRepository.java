package com.upwordsapi.upwords;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardStateRepository extends JpaRepository<BoardState, Long>{
    Optional<BoardState> findById(Long id);
}
