package com.yonsai.book.controller;

import com.yonsai.book.aspect.LoggingAspect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

// static import: 코드를 짧고 읽기 좋게 만들어줍니다.
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class) // 1. 컨트롤러만 집중적으로 테스트 (가볍고 빠름)
@Import(LoggingAspect.class)      // 2. AOP 로그 기능도 테스트에 포함
@EnableAspectJAutoProxy           // 3. AOP 작동 활성화
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc; // 가짜 브라우저 역할 (요청을 보내고 결과를 받음)

    @Test
    @DisplayName("도서 등록 기능 테스트 (/add)")
    void addTest() throws Exception {
        // given (준비)
        String title = "Junit5_Test_Book";

        // when & then (실행 및 검증)
        mockMvc.perform(get("/add")              // GET 요청 보내기
                .param("title", title))          // 파라미터 ?title=...
                .andExpect(status().isOk())      // 상태 코드가 200(OK)인가?
                .andExpect(content().string("도서 등록 완료: " + title)) // 결과 글자가 맞는가?
                .andDo(print());                 // 처리 과정을 콘솔에 출력해라
    }

    @Test
    @DisplayName("도서 수정 기능 테스트 (/update)")
    void updateTest() throws Exception {
        // given
        String id = "100";

        // when & then
        mockMvc.perform(get("/update")
                .param("id", id))
                .andExpect(status().isOk())
                .andExpect(content().string("도서 수정 완료 ID: " + id))
                .andDo(print());
    }

    @Test
    @DisplayName("도서 조회 기능 테스트 (/select)")
    void selectTest() throws Exception {
        // when & then
        mockMvc.perform(get("/select"))
                .andExpect(status().isOk())
                .andExpect(content().string("도서 전체 조회 결과입니다."))
                .andDo(print());
    }
}