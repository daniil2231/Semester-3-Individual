package fontys.pls.work.controller.converters;

import fontys.pls.work.controller.DTO.TotalTradedVolumeResponseDTO;
import fontys.pls.work.controller.DTO.TotalTradedVolumeResponseDTOConverter;
import fontys.pls.work.domain.TotalTradedVolumeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalTradedVolumeResponseDTOConverterTest {

    @Test
    void shouldConvertAllTotalTradedVolFieldsToDTO() {
        TotalTradedVolumeResponse volToBeConverted = TotalTradedVolumeResponse.builder()
                .totalTradedVolume(10000.0)
                .build();

        TotalTradedVolumeResponseDTO actual = TotalTradedVolumeResponseDTOConverter.convert(volToBeConverted);

        TotalTradedVolumeResponseDTO expected = TotalTradedVolumeResponseDTO.builder()
                .totalTradedVolume(10000.0)
                .build();
        assertEquals(actual, expected);
    }
}
