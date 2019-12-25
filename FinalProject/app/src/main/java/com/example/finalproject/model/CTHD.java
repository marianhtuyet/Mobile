package com.example.finalproject.model;

public class CTHD {
    private int masp;
    private int price;
    private String name;
    private int soluong;
    private String image;

    public CTHD( int masp, int price, String name, int soluong, String image) {

        this.masp = masp;
        this.price = price;
        this.name = name;
        this.soluong = soluong;
        this.image = image;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
