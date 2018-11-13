package com.lxisoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.domain.Reply;
import com.lxisoft.service.dto.ReplyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import org.springframework.stereotype.Repository;

import com.lxisoft.domain.Reply;


/**
 * Spring Data  repository for the Reply entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {


	Page<Reply> findByCommentId(Pageable pageable, Long id);

	


}
