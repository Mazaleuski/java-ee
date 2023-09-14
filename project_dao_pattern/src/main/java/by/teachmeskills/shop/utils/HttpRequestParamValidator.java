package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.exceptions.RequestParamNullException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpRequestParamValidator {

    private static final String REQUEST_PARAM_IS_NULL = "Request parameters is null.";

    public static void validateParamNotNull(String parameter) throws RequestParamNullException {
        if (parameter == null) {
            throw new RequestParamNullException(REQUEST_PARAM_IS_NULL);
        }
    }
}
