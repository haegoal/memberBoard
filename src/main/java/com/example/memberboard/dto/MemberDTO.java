package com.example.memberboard.dto;

import com.example.memberboard.entity.MemberEntity;
import com.example.memberboard.entity.MemberFileEntity;
import com.example.memberboard.util.UtilClass;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private String createdAt;
    private int fileAttached;
    private List<MultipartFile> memberProfile;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setCreatedAt(UtilClass.dateTimeFormat(memberEntity.getCreatedAt()));
        if(memberEntity.getFileAttached()==1){
            for (MemberFileEntity memberFileEntity: memberEntity.getMemberFileEntityList()){
                memberDTO.getOriginalFileName().add(memberFileEntity.getOriginalFileName());
                memberDTO.getStoredFileName().add(memberFileEntity.getStoredFileName());
            }
            memberDTO.setFileAttached(1);
        }else{
            memberDTO.setFileAttached(0);
        }
        return memberDTO;
    }
}
