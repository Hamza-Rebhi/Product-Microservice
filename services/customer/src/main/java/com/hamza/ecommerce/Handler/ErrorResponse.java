package com.hamza.ecommerce.Handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
