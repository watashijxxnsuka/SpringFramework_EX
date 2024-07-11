package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDao {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // 공지사항 등록
    private final String POST = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID) VALUES(?,?,?)";

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao post method called");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST);
            stmt.setString(1, boardDto.getTitle());
            stmt.setString(2, boardDto.getContent());
            stmt.setInt(3, boardDto.getWRITER_ID());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao post method end");
    }
    
    // 공지사항 수정
    private final String MODIFY = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, MODDATE = ? WHERE ID = ?";

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao modify method called");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(MODIFY);
            stmt.setString(1, boardDto.getTitle());
            stmt.setString(2, boardDto.getContent());
            stmt.setString(3, boardDto.getModdate().toString());
            stmt.setInt(4, boardDto.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao modify method end");
    }
    
    // 공지사항 가져오는 쿼리
    private final String GET_NOTICE_BOARD_LIST = "SELECT N.ID, N.TITLE, N.CONTENT, N.WRITER_ID, M.NICKNAME, N.REGDATE, N.MODDATE, N.CNT FROM NOTICE N JOIN MEMBER M ON N.WRITER_ID = M.ID";

    public List<BoardDto> getNoticeBoardList() {
        System.out.println("NoticeDao getNoticeBoardList method called");

        List<BoardDto> noticeDtoList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_NOTICE_BOARD_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                BoardDto boardDto = new BoardDto();

                boardDto.setId(rs.getInt("ID"));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setCnt(rs.getInt("CNT"));

                noticeDtoList.add(boardDto);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        System.out.println("NoticeDao getNoticeBoardList method end");
        return noticeDtoList;
    }

    private final String DELETE = "DELETE FROM NOTICE WHERE ID = ?";

    public void delete(int id) {
        System.out.println("NoticeDao delete method called");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao delete method end");
    }

    private final String GET_NOTICE_BOARD = "SELECT N.ID, N.TITLE, N.CONTENT, N.WRITER_ID, M.NICKNAME, N.REGDATE, N.MODDATE, N.CNT FROM NOTICE N JOIN MEMBER M ON N.WRITER_ID = M.ID WHERE N.ID = ?";

    public BoardDto getNoticeBoard(int id) {
        System.out.println("NoticeDao getNoticeBoard method called");
        BoardDto boardDto = new BoardDto();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_NOTICE_BOARD);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                boardDto.setId(rs.getInt("ID"));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setCnt(rs.getInt("CNT"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        System.out.println("NoticeDao getNoticeBoard method end");
        return boardDto;
    }

}
