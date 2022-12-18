package com.adamrosyad.aaaaaaaaaaisyah;

public class laptransb {
    String kdtrans;
    String tgl;
    int total;
    int diskon;
    double gtotal;
    int bayar;
    int kembali;

    public laptransb(String kdtrans, String tgl, int total, int diskon, double gtotal, int bayar, int kembali) {
        this.kdtrans = kdtrans;
        this.tgl = tgl;
        this.total = total;
        this.diskon = diskon;
        this.gtotal = gtotal;
        this.bayar = bayar;
        this.kembali = kembali;
    }

    public String getKdtrans() {
        return kdtrans;
    }

    public void setKdtrans(String kdtrans) {
        this.kdtrans = kdtrans;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public double getGtotal() {
        return gtotal;
    }

    public void setGtotal(double gtotal) {
        this.gtotal = gtotal;
    }

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

    public int getKembali() {
        return kembali;
    }

    public void setKembali(int kembali) {
        this.kembali = kembali;
    }
}
