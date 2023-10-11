package com.example.memberboard.repository;


import com.example.memberboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findByBoardWriterContaining(String q, Pageable pageable);

    Page<BoardEntity> findByBoardTitleContaining(String q, Pageable pageable);


    @Modifying // insert, update, delete 할 때
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id =:id")
//    @Query(value = "update board_table set board_hits=board_hits+1 where id = :id", nativeQuery = true)
    void updateHits(@Param("id") Long id);
}
