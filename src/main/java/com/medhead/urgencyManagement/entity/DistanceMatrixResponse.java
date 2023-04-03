package com.medhead.urgencyManagement.entity;

import java.util.List;

public class DistanceMatrixResponse {
    public List<String> destinationAddresses;
    public List<String> originAddresses;
    public List<DistanceMatrixRow> rows;
    public DistanceMatrixStatus status;
    public String errorMessage;
}
