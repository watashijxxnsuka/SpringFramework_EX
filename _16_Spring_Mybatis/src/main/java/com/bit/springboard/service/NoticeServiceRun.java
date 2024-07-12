package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class NoticeServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        BoardService noticeBoardService = factory.getBean("NoticeBoardService", BoardService.class);

        BoardDto boardDto = new BoardDto();
        
        // 공지사항 등록
        boardDto.setTitle("공지사항 1번");
        boardDto.setContent("공지사항 1번입니다");
        boardDto.setWRITER_ID(1);
        noticeBoardService.post(boardDto);

        // 공지사항 수정
        BoardDto noticeModifyDto = new BoardDto();

        noticeModifyDto.setId(7);
        noticeModifyDto.setTitle("공지사항 7번 수정");
        noticeModifyDto.setContent("공지사항 7번입니다.-수정");
        noticeModifyDto.setModdate(LocalDateTime.now());

        noticeBoardService.modify(noticeModifyDto);

        // 공지사항 삭제
        noticeBoardService.delete(13);

        // 공지사항 목록 조회
        noticeBoardService.getBoardList().forEach(notice -> {
            System.out.println(notice);
        });

        // 특정 ID 공지사항 조회
        System.out.println(noticeBoardService.getBoard(7));
    }
}
