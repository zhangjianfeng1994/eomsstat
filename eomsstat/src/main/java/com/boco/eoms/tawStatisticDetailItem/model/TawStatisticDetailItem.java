package com.boco.eoms.tawStatisticDetailItem.model;

public class TawStatisticDetailItem {
    private String id;

    private String detailId;

    private String detailColumnEng;

    private String detailColumnChina;

    private String transferType;

    private String transferCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }

    public String getDetailColumnEng() {
        return detailColumnEng;
    }

    public void setDetailColumnEng(String detailColumnEng) {
        this.detailColumnEng = detailColumnEng == null ? null : detailColumnEng.trim();
    }

    public String getDetailColumnChina() {
        return detailColumnChina;
    }

    public void setDetailColumnChina(String detailColumnChina) {
        this.detailColumnChina = detailColumnChina == null ? null : detailColumnChina.trim();
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode == null ? null : transferCode.trim();
    }
}