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
public class FreeBoardDaoJDBC {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // 게시글 등록 쿼리
    private final String POST = "INSERT INTO FREEBOARD(TITLE, CONTENT, WRITER_ID) VALUES(?,?,?)";

    public void post(BoardDto boardDto) {
        System.out.println("FreeBoardDao post method called");

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
        System.out.println("FreeBoardDao post method end");
    }

    // 게시물 수정 쿼리
    private final String MODIFY = "UPDATE FREEBOARD SET TITLE = ?, CONTENT = ?, MODDATE = ? WHERE ID = ?";

    public void modify(BoardDto boardDto) {
        System.out.println("FreeBoardDao modify method called");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(MODIFY);

            stmt.setString(1, boardDto.getTitle());
            stmt.setString(2, boardDto.getContent());
            stmt.setString(3, String.valueOf(boardDto.getModdate()));
            stmt.setInt(4, boardDto.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("FreeBoardDao modify method end");
    }

    // 게시글 가져오는 쿼리
    private final String GET_BOARD_LIST = "SELECT F.ID, F.TITLE, F.CONTENT, M.NICKNAME, F.WRITER_ID, F.REGDATE, F.MODDATE, F.CNT FROM FREEBOARD F JOIN MEMBER M ON F.WRITER_ID = M.ID";

    public List<BoardDto> getBoardList() {
        System.out.println("FreeBoardDao getBoardList method called");

        List<BoardDto> boardList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_BOARD_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                BoardDto boardDto = new BoardDto();

                boardDto.setId(rs.getInt("ID"));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
//                 java.sql.Date 타입 -> LocalDateTime 타입으로 변환
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());

//                Timestamp regdate = rs.getTimestamp("REGDATE");
//                if (regdate != null) {
//                    boardDto.setRegdate(regdate.toLocalDateTime());
//                }
//
//                Timestamp moddate = rs.getTimestamp("MODDATE");
//                if (moddate != null) {
//                    boardDto.setModdate(moddate.toLocalDateTime());
//                }

                boardDto.setCnt(rs.getInt("CNT"));

                boardList.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        System.out.println("FreeBoardDao getBoardList method end");
        return boardList;
    }

    // 게시글 삭제
    private final String DELETE = "DELETE FROM FREEBOARD WHERE ID = ?";

    public void delete(int id) {
        System.out.println("FreeBoardDao delete method called");

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
        System.out.println("FreeBoardDao delete method end");
    }
    
    // 특정 id 의 게시글 하나만 조회
    private final String GET_BOARD = "SELECT F.ID, F.TITLE, F.CONTENT, M.NICKNAME, F.WRITER_ID, F.REGDATE, F.MODDATE, F.CNT FROM FREEBOARD F JOIN MEMBER M ON F.WRITER_ID = M.ID WHERE F.ID = ?";

    public BoardDto getBoard(int id) {
        System.out.println("FreeBoardDao getBoard method called");
        BoardDto boardDto = new BoardDto();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_BOARD);
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
        System.out.println("FreeBoardDao getBoard method end");
        return boardDto;
    }
}
