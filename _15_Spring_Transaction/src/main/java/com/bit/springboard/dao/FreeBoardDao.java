package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// JDBC Template 사용방식 2
// JDBCTemplate 을 필드로 선언하고 의존성을 주입받아서 사용하는 방식
@Repository
public class FreeBoardDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FreeBoardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시글 등록 쿼리
    private final String POST = "INSERT INTO FREEBOARD(ID, TITLE, CONTENT, WRITER_ID) VALUES(?,?,?,?)";

    public void post(BoardDto boardDto) {
        System.out.println("FreeBoardDao post method called");

        jdbcTemplate.update(POST, boardDto.getId(),boardDto.getTitle(), boardDto.getContent(), boardDto.getWRITER_ID());

        System.out.println("FreeBoardDao post method end");
    }


    // 게시물 수정 쿼리
    private final String MODIFY = "UPDATE FREEBOARD SET TITLE = ?, CONTENT = ?, MODDATE = ? WHERE ID = ?";

    public void modify(BoardDto boardDto) {
        System.out.println("FreeBoardDao modify method called");

        jdbcTemplate.update(MODIFY, boardDto.getTitle(), boardDto.getContent(), boardDto.getModdate().toString(), boardDto.getId());

        System.out.println("FreeBoardDao modify method end");
    }


    // 게시글 가져오는 쿼리
    private final String GET_BOARD_LIST = "SELECT F.ID, F.TITLE, F.CONTENT, M.NICKNAME, F.WRITER_ID, F.REGDATE, F.MODDATE, F.CNT FROM FREEBOARD F JOIN MEMBER M ON F.WRITER_ID = M.ID";

    public List<BoardDto> getBoardList() {
        System.out.println("FreeBoardDao getBoardList method called");

        List<BoardDto> boardList = new ArrayList<>();

        boardList = jdbcTemplate.query(GET_BOARD_LIST, new BoardRowMapper());

        System.out.println("FreeBoardDao getBoardList method end");
        return boardList;
    }


    // 게시글 삭제
    private final String DELETE = "DELETE FROM FREEBOARD WHERE ID = ?";

    public void delete(int id) {
        System.out.println("FreeBoardDao delete method called");

        jdbcTemplate.update(DELETE, id);

        System.out.println("FreeBoardDao delete method end");
    }


    // 특정 id 의 게시글 하나만 조회
    private final String GET_BOARD = "SELECT F.ID, F.TITLE, F.CONTENT, M.NICKNAME, F.WRITER_ID, F.REGDATE, F.MODDATE, F.CNT FROM FREEBOARD F JOIN MEMBER M ON F.WRITER_ID = M.ID WHERE F.ID = ?";

    public BoardDto getBoard(int id) {
        System.out.println("FreeBoardDao getBoard method called");

        BoardDto boardDto = new BoardDto();

        // queryForObject 의 두번째 매개변수는 Object 배열 형태여야한다.
        Object[] args = {id};

        boardDto = jdbcTemplate.queryForObject(GET_BOARD, args, new BoardRowMapper());

        System.out.println("FreeBoardDao getBoard method end");
        return boardDto;
    }
}
