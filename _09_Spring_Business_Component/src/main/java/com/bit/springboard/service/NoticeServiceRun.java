package com.bit.springboard.service;

import com.bit.springboard.dto.NoticeDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class NoticeServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        BoardService noticeBoardService = factory.getBean("NoticeBoardService", BoardService.class);

        NoticeDto noticeDto = new NoticeDto();
        
        // 공지사항 등록
        noticeDto.setTitle("공지사항 1번");
        noticeDto.setContent("공지사항 1번입니다");
        noticeDto.setWRITER_ID(1);
        noticeBoardService.post(noticeDto);

        // 공지사항 수정
        NoticeDto noticeModifyDto = new NoticeDto();

        noticeModifyDto.setId(2);
        noticeModifyDto.setTitle("공지사항 1번 수정");
        noticeModifyDto.setContent("공지사항 1번입니다.-수정");
        noticeModifyDto.setModdate(LocalDateTime.now());

        noticeBoardService.modify(noticeModifyDto);

        // 공지사항 삭제
        noticeBoardService.delete(3);

        // 공지사항 목록 조회
        noticeBoardService.getNoticeBoardList().forEach(notice -> {
            System.out.println(notice);
        });

        // 특정 ID 공지사항 조회
        noticeBoardService.getNoticeBoard(2);
    }
}
