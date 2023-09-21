package com.amor4ti.dailylab.domain.todo.service;

import com.amor4ti.dailylab.domain.category.repository.CategoryRepository;
import com.amor4ti.dailylab.domain.categoryBlackList.repository.CategoryBlackListRepository;
import com.amor4ti.dailylab.domain.entity.Member;
import com.amor4ti.dailylab.domain.entity.Todo;
import com.amor4ti.dailylab.domain.entity.category.Category;
import com.amor4ti.dailylab.domain.entity.category.CategoryBlackList;
import com.amor4ti.dailylab.domain.entity.category.MemberCategoryId;
import com.amor4ti.dailylab.domain.member.repository.MemberRepository;
import com.amor4ti.dailylab.domain.todo.dto.request.TodoCheckUpdateDto;
import com.amor4ti.dailylab.domain.todo.dto.request.TodoRegistDto;
import com.amor4ti.dailylab.domain.todo.dto.request.TodoUpdateDto;
import com.amor4ti.dailylab.domain.todo.dto.response.TodoDto;
import com.amor4ti.dailylab.domain.todo.dto.response.TodoRecommendedDto;
import com.amor4ti.dailylab.domain.todo.dto.response.TodoSmallDto;
import com.amor4ti.dailylab.domain.todo.repository.TodoRepository;
import com.amor4ti.dailylab.global.exception.CustomException;
import com.amor4ti.dailylab.global.exception.ExceptionStatus;
import com.amor4ti.dailylab.global.response.CommonResponse;
import com.amor4ti.dailylab.global.response.DataResponse;
import com.amor4ti.dailylab.global.response.ResponseService;
import com.amor4ti.dailylab.global.response.ResponseStatus;
import com.amor4ti.dailylab.global.util.JsonConverter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    @Value("${data-server-url}")
    private String DATA_SERVER_URL;

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryBlackListRepository categoryBlackListRepository;

    private final ResponseService responseService;

    private final JsonConverter jsonConverter;

    @Override
    public DataResponse getTodoListByMemberId(Long memberId) {
        List<TodoDto> todoDtoList = todoRepository.findByMemberId(memberId);

        return responseService.successDataResponse(ResponseStatus.RESPONSE_SUCCESS, todoDtoList);
    }

    @Override
    public DataResponse getPartTodoListByMemberIdAndTodoDate(LocalDate todoDate, Long memberId) {
        List<TodoSmallDto> todoSmallDtoList = todoRepository.findSomedayPartTodoDtoListByMemberIdAndTodoDate(todoDate, memberId);

        return responseService.successDataResponse(ResponseStatus.RESPONSE_SUCCESS, todoSmallDtoList);
    }

    @Override
    public DataResponse getFullTodoListByMemberIdAndTodoDate(LocalDate todoDate, Long memberId) {
        List<TodoDto> todoDtoList = todoRepository.findSomedayFullTodoDtoListByMemberIdAndTodoDate(todoDate, memberId);

        return responseService.successDataResponse(ResponseStatus.RESPONSE_SUCCESS, todoDtoList);
    }

    @Override
    public DataResponse getAllTodoList() {
        List<TodoDto> todoDtoList = new ArrayList<>();
        List<Todo> todoList = todoRepository.findAll();

        for (Todo todo : todoList) {
            Category category = categoryRepository.findByCategoryId(todo.getCategory().getCategoryId())
                    .orElseThrow(() -> new CustomException(ExceptionStatus.CATEGORY_NOT_FOUND));

            TodoDto todoDto = new TodoDto().toDto(todo, category);

            todoDtoList.add(todoDto);
        }

        return responseService.successDataResponse(ResponseStatus.RESPONSE_SUCCESS, todoDtoList);
    }

    @Override
    @Transactional
    public DataResponse registTodo(TodoRegistDto todoRegistDto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        Category category = categoryRepository.findByCategoryId(todoRegistDto.getCategoryId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.CATEGORY_NOT_FOUND));

        MemberCategoryId memberCategoryId = MemberCategoryId.builder()
                        .categoryId(todoRegistDto.getCategoryId())
                        .memberId(memberId)
                        .build();

        Optional<CategoryBlackList> blackListOptional = categoryBlackListRepository.findByMemberCategoryId(memberCategoryId);

        // 이미 블랙리스트에 있는 경우 등록 X
        if(blackListOptional.isPresent() && !blackListOptional.get().isRemove())
            throw new CustomException(ExceptionStatus.CATEGORY_BLACKLIST_ALREADY_FALSE);

        Optional<Todo> todayTodoOptional = todoRepository.findByMemberIdAndCategoryIdAndTodoDate(memberId, todoRegistDto.getCategoryId(), todoRegistDto.getTodoDate());

        // 이미 오늘 등록한 카테고리의 todo인 경우
        if(todayTodoOptional.isPresent())
            throw new CustomException(ExceptionStatus.TODO_ALREADY_REGIST_TODAY);

        Todo todo = todoRegistDto.toEntity(member, category);
        todoRepository.save(todo);

        return responseService.successDataResponse(ResponseStatus.TODO_REGIST_SUCCESS, todo.getTodoId());
    }

    @Override
    @Transactional
    public CommonResponse deleteTodo(Long memberId, List<Long> todoIdList) {
        for (Long todoId : todoIdList) {
            Todo todo = todoRepository.findByTodoId(todoId)
                    .orElseThrow(() -> new CustomException(ExceptionStatus.TODO_NOT_FOUND));

            todo.deleteTodo();
            todoRepository.save(todo);
        }

        return responseService.successResponse(ResponseStatus.RESPONSE_SUCCESS);
    }

    @Override
    @Transactional
    public CommonResponse changeCheckTodo(Long memberId, TodoCheckUpdateDto todoCheckUpdateDto) {
        Todo todo = todoRepository.findByTodoId(todoCheckUpdateDto.getTodoId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.TODO_NOT_FOUND));

        System.out.println(todoCheckUpdateDto.getCheckedDate());

        todo.checkTodo(todoCheckUpdateDto.getCheckedDate());
        todoRepository.save(todo);

        return responseService.successResponse(ResponseStatus.RESPONSE_SUCCESS);
    }

    @Override
    public DataResponse recommendTodo(Long memberId, String todoDate) {
        // fastAPI 요청 주소
//        String fastApiUrl = "http://localhost:8181/data/todo";
        String fastApiUrl = DATA_SERVER_URL + "/todo";

        // RestTemplate 통신
        Map<String, Object> data = new HashMap<>();
        data.put("memberId", memberId);
        data.put("todoDate", todoDate);
        RestTemplate restTemplate = new RestTemplate();

        // 통신 결과 (FastAPI에서 반환한 값)
        String response = restTemplate.postForObject(fastApiUrl, data, String.class);

        // String -> JSON
        JsonObject jsonObject = jsonConverter.converter(response);

        List<Long> CategoryIdList = new ArrayList<>();
        List<Double> ScoreList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            // 카테고리 ID (랭킹)
            CategoryIdList.add(Long.parseLong(entry.getKey()));
            
            // 점수 (랭킹)
            ScoreList.add(entry.getValue().getAsDouble());
        }

        // 빈 추천 Todo 객체
        List<TodoRecommendedDto> todoRecommendedDtoList = new ArrayList<>();

        int cnt = 0;
        
        for (Long categoryId : CategoryIdList) {
            // 일단 3개만
            if(cnt == 3)
                break;

            // 블랙리스트 체크 로직
            MemberCategoryId memberCategoryId = MemberCategoryId.builder()
                    .memberId(memberId)
                    .categoryId(categoryId)
                    .build();

            Optional<CategoryBlackList> optionalBlackList = categoryBlackListRepository.findByMemberCategoryId(memberCategoryId);
            
            if(optionalBlackList.isPresent() && !optionalBlackList.get().isRemove())
                break;

            cnt++;

            // db에 추천 db 등록 로직
            TodoRegistDto todoRegistDto = TodoRegistDto.builder()
                    .categoryId(categoryId)
                    .content(null)
                    .todoDate(LocalDate.parse(todoDate))
                    .build();

            DataResponse dataResponse = registTodo(todoRegistDto, memberId);

            Long newTodoId = (Long) dataResponse.getData();

            TodoRecommendedDto todoRecommendedDto = todoRepository.findTodoRecommendedDtoByMemberIdAndTodoId(memberId, newTodoId)
                    .orElseThrow(() -> new CustomException(ExceptionStatus.EXCEPTION));

            System.out.println(todoRecommendedDto);

            todoRecommendedDtoList.add(todoRecommendedDto);
        }

        return responseService.successDataResponse(ResponseStatus.RESPONSE_SUCCESS, todoRecommendedDtoList);
    }

    @Override
    @Transactional
    public List<Long> getUnfinishedMemberIdByTodoDate(LocalDate todoDate){
        return todoRepository.findMemberIdByTodoDate(todoDate);
    }
}
