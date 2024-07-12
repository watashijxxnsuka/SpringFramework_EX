package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDao{
    private SqlSessionTemplate mybatis;

    @Autowired
    public NoticeDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mybatis = sqlSessionTemplate;
    }


    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao post method called");

        mybatis.insert("noticeDao.post", boardDto);

        System.out.println("NoticeDao post method end");
    }


    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao modify method called");

        mybatis.update("noticeDao.modify", boardDto);

        System.out.println("NoticeDao modify method end");
    }


    public List<BoardDto> getNoticeBoardList() {
        System.out.println("NoticeDao getNoticeBoardList method called");

        return mybatis.selectList("noticeDao.get_board_list");
    }


    public void delete(int id) {
        System.out.println("NoticeDao delete method called");

        mybatis.delete("noticeDao.delete", id);

        System.out.println("NoticeDao delete method end");
    }


    public BoardDto getNoticeBoard(int id) {
        System.out.println("NoticeDao getNoticeBoard method called");

        return mybatis.selectOne("noticeDao.get_board", id);
    }

}
