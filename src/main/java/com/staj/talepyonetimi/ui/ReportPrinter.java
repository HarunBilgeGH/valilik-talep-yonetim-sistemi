package com.staj.talepyonetimi.ui;

import java.util.List;

import com.staj.talepyonetimi.model.WorkRequest;

public class ReportPrinter {

    private final RequestPrinter requestPrinter = new RequestPrinter();

    public void print(WorkRequest request) {
        requestPrinter.print(request);
    }

    public void printAll(List<WorkRequest> requests) {
        requestPrinter.printAll(requests);
    }
}
