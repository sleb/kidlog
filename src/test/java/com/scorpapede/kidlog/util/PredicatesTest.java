package com.scorpapede.kidlog.util;

import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Status;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.amazon.ask.model.slu.entityresolution.StatusCode.ER_ERROR_EXCEPTION;
import static com.amazon.ask.model.slu.entityresolution.StatusCode.ER_SUCCESS_MATCH;
import static com.scorpapede.kidlog.util.Predicates.erAuthority;
import static com.scorpapede.kidlog.util.Predicates.erSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PredicatesTest {

    private static final Pattern AUTHORITY_PATTERN = Pattern.compile("amzn1\\.er-authority\\.echo-sdk\\.amzn1\\.ask\\.skill\\.d56517e6-32bb-4a9d-bea7-8b82128a268c\\.\\w+");

    @Test
    void testErSuccess() {
        assertTrue(
            erSuccess().test(
                Resolution.builder()
                    .withStatus(
                        Status.builder()
                            .withCode(ER_SUCCESS_MATCH)
                            .build()
                    )
                    .build()
            )
        );

        assertFalse(
            erSuccess().test(
                Resolution.builder()
                    .withStatus(
                        Status.builder()
                            .withCode(ER_ERROR_EXCEPTION)
                            .build()
                    )
                    .build()
            )
        );
    }

    @Test
    void testErAuthority() {
        assertTrue(
            erAuthority(AUTHORITY_PATTERN).test(
                Resolution.builder()
                    .withAuthority("amzn1.er-authority.echo-sdk.amzn1.ask.skill.d56517e6-32bb-4a9d-bea7-8b82128a268c.Kid")
                    .build()
            )
        );

        assertFalse(
            erAuthority(AUTHORITY_PATTERN).test(
                Resolution.builder()
                    .withAuthority("wrong")
                    .build()
            )
        );
    }
}