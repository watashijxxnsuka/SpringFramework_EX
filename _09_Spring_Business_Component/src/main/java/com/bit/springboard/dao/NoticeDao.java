package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.NoticeDto;
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

    public void post(NoticeDto noticeDto) {
        System.out.println("NoticeDao post method called");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST);
            stmt.setString(1, noticeDto.getTitle());
            stmt.setString(2, noticeDto.getContent());
            stmt.setInt(3, noticeDto.getWRITER_ID());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao post method end");
    }
    
    // 공지사항 수정
    private final String MODIFY = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, WRITER_ID = ? WHERE ID = ?";
    public void modify(NoticeDto noticeDto) {
        System.out.println("NoticeDao modify method called");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(MODIFY);
            stmt.setString(1, noticeDto.getTitle());
            stmt.setString(2, noticeDto.getContent());
            stmt.setInt(3, noticeDto.getWRITER_ID());
            stmt.setInt(4, noticeDto.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao modify method end");
    }
    
    // 공지사항 가져오는 쿼리
    private final String GET_NOTICE_BOARD_LIST = "SELECT N.ID, N.TITLE, N.CONTENT, N.WRITER_ID, N.NICKNAME, N.REGDATE, N.MODDATE, N.CNT FROM NOTICE N JOIN MEMBER M ON N.WRITER_ID = M.ID";

    public List<NoticeDto> getNoticeBoardList() {
        System.out.println("NoticeDao getNoticeBoardList method called");

        List<NoticeDto> noticeDtoList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_NOTICE_BOARD_LIST);
            rs = stmt.executeQuery();

            while (rs.next()) {
                NoticeDto noticeDto1 = new NoticeDto();

                noticeDto1.setId(rs.getInt("ID"));
                noticeDto1.setTitle(rs.getString("TITLE"));
                noticeDto1.setContent(rs.getString("CONTENT"));
                noticeDto1.setWRITER_ID(rs.getInt("WRITER_ID"));
                noticeDto1.setNickname(rs.getString("NICKNAME"));
                noticeDto1.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                noticeDto1.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                noticeDto1.setCnt(rs.getInt("CNT"));

                noticeDtoList.add(noticeDto1);

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

    private final String GET_NOTICE_BOARD = "SELECT N.ID, N.TITLE, N.CONTENT, N.WRITER_ID, N.NICKNAME, N.REGDATE, N.MODDATE, N.CNT FROM NOTICE N JOIN MEMBER M ON N.WRITER_ID = M.ID WHERE ID = ?";

    public NoticeDto getNoticeBoard(int id) {
        System.out.println("NoticeDao getNoticeBoard method called");
        NoticeDto noticeDto = new NoticeDto();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_NOTICE_BOARD);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                noticeDto.setId(rs.getInt("ID"));
                noticeDto.setTitle(rs.getString("TITLE"));
                noticeDto.setContent(rs.getString("CONTENT"));
                noticeDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                noticeDto.setNickname(rs.getString("NICKNAME"));
                noticeDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                noticeDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                noticeDto.setCnt(rs.getInt("CNT"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        System.out.println("NoticeDao getNoticeBoard method end");
        return noticeDto;
    }

}
