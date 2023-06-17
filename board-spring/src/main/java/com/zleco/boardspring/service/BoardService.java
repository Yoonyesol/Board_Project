package com.zleco.boardspring.service;

import com.zleco.boardspring.dto.ResponseDto;
import com.zleco.boardspring.entity.BoardEntity;
import com.zleco.boardspring.entity.PopualrSearchEntity;
import com.zleco.boardspring.repository.BoardRepository;
import com.zleco.boardspring.repository.PopluarSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    @Autowired BoardRepository boardRepository;
    @Autowired PopluarSearchRepository popluarSearchRepository;

    public ResponseDto<List<BoardEntity>> getTop3(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));

        try {
            boardList =  boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<BoardEntity>> getList(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try {
            boardList =  boardRepository.findByOrderByBoardWriteDateDesc();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<PopualrSearchEntity>> getPopularsearchList(){
        List<PopualrSearchEntity> popularSearchList = new ArrayList<PopualrSearchEntity>();

        try {
            popularSearchList =  popluarSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("Success", popularSearchList);
    }

    public ResponseDto<List<BoardEntity>> getSearchList(String boardTitle){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try {
            boardList =  boardRepository.findByBoardTitleContains(boardTitle);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("Success", boardList);
    }
}
