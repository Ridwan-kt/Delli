package com.exceptions;

import java.util.Map;

public record CustomErrorResponse (
        Map<String, String> errors
){}