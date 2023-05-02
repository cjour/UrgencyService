package com.medhead.urgencyManagement.entity;

public class DistanceMatrixElement {
    public DistanceMatrixElementStatus status;
    public TextValueObject distance;
    public TextValueObject duration;

    public DistanceMatrixElement() {
    }

    public DistanceMatrixElement(DistanceMatrixElementStatus status, TextValueObject distance, TextValueObject duration) {
        this.status = status;
        this.distance = distance;
        this.duration = duration;
    }
}
