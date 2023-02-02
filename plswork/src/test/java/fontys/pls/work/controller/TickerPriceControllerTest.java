package fontys.pls.work.controller;

import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.domain.TickerPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class TickerPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @Test
    void getTickerPrice_ShouldReturn200() throws Exception {
        TickerPrice tp = TickerPrice.builder()
                .symbol("BTCUSDT")
                .price(15000.0)
                .build();

        when(getTickerPriceUseCase.getTickerPrice()).thenReturn(Optional.ofNullable(tp));

        mockMvc.perform(get("/tickers/prices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            { "symbol": "BTCUSDT", "price": 15000.0 }
                        """));

        verify(getTickerPriceUseCase).getTickerPrice();
    }
}
