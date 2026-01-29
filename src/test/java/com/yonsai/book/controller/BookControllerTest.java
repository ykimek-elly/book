package com.yonsai.book.controller;

import com.yonsai.book.aspect.LoggingAspect;
import com.yonsai.book.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

// 테스트를 위한 편의 기능(static import)
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class) // 1. 컨트롤러만 집중 테스트
@Import(LoggingAspect.class)      // 2. AOP 로그 설정 포함
@EnableAspectJAutoProxy           // 3. AOP 기능 활성화
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc; // 가짜 브라우저

    @MockBean // 4. [중요] 가짜 서비스 생성 (리포지토리/DB 없이 테스트 가능)
    private BookService bookService;

    @Test
    @DisplayName("도서 등록 기능 테스트 (/add)")
    void addTest() throws Exception {
        // given (준비)
        String title = "HarryPotter";
        String author = "JKRowling";

        // 가짜 서비스가 할 일을 미리 정의 (Stubbing)
        // "addBook 메서드가 호출되면, 에러 없이 그냥 넘어가라(null 반환)"
        given(bookService.addBook(anyString(), anyString())).willReturn(null);

        // when & then (실행 및 검증)
        mockMvc.perform(get("/add")
                .param("title", title)
                .param("author", author))
                .andExpect(status().isOk()) // 200 OK 인가?
                .andExpect(content().string("도서 등록 완료: " + title + " (저자: " + author + ")")) // 화면 메시지 검증
                .andDo(print()); // 로그 출력
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