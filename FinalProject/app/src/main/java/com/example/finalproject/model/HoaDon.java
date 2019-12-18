package com.example.finalproject.model;

import java.util.Date;

public class HoaDon {
    private int mahd;
    private int makh;
    private int tongtien;
    private String nguoinhan;
    private String sodienthoai;
    private String diachigiao;
    private Date ngaythanhtoan;

    public HoaDon(int mahd, int makh, int tongtien, String nguoinhan, String sodienthoai, String diachigiao, Date ngaythanhtoan) {
        this.mahd = mahd;
        this.makh = makh;
        this.tongtien = tongtien;
        this.nguoinhan = nguoinhan;
        this.sodienthoai = sodienthoai;
        this.diachigiao = diachigiao;
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getNguoinhan() {
        return nguoinhan;
    }

    public void setNguoinhan(String nguoinhan) {
        this.nguoinhan = nguoinhan;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachigiao() {
        return diachigiao;
    }

    public void setDiachigiao(String diachigiao) {
        this.diachigiao = diachigiao;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }
}