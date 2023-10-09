package com.example.memberboard.dto;

import com.example.memberboard.entity.BoardEntity;
import com.example.memberboard.util.UtilClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String createdAt;
    private int boardHits;
    private int fileAttached;
    private List<MultipartFile> boardFile;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static BoardDTO toDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setCreatedAt(UtilClass.dateTimeFormat(boardEntity.getCreatedAt()));
//        if (boardEntity.getFileAttached() == 1) {
//            for (BoardFileEntity boardFileEntity: entity.getBoardFileEntityList()) {
//                boardDTO.getOriginalFileName().add(boardFileEntity.getOriginalFileName());
//                boardDTO.getStoredFileName().add(boardFileEntity.getStoredFileName());
//
//            }
//            boardDTO.setFileAttached(1);
//        }else{
//            boardDTO.setFileAttached(0);
//        }
        return boardDTO;
    }
}
