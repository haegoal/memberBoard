package com.example.memberboard.service;

import com.example.memberboard.dto.MemberDTO;
import com.example.memberboard.entity.MemberEntity;
import com.example.memberboard.entity.MemberFileEntity;
import com.example.memberboard.repository.MemberFileRepository;
import com.example.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFileRepository memberFileRepository;

    public MemberDTO findByMemberEmail(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public Long save(MemberDTO memberDTO) throws IOException {
        if(memberDTO.getMemberProfile()==null) {
            MemberEntity memberEntity = MemberEntity.toSave(memberDTO);
            return memberRepository.save(memberEntity).getId();
        }else{
        MemberEntity memberEntity = MemberEntity.toSaveEntityWtihFile(memberDTO);
        MemberEntity savedEntity = memberRepository.save(memberEntity);
            for (MultipartFile memberProfile:memberDTO.getMemberProfile()) {
                String originalFilename = memberProfile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "C:\\springboot_img\\" + storedFileName;
                memberProfile.transferTo(new File(savePath));
                MemberFileEntity memberFileEntity = MemberFileEntity.toSaveBoardFile(savedEntity, originalFilename, storedFileName);
                memberFileRepository.save(memberFileEntity);
            }
        return savedEntity.getId();
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword()).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }
}
