package com.example.memberboard.repository;

import com.example.memberboard.entity.MemberFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFileRepository extends JpaRepository<MemberFileEntity, Long> {
}
