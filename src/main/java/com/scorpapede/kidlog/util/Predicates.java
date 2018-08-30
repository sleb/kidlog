package com.scorpapede.kidlog.util;

import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.StatusCode;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface Predicates {
    static Predicate<Resolution> erSuccess() {
        return r -> StatusCode.ER_SUCCESS_MATCH.equals(r.getStatus().getCode());
    }

    static Predicate<Resolution> erAuthority(Pattern authorityPattern) {
        return r -> authorityPattern.matcher(r.getAuthority()).matches();
    }
}
