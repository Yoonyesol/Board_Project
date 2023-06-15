package com.zleco.boardspring.controller;

import com.zleco.boardspring.dto.ResponseDto;
import com.zleco.boardspring.entity.BoardEntity;
import com.zleco.boardspring.entity.PopualrSearchEntity;
import com.zleco.boardspring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @Autowired BoardService boardService;
    @GetMapping("/top3")
    public ResponseDto<List<BoardEntity>> getTop3() {
        return boardService.getTop3();
    }

    @GetMapping("/list")
    public ResponseDto<List<BoardEntity>> getList() {
        return boardService.getList();
    }

    @GetMapping("/popularsearchList")
    public ResponseDto<List<PopualrSearchEntity>> getPopularsearchList() {
        return boardService.getPopularsearchList();
    }
}