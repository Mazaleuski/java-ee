package by.teachmeskills.utils;

import by.teachmeskills.exceptions.RequestParamNullException;

public class HttpRequestParamValidator {

    private static final String REQUEST_PARAM_IS_NULL = "Request parameters is null.";

    public static void validateParamNotNull(String parameter) throws RequestParamNullException {
        if (parameter == null) {
            throw new RequestParamNullException(REQUEST_PARAM_IS_NULL);
        }
    }
}
