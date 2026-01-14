package com.lcsoo.product_management.util;

import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String JSON(Object o) throws Exception {
            return objectMapper.writeValueAsString(o);
    }
}
