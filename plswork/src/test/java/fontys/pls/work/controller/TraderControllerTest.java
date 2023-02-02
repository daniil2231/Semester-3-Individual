package fontys.pls.work.controller;

import fontys.pls.work.business.*;
import fontys.pls.work.domain.TotalTradedVolumeResponse;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class TraderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTraderUseCase createTraderUseCase;

    @MockBean
    private ChangeTraderFundsUseCase changeTraderFundsUseCase;

    @MockBean
    private GetTraderUseCase getTraderUseCase;

    @MockBean
    private UpdateTraderUseCase updateTraderUseCase;

    @MockBean
    private GetTradersUseCase getTradersUseCase;

    @MockBean
    private GetTotalTradedVolume getTotalTradedVolume;

    @MockBean
    private GetTradersByEmailUseCase getTradersByEmailUseCase;

    @Test
    void createTrader_shouldReturn200_whenRequestIsValid() throws Exception {

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

        when(createTraderUseCase.createTrader("test@mail.com", "123", "dandi bob", "2323-2323-2323-2323", "123", "09/24")).thenReturn(trader);

        mockMvc.perform(post("/traders")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "id": 1,
                                    "email": "test@mail.com",
                                    "password": "123",
                                    "nameOnCard": "dandi bob",
                                    "cardNumber": "2323-2323-2323-2323",
                                    "cardCVV": "123",
                                    "cardValidThru": "09/24"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            { "id": 1, "email":"test@mail.com", "password":"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm", "nameOnCard":"dandi bob", "cardNumber":"2323-2323-2323-2323", "cardCVV":"123", "cardValidThru": "09/24", "tradedVolume": 11500.0, "realizedPnl": 0.0, "funds": 0.0 }
                        """));

        verify(createTraderUseCase).createTrader("test@mail.com", "123", "dandi bob", "2323-2323-2323-2323", "123", "09/24");
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void updateTrader_shouldReturn200_whenRequestIsValid() throws Exception {

        Trader trader = Trader.builder()
                .id(1L)
                .email("test@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("bandi dod")
                .cardNumber("1212-1212-1212-1212")
                .cardCVV("111")
                .cardValidThru("10/25")
                .funds(1000.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        when(updateTraderUseCase.updateTrader(1L, "bandi dod", "1212-1212-1212-1212", "111", "10/25")).thenReturn(trader);

        mockMvc.perform(put("/traders/update")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "id": 1,
                                    "nameOnCard": "bandi dod",
                                    "cardNumber": "1212-1212-1212-1212",
                                    "cardCVV": "111",
                                    "cardValidThru": "10/25"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            { "id": 1, "email":"test@mail.com", "password":"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm", "nameOnCard":"bandi dod", "cardNumber":"1212-1212-1212-1212", "cardCVV":"111", "cardValidThru": "10/25", "tradedVolume": 11500.0, "realizedPnl": 0.0, "funds": 1000.0 }
                        """));

        verify(updateTraderUseCase).updateTrader(1L, "bandi dod", "1212-1212-1212-1212", "111", "10/25");
    }

    @Test
    void addFundsToTrader_shouldReturn200_whenRequestIsValid() throws Exception {

        Trader trader = Trader.builder()
                .id(1L)
                .email("test@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(1000.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        when(changeTraderFundsUseCase.changeTraderFunds(1000.0, 1L)).thenReturn(trader);

        mockMvc.perform(put("/traders")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "id": 1,
                                    "funds": 1000.0
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            { "id": 1, "email":"test@mail.com", "password":"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm", "nameOnCard":"dandi bob", "cardNumber":"2323-2323-2323-2323", "cardCVV":"123", "cardValidThru": "09/24", "tradedVolume": 11500.0, "realizedPnl": 0.0, "funds": 1000.0 }
                        """));

        verify(changeTraderFundsUseCase).changeTraderFunds(1000.0, 1L);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void getTraderShouldReturn200WhenTraderFound() throws Exception {
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

        when(getTraderUseCase.getTrader(1L)).thenReturn(Optional.of(trader));

        mockMvc.perform(get("/traders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            { "id": 1, "email":"test@mail.com", "password":"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm", "nameOnCard":"dandi bob", "cardNumber":"2323-2323-2323-2323", "cardCVV":"123", "cardValidThru": "09/24", "tradedVolume": 11500.0, "realizedPnl": 0.0, "funds": 0.0 }
                        """));

        verify(getTraderUseCase).getTrader(1L);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"trader"})
    void getTrader_shouldReturn404Error_whenTraderNotFound() throws Exception {
        when(getTraderUseCase.getTrader(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/traders/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getTraderUseCase).getTrader(1L);
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"admin"})
    void getTraders_shouldReturn200_whenTradersFound() throws Exception {

        Trader trader1 = Trader.builder()
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

        Trader trader2 = Trader.builder()
                .id(2L)
                .email("test1@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        when(getTradersUseCase.getTraders()).thenReturn(List.of(trader1, trader2));

        mockMvc.perform(get("/traders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("\"traders\":[\n" +
                        "//                                    {\"id\":1, \"email\":\"test@mail.com\", \"password\":\"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm\", \"nameOnCard\":\"dandi bob\", \"cardNumber\":\"2323-2323-2323-2323\", \"cardCVV\":\"123\", \"cardValidThru\": \"09/24\", \"tradedVolume\": 11500.0, \"realizedPnl\": 0.0, \"funds\": 0.0},\n" +
                        "//                                    {\"id\":2, \"email\":\"test1@mail.com\", \"password\":\"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm\", \"nameOnCard\":\"dandi bob\", \"cardNumber\":\"2323-2323-2323-2323\", \"cardCVV\":\"123\", \"cardValidThru\": \"09/24\", \"tradedVolume\": 11500.0, \"realizedPnl\": 0.0, \"funds\": 0.0}\n" +
                        "//                                ]");

        verify(getTradersUseCase).getTraders();
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"admin"})
    void getTradersByEmail_shouldReturn200_whenTradersFound() throws Exception {

        Trader trader1 = Trader.builder()
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

        Trader trader2 = Trader.builder()
                .id(2L)
                .email("test1@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        Trader trader3 = Trader.builder()
                .id(3L)
                .email("asd@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(11500.0)
                .build();

        when(getTradersByEmailUseCase.getTradersByEmail("test")).thenReturn(List.of(trader1, trader2));

        mockMvc.perform(get("/traders/filter/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("\"traders\":[\n" +
                        "//                                    {\"id\":1, \"email\":\"test@mail.com\", \"password\":\"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm\", \"nameOnCard\":\"dandi bob\", \"cardNumber\":\"2323-2323-2323-2323\", \"cardCVV\":\"123\", \"cardValidThru\": \"09/24\", \"tradedVolume\": 11500.0, \"realizedPnl\": 0.0, \"funds\": 0.0},\n" +
                        "//                                    {\"id\":2, \"email\":\"test1@mail.com\", \"password\":\"$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm\", \"nameOnCard\":\"dandi bob\", \"cardNumber\":\"2323-2323-2323-2323\", \"cardCVV\":\"123\", \"cardValidThru\": \"09/24\", \"tradedVolume\": 11500.0, \"realizedPnl\": 0.0, \"funds\": 0.0}\n" +
                        "//                                ]");

        verify(getTradersByEmailUseCase).getTradersByEmail("test");
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"admin"})
    void getTotalTradedVolume_shouldReturn200() throws Exception {
        Trader trader1 = Trader.builder()
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

        Trader trader2 = Trader.builder()
                .id(2L)
                .email("test1@mail.com")
                .password("$2a$10$nJmM0.IfK2BW7AA9BWPnNu4Aql2MpOnq4skMYH1cYwGg5ZhdIpMVm")
                .nameOnCard("dandi bob")
                .cardNumber("2323-2323-2323-2323")
                .cardCVV("123")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(500.0)
                .build();

        when(getTotalTradedVolume.getTotalTradedVolume()).thenReturn(TotalTradedVolumeResponse.builder().totalTradedVolume(12000.0).build());

        mockMvc.perform(get("/traders/volume"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            { "totalTradedVolume":12000.0 }
                        """));

        verify(getTotalTradedVolume).getTotalTradedVolume();
    }
}
