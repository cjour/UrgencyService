package com.medhead.urgencyManagement.entity;

public enum DistanceMatrixStatus {
    OK,
    INVALID_REQUEST,
    MAX_ELEMENTS_EXCEEDED,
    MAX_DIMENSIONS_EXCEEDED,
    OVER_DAILY_LIMIT,
    OVER_QUERY_LIMIT,
    REQUEST_DENIED,
    UNKNOWN_ERROR
}
