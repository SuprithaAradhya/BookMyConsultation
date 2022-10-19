package com.bookmyconsultation.bookmyconsultation.provider;

import com.bookmyconsultation.bookmyconsultation.exception.RestErrorCode;
import com.bookmyconsultation.bookmyconsultation.exception.UnauthorizedException;

import static com.bookmyconsultation.bookmyconsultation.constants.ResourceConstants.BEARER_AUTH_PREFIX;

public class BearerAuthDecoder {

    private final String accessToken;

    public BearerAuthDecoder(final String bearerToken) {
        if (!bearerToken.startsWith(BEARER_AUTH_PREFIX)) {
            throw new UnauthorizedException(RestErrorCode.ATH_003);
        }

        final String[] bearerTokens = bearerToken.split(BEARER_AUTH_PREFIX);
        if (bearerTokens.length != 2) {
            throw new UnauthorizedException(RestErrorCode.ATH_004);
        }
        this.accessToken = bearerTokens[1];
    }

    public String getAccessToken() {
        return accessToken;
    }

}
