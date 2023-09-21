package com.amor4ti.dailylab.global.schedule;

import com.amor4ti.dailylab.domain.diary.service.DiaryService;
import com.amor4ti.dailylab.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final TodoService todoService;
    private final DiaryService diaryService;

    // 0초 0분 6시 매일 매달 매요일
    @Scheduled(cron = "0 0 6 * * *")
    @Transactional
    public void forceCompleteDay(){
        // 하루를 6시까지 마무리하지 않은 사용자 조회
        LocalDate today = LocalDate.now().minusDays(1);
        List<Long> unfinishedMemberList = todoService.getUnfinishedMemberIdByTodoDate(today);

        log.info("하루를 마무리 하지 않은 사용자가 {}명 있습니다.", unfinishedMemberList.size());

        LocalDate tomorrow = LocalDate.now();
    
        // 확정 일기 생성 및 내일 Todo 추천
        unfinishedMemberList.stream().parallel().forEach(memberId -> {
            diaryService.createConfirmDiary(memberId, tomorrow);
            todoService.recommendTodo(memberId, tomorrow.toString());
        });

        log.info("하루를 마무리 하지 않은 사용자 {}명의 하루가 마무리 되었습니다.", unfinishedMemberList.size());
    }
}
