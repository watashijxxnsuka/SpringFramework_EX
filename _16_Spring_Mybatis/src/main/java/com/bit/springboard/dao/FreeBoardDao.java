package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// JDBC Template 사용방식 2
// JDBCTemplate 을 필드로 선언하고 의존성을 주입받아서 사용하는 방식
@Repository
public class FreeBoardDao {
    private SqlSessionTemplate mybatis;

    @Autowired
    public FreeBoardDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mybatis = sqlSessionTemplate;
    }


    public void post(BoardDto boardDto) {
        System.out.println("FreeBoardDao post method called");

        mybatis.insert("FreeBoardDao.post", boardDto);

        System.out.println("FreeBoardDao post method end");
    }


    public void modify(BoardDto boardDto) {
        System.out.println("FreeBoardDao modify method called");

        mybatis.update("FreeBoardDao.modify", boardDto);

        System.out.println("FreeBoardDao modify method end");
    }


    public List<BoardDto> getBoardList() {
        System.out.println("FreeBoardDao getBoardList method called");

        List<BoardDto> boardList = new ArrayList<>();

        boardList = mybatis.selectList("FreeBoardDao.get_board_list");

        System.out.println("FreeBoardDao getBoardList method end");
        return boardList;
    }


    public void delete(int id) {
        System.out.println("FreeBoardDao delete method called");

        mybatis.delete("FreeBoardDao.delete", id);

        System.out.println("FreeBoardDao delete method end");
    }


    public BoardDto getBoard(int id) {
        System.out.println("FreeBoardDao getBoard method called");

        BoardDto boardDto = new BoardDto();

        boardDto = mybatis.selectOne("FreeBoardDao.get_board", id);

        System.out.println("FreeBoardDao getBoard method end");
        return boardDto;
    }
}
