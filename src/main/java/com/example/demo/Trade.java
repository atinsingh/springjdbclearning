package com.example.demo;

public class Trade {
    private Long id;
    private String account;
    private String security;
    private Long quantity;
    private String status;
    private String direction;

    public Trade(Long id, String account, String security, Long quantity, String status, String direction) {
        this.id = id;
        this.account = account;
        this.security = security;
        this.quantity = quantity;
        this.status = status;
        this.direction = direction;
    }

    public Trade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", security='" + security + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
