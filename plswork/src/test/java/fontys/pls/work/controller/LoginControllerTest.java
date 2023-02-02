package fontys.pls.work.controller;

import fontys.pls.work.business.LoginUseCase;
import fontys.pls.work.domain.LoginResponse;
import fontys.pls.work.domain.UserD;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginUseCase loginUseCase;

    @Test
    void login_ShouldReturn200_whenUserFound() throws Exception {

        UserD user = UserD.builder()
                .id(1L)
                .email("test@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .build();

        LoginResponse loginResponse = LoginResponse.builder()
                .role("trader")
                .accessToken("accessToken")
                .build();

        when(loginUseCase.login("test@mail.com", "123")).thenReturn(loginResponse);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "email": "test@mail.com",
                                    "password": "123"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            { "accessToken": "accessToken", "role":"trader" }
                        """));

        verify(loginUseCase).login("test@mail.com", "123");
    }
}
