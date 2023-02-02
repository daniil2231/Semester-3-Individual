package fontys.pls.work.controller.DTO;

import fontys.pls.work.domain.TotalTradedVolumeResponse;

public class TotalTradedVolumeResponseDTOConverter {
    private TotalTradedVolumeResponseDTOConverter() {

    }

    public static TotalTradedVolumeResponseDTO convert(TotalTradedVolumeResponse totalVol) {
        return TotalTradedVolumeResponseDTO.builder()
                .totalTradedVolume(totalVol.getTotalTradedVolume())
                .build();
    }
}
