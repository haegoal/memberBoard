package com.example.memberboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
@Table(name = "board_file_table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String originalFileName;

    @Column(length = 100)
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;

    public static BoardFileEntity toSaveBoardFile(BoardEntity savedEntity, String orginalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(orginalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(savedEntity);
        return boardFileEntity;
    }
}