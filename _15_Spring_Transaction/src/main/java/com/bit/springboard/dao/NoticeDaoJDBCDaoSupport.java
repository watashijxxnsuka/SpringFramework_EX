package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDaoJDBCDaoSupport extends JdbcDaoSupport {

    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    // 공지사항 등록
    private final String POST = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID) VALUES(?,?,?)";

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao post method called");

        getJdbcTemplate().update(POST, boardDto.getTitle(), boardDto.getContent(), boardDto.getWRITER_ID());

        System.out.println("NoticeDao post method end");
    }
    
    // 공지사항 수정
    private final String MODIFY = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, MODDATE = ? WHERE ID = ?";

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao modify method called");

        getJdbcTemplate().update(MODIFY, boardDto.getTitle(), boardDto.getContent(), boardDto.getModdate().toString(), boardDto.getId());

        System.out.println("NoticeDao modify method end");
    }
    
    // 공지사항 가져오는 쿼리
    private final String GET_NOTICE_BOARD_LIST = "SELECT N.ID, N.TITLE, N.CONTENT, N.WRITER_ID, M.NICKNAME, N.REGDATE, N.MODDATE, N.CNT FROM NOTICE N JOIN MEMBER M ON N.WRITER_ID = M.ID";

    public List<BoardDto> getNoticeBoardList() {
        System.out.println("NoticeDao getNoticeBoardList method called");

        List<BoardDto> noticeDtoList = new ArrayList<>();

        noticeDtoList = getJdbcTemplate().query(GET_NOTICE_BOARD_LIST, new BoardRowMapper());

        System.out.println("NoticeDao getNoticeBoardList method end");
        return noticeDtoList;
    }

    private final String DELETE = "DELETE FROM NOTICE WHERE ID = ?";

    public void delete(int id) {
        System.out.println("NoticeDao delete method called");

        getJdbcTemplate().update(DELETE, id);

        System.out.println("NoticeDao delete method end");
    }

    private final String GET_NOTICE_BOARD = "SELECT N.ID, N.TITLE, N.CONTENT, N.WRITER_ID, M.NICKNAME, N.REGDATE, N.MODDATE, N.CNT FROM NOTICE N JOIN MEMBER M ON N.WRITER_ID = M.ID WHERE N.ID = ?";

    public BoardDto getNoticeBoard(int id) {
        System.out.println("NoticeDao getNoticeBoard method called");

        BoardDto boardDto = new BoardDto();

        Object[] args = {id};

        boardDto =  getJdbcTemplate().queryForObject(GET_NOTICE_BOARD, args, new BoardRowMapper());

        System.out.println("NoticeDao getNoticeBoard method end");
        return boardDto;
    }

}
