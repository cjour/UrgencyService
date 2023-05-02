package com.medhead.urgencyManagement.entity;

import java.util.List;

public class DistanceMatrixRow {
    public List<DistanceMatrixElement> elements;

    public DistanceMatrixRow() {
    }

    public DistanceMatrixRow(List<DistanceMatrixElement> elements) {
        this.elements = elements;
    }
}
