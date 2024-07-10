package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.dto.NoticeDto;

import java.util.List;

public interface BoardService {
    // Board
    void post(BoardDto boardDto);

    void modify(BoardDto boardDto);

    void delete(int id);

    List<BoardDto> getBoardList();

    BoardDto getBoard(int id);

    // Notice
    void post(NoticeDto noticeDto);

    void modify(NoticeDto noticeDto);

    List<NoticeDto> getNoticeBoardList();

    NoticeDto getNoticeBoard(int id);
}
