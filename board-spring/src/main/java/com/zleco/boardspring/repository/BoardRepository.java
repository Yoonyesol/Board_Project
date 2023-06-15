package com.zleco.boardspring.repository;

import com.zleco.boardspring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(Date boardWriteDate);

    public List<BoardEntity> findByOrderByBoardWriteDateDesc();
}
