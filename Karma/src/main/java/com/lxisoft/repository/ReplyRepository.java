package com.lxisoft.repository;

import com.lxisoft.domain.Reply;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reply entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
