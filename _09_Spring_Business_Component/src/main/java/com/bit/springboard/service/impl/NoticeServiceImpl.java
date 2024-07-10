package com.bit.springboard.service.impl;

import com.bit.springboard.dao.NoticeDao;
import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.dto.NoticeDto;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoticeBoardService")
public class NoticeServiceImpl implements BoardService {
    private NoticeDao noticeDao;

    @Autowired
    public NoticeServiceImpl(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public void post(BoardDto boardDto) {

    }

    @Override
    public void modify(BoardDto boardDto) {

    }

    @Override
    public void delete(int id) {
        noticeDao.delete(id);
    }

    @Override
    public List<BoardDto> getBoardList() {
        return List.of();
    }

    @Override
    public BoardDto getBoard(int id) {
        return null;
    }

    @Override
    public void post(NoticeDto noticeDto) {
        noticeDao.post(noticeDto);
    }

    @Override
    public void modify(NoticeDto noticeDto) {
        noticeDao.modify(noticeDto);
    }

    @Override
    public List<NoticeDto> getNoticeBoardList() {
        return noticeDao.getNoticeBoardList();
    }

    @Override
    public NoticeDto getNoticeBoard(int id) {
        return noticeDao.getNoticeBoard(id);
    }
}
