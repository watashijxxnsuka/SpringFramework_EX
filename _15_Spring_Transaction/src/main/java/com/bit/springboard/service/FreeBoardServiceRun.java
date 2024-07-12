package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class FreeBoardServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory = new GenericXmlApplicationContext("root-context.xml");

        BoardService boardService = factory.getBean("freeBoardService", BoardService.class);

        BoardDto boardDto = new BoardDto();
        
        // 게시글 등록
        boardDto.setId(100);
        boardDto.setTitle("자유게시글1");
        boardDto.setContent("자유게시글 1번입니다.");
        // writer_id 는 member 테이블의 id 컬럼을 foreign key 로 가져오기 때문에
        // member table 에 존재하는 id 값만 입력할 수 있다.
        boardDto.setWRITER_ID(1);

        boardService.post(boardDto);

        
        // 게시글 수정
        BoardDto modifyboardDto = new BoardDto();

        modifyboardDto.setId(1);
        modifyboardDto.setTitle("자유게시글1 수정");
        modifyboardDto.setContent("자유게시글 1번 입니다.-수정됨.");
        modifyboardDto.setModdate(LocalDateTime.now());
        
        boardService.modify(modifyboardDto);

        // 게시글 삭제
        boardService.delete(3);

        // 게시글 목록 조회
        boardService.getBoardList().forEach(board -> {
            System.out.println(board);
        });

        // 특정 ID 게시글 조회
        System.out.println(boardService.getBoard(5));

        factory.close();
    }
}
