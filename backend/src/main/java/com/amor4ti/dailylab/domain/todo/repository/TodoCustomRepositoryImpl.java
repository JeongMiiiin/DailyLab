package com.amor4ti.dailylab.domain.todo.repository;

import com.amor4ti.dailylab.domain.entity.QTodo;
import com.amor4ti.dailylab.domain.entity.Todo;
import com.amor4ti.dailylab.domain.todo.dto.response.QTodoDto;
import com.amor4ti.dailylab.domain.todo.dto.response.QTodoSmallDto;
import com.amor4ti.dailylab.domain.todo.dto.response.TodoDto;
import com.amor4ti.dailylab.domain.todo.dto.response.TodoSmallDto;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    QTodo qtodo = QTodo.todo;

    Expression<Boolean> checkedExpression = new CaseBuilder()
            .when(qtodo.checkedDate.isNull()).then(false)
            .otherwise(true);

    private final JPAQueryFactory jpaQueryFactory;

    public TodoCustomRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Todo> findTodayTodoListByMemberIdAndTodoDate(Long memberId, LocalDate todoDate) {

        return jpaQueryFactory
                .selectFrom(qtodo)
                .where(qtodo.member.memberId.eq(memberId)
                        .and(qtodo.todoDate.eq(todoDate)))
                .fetch();
    }

    @Override
    public List<TodoSmallDto> findSomedayPartTodoDtoListByMemberIdAndTodoDate(LocalDate todoDate, Long memberId) {

        return jpaQueryFactory
                .select(new QTodoSmallDto(
                        qtodo.todoId,
                        qtodo.content,
                        checkedExpression
                ))
                .from(qtodo)
                .where(qtodo.member.memberId.eq(memberId)
                        .and(qtodo.todoDate.eq(todoDate)))
                .fetch();
    }

    @Override
    public List<TodoDto> findSomedayFullTodoDtoListByMemberIdAndTodoDate(LocalDate todoDate, Long memberId) {

        return jpaQueryFactory
                .select(new QTodoDto(
                        qtodo.todoId,
                        qtodo.content,
                        qtodo.category.categoryId,
                        qtodo.category.large,
                        qtodo.category.medium,
                        qtodo.category.small,
                        qtodo.todoDate,
                        qtodo.checkedDate,
                        checkedExpression,
                        qtodo.isSystem,
                        qtodo.isDeleted,
                        qtodo.member.memberId,
                        qtodo.member.username))
                .from(qtodo)
                .where(qtodo.member.memberId.eq(memberId)
                        .and(qtodo.todoDate.eq(todoDate)))
                .fetch();
    }

    @Override
    public Optional<Todo> findByMemberIdAndCategoryIdAndTodoDate(Long memberId, Long categoryId, LocalDate todoDate) {

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(qtodo)
                .where(qtodo.member.memberId.eq(memberId)
                        .and(qtodo.category.categoryId.eq(categoryId))
                        .and(qtodo.todoDate.eq(todoDate)))
                .fetchOne());
    }

    @Override
    public List<TodoDto> findByMemberId(Long memberId) {

        return jpaQueryFactory
                .select(new QTodoDto(
                                    qtodo.todoId,
                                    qtodo.content,
                                    qtodo.category.categoryId,
                                    qtodo.category.large,
                                    qtodo.category.medium,
                                    qtodo.category.small,
                                    qtodo.todoDate,
                                    qtodo.checkedDate,
                                    checkedExpression,
                                    qtodo.isSystem,
                                    qtodo.isDeleted,
                                    qtodo.member.memberId,
                                    qtodo.member.username
                                ))
                .from(qtodo)
                .where(qtodo.member.memberId.eq(memberId))
                .fetch();
    }
}