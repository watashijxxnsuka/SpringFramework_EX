package com.bit.springboard.dao;

import com.bit.springboard.dto.MemberDto;
import com.bit.springboard.service.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// DAO(Data Access Object): 데이터베이스에 직접 접근해서 쿼리를 실행하는 클래스
@Repository
public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 쿼리문 작성
    private final String JOIN = "INSERT INTO MEMBER(USERNAME, PASSWORD, EMAIL, NICKNAME, TEL) VALUES(?,?,?,?,?)";

    private final String GET_MEMBERS = "SELECT ID" + ", USERNAME" + ", PASSWORD"
            + ", NICKNAME" + ", EMAIL" +", TEL" + " FROM MEMBER";

    private final String GET_MEMBER_BY_USERNAME = "SELECT ID" + ", USERNAME" + ", PASSWORD"
                + ", NICKNAME" + ", EMAIL" + ", TEL " + " FROM MEMBER " + " WHERE USERNAME = ? ";

    public void join(MemberDto memberDto) {
        System.out.println("MemberDao의 join method 실행");

        jdbcTemplate.update(JOIN, memberDto.getUsername(), memberDto.getPassword(), memberDto.getEmail(), memberDto.getNickname(), memberDto.getTel());

        System.out.println("MemberDao의 Join method 실행 종료");
    }

    
    public List<MemberDto> getMembers() {
        System.out.println("MemberDao의 getMembers method 실행");

        List<MemberDto> memberDtoList = new ArrayList<>();

        memberDtoList = jdbcTemplate.query(GET_MEMBERS, new MemberRowMapper());

        System.out.println("MemberDao의 getMembers method 실행 종료");
        return memberDtoList;
    }


    public MemberDto getMemberByUsername(String username) {
        System.out.println("MemberDao의 getMemberByUsername method 실행");

        MemberDto memberDto = new MemberDto();

        Object[] args = {username};

        memberDto = (MemberDto) jdbcTemplate.queryForObject(GET_MEMBER_BY_USERNAME, args,  new MemberRowMapper());

        System.out.println("MemeberDao의 getMemberByUsername method 실행 종료");
        return memberDto;
    }

}
