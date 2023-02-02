package fontys.pls.work.business;

import fontys.pls.work.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
