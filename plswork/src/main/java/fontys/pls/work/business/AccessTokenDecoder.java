package fontys.pls.work.business;

import fontys.pls.work.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
