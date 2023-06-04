package com.zleco.boardspring.repository;

import com.zleco.boardspring.entity.PopualrSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopluarSearchRepository extends JpaRepository<PopualrSearchEntity, String> {
}
