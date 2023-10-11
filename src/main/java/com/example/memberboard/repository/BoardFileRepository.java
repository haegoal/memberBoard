package com.example.memberboard.repository;


import com.example.memberboard.entity.BoardEntity;
import com.example.memberboard.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {

    void deleteAllByBoardEntity(BoardEntity boardEntity);
}
