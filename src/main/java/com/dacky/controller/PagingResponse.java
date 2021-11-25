package com.dacky.controller;

public class PagingResponse extends BaseResponse {

    public PagingResponse(int status, String message, Object response, Long rowCount) {
        super(status, message, response);
        this.rowCount = rowCount;
    }

    public PagingResponse() {
        super();
    }

    private Long page;
    private Long totalPage;
    private Long rowCount;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }
}
