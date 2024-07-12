package com.bit.springboard.dao;

import com.bit.springboard.dto.MemberDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// DAO(Data Access Object): 데이터베이스에 직접 접근해서 쿼리를 실행하는 클래스
@Repository
public class MemberDao {
    private SqlSessionTemplate mybatis;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSessionTemplate ) {
        this.mybatis = sqlSessionTemplate;
    }


    public void join(MemberDto memberDto) {
        System.out.println("MemberDao의 join method 실행");

        mybatis.insert("memberDao.join", memberDto);

        System.out.println("MemberDao의 Join method 실행 종료");
    }

    
    public List<MemberDto> getMembers() {
        System.out.println("MemberDao의 getMembers method 실행");

        return mybatis.selectList("memberDao.getMembers");
    }


    public MemberDto getMemberByUsername(String username) {
        System.out.println("MemberDao의 getMemberByUsername method 실행");

        return mybatis.selectOne("memberDao.getMemberByUsername", username);
    }

}
