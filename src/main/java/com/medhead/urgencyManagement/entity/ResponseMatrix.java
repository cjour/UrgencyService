package com.medhead.urgencyManagement.entity;

import java.util.List;

public class ResponseMatrix {
    public List<List<Integer>> distance;
    public List<List<Integer>> times;
    public List<List<Integer>> weights;
    public ResponseInfo info;

    public ResponseMatrix() {
    }

    public ResponseMatrix(List<List<Integer>> distance, List<List<Integer>> times, List<List<Integer>> weights, ResponseInfo info) {
        this.distance = distance;
        this.times = times;
        this.weights = weights;
        this.info = info;
    }
}

class ResponseInfo {
    List<String> copyrights;
    Double took;

    public ResponseInfo() {
    }

    public ResponseInfo(List<String> copyrights, Double took) {
        this.copyrights = copyrights;
        this.took = took;
    }
}
