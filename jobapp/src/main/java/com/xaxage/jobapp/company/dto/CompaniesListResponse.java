package com.xaxage.jobapp.company.dto;

import java.util.List;

public class CompaniesListResponse {
    private int size;
    private List<CompanyResponse> items;

    public CompaniesListResponse(int size, List<CompanyResponse> items) {
        this.size = size;
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<CompanyResponse> getItems() {
        return items;
    }

    public void setItems(List<CompanyResponse> items) {
        this.items = items;
    }
}
