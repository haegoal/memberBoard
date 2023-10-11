package com.example.memberboard.entity;

import lombok.*;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
@Table(name = "member_file_entity")
public class MemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String originalFileName;

    @Column(length = 100)
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    public static MemberFileEntity toSaveMemberFile(MemberEntity savedEntity, String orginalFileName, String storedFileName) {
        MemberFileEntity memberFileEntity = new MemberFileEntity();
        memberFileEntity.setOriginalFileName(orginalFileName);
        memberFileEntity.setStoredFileName(storedFileName);
        memberFileEntity.setMemberEntity(savedEntity);
        return memberFileEntity;
    }
}