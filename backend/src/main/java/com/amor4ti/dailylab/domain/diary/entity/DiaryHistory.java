package com.amor4ti.dailylab.domain.diary.entity;

import com.amor4ti.dailylab.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryHistoryId;

    private Long memberId;
    private String content;
    private LocalDate diaryDate;

    private Double similarity;
}
