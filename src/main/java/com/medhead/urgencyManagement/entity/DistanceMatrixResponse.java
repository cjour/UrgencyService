package com.medhead.urgencyManagement.entity;

import java.util.List;

public class DistanceMatrixResponse {
    public List<String> destinationAddresses;
    public List<String> originAddresses;
    public List<DistanceMatrixRow> rows;
    public DistanceMatrixStatus status;
    public String errorMessage;

    public DistanceMatrixResponse() {
    }

    public DistanceMatrixResponse(List<String> destinationAddresses, List<String> originAddresses, List<DistanceMatrixRow> rows, DistanceMatrixStatus status, String errorMessage) {
        this.destinationAddresses = destinationAddresses;
        this.originAddresses = originAddresses;
        this.rows = rows;
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
