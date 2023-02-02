package fontys.pls.work.controller;

import fontys.pls.work.business.ClosePositionUseCase;
import fontys.pls.work.business.CreatePositionUseCase;
import fontys.pls.work.business.GetPositionUseCase;
import fontys.pls.work.business.GetPositionsByTraders_idUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class PositionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPositionUseCase getPositionUseCase;

    @MockBean
    private GetPositionsByTraders_idUseCase getPositionsByTraderUseCase;

    @MockBean
    private ClosePositionUseCase closePositionUseCase;

    @MockBean
    private CreatePositionUseCase createPositionUseCase;

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void getPositionShouldReturn200WhenPositionFound() throws Exception {
        Trader trader = Trader.builder()
                .id(1L)
                .email("test@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        Position position = Position.builder()
                .id(1L)
                .positionType("long")
                .val(500.0)
                .liquidationPrice(84.72955)
                .entryPrice(16945.91)
                .changeInPrice(-1.4963492665781875)
                .trader(trader)
                .build();

        when(getPositionUseCase.getPosition(1L)).thenReturn(Optional.of(position));

        mockMvc.perform(get("/positions/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            { "id":1, "trader":{ "id": 1, "email":"test@mail.com", "password":"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm", "nameOnCard":"dandi bob", "cardNumber":"2323-2323-2323-2323", "cardCVV":"123", "cardValidThru": "09/24", "tradedVolume": 11500.0, "realizedPnl": 0.0, "funds": 0.0 }, "positionType": "long", "val": 500.0, "entryPrice": 16945.91, "liquidationPrice": 84.72955, "changeInPrice": -1.4963492665781875 }
                        """));

        verify(getPositionUseCase, times(2)).getPosition(1L);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void getPosition_shouldReturn404Error_whenPositionNotFound() throws Exception {
        when(getPositionUseCase.getPosition(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/positions/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getPositionUseCase).getPosition(1L);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void getAllPositions_shouldReturn200WithPositionsList_WithFilterProvided() throws Exception {

        Trader trader = Trader.builder()
                .id(1L)
                .email("test@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        Position position1 = Position.builder()
                .id(1L)
                .positionType("long")
                .val(500.0)
                .liquidationPrice(84.72955)
                .entryPrice(16945.91)
                .changeInPrice(-1.4963492665781875)
                .trader(trader)
                .build();

        Position position2 = Position.builder()
                .id(2L)
                .positionType("long")
                .val(1000.0)
                .liquidationPrice(84.72955)
                .entryPrice(16945.91)
                .changeInPrice(-1.4963492665781875)
                .trader(trader)
                .build();

        List<Position> positions = List.of(position1, position2);

        when(getPositionsByTraderUseCase.getPositionsByTraders_id(1L)).thenReturn(positions);

        mockMvc.perform(get("/positions/all/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                                {
                                    "id": 1,
                                    "email": "test@mail.com",
                                    "password": "$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm",
                                    "nameOnCard": "dandi bob",
                                    "cardNumber": "2323-2323-2323-2323",
                                    "cardCVV": "123",
                                    "cardValidThru": "09/24",
                                    "tradedVolume": 11500.0,
                                    "realizedPnl": 0.0,
                                    "funds": 0.0
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("\"positions\":[\n" +
                        "//                                    {\"id\":1, \"trader\":{ \"id\":1, \"email\":\"test@mail.com\", \"password\":\"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm\", \"nameOnCard\":\"dandi bob\", \"cardNumber\":\"2323-2323-2323-2323\", \"cardCVV\":\"123\", \"cardValidThru\": \"09/24\", \"tradedVolume\": 11500.0, \"realizedPnl\": 0.0, \"funds\": 0.0 }, \"positionType\":\"long\", \"val\":500.0, \"entryPrice\":16945.91, \"liquidationPrice\":84.72955, \"changeInPrice\":-1.4963492665781875},\n" +
                        "//                                    {\"id\":2, \"trader\":{ \"id\":1, \"email\":\"test@mail.com\", \"password\":\"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm\", \"nameOnCard\":\"dandi bob\", \"cardNumber\":\"2323-2323-2323-2323\", \"cardCVV\":\"123\", \"cardValidThru\": \"09/24\", \"tradedVolume\": 11500.0, \"realizedPnl\": 0.0, \"funds\": 0.0 }, \"positionType\":\"long\", \"val\":1000.0, \"entryPrice\":16945.91, \"liquidationPrice\":84.72955, \"changeInPrice\":-1.4963492665781875}\n" +
                        "//                                ]");

        verify(getPositionsByTraderUseCase).getPositionsByTraders_id(1L);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void closePosition_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/positions/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(closePositionUseCase).closePosition(1L);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void createPosition_shouldReturn200_WhenRequestIsValid() throws Exception {

        Trader trader = Trader.builder()
                .id(1L)
                .email("test@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        Position position = Position.builder()
                .id(1L)
                .positionType("long")
                .val(500.0)
                .liquidationPrice(84.72955)
                .entryPrice(16945.91)
                .changeInPrice(-1.4963492665781875)
                .trader(trader)
                .build();

        when(createPositionUseCase.createPosition(1L, 500.0, "long")).thenReturn(position);

        mockMvc.perform(post("/positions")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "val":500.0,
                                    "positionType":"long",
                                    "trader": {
                                        "id": 1
                                    }
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            { "id":1, "trader":{ "id": 1, "email":"test@mail.com", "password":"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm", "nameOnCard":"dandi bob", "cardNumber":"2323-2323-2323-2323", "cardCVV":"123", "cardValidThru": "09/24", "tradedVolume": 11500.0, "realizedPnl": 0.0, "funds": 0.0 }, "positionType": "long", "val": 500.0, "entryPrice": 16945.91, "liquidationPrice": 84.72955, "changeInPrice": -1.4963492665781875 }
                        """));

        verify(createPositionUseCase).createPosition(1L, 500.0, "long");
    }
}
