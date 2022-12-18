package com.adamrosyad.aaaaaaaaaaisyah;

public class user {
    String id, nama, alamat, tlp, akses, pass;

    public user(String id, String nama, String alamat, String tlp, String akses, String pass) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.tlp = tlp;
        this.akses = akses;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getAkses() {
        return akses;
    }

    public void setAkses(String akses) {
        this.akses = akses;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
