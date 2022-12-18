package com.adamrosyad.aaaaaaaaaaisyah;

public class barang {
    String kdbar, nambar, satbar, stok, hrgbeli, hrgjual;

    public barang(String kdbar, String nambar, String satbar, String stok, String hrgbeli, String hrgjual) {
        this.kdbar = kdbar;
        this.nambar = nambar;
        this.satbar = satbar;
        this.stok = stok;
        this.hrgbeli = hrgbeli;
        this.hrgjual = hrgjual;
    }

    public String getKdbar() {
        return kdbar;
    }

    public void setKdbar(String kdbar) {
        this.kdbar = kdbar;
    }

    public String getNambar() {
        return nambar;
    }

    public void setNambar(String nambar) {
        this.nambar = nambar;
    }

    public String getSatbar() {
        return satbar;
    }

    public void setSatbar(String satbar) {
        this.satbar = satbar;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHrgbeli() {
        return hrgbeli;
    }

    public void setHrgbeli(String hrgbeli) {
        this.hrgbeli = hrgbeli;
    }

    public String getHrgjual() {
        return hrgjual;
    }

    public void setHrgjual(String hrgjual) {
        this.hrgjual = hrgjual;
    }
}
