package com.rpl.reseppedia.source.remote.response;


public class SaranResponse {

    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getPorsi() {
        return porsi;
    }

    public void setPorsi(String porsi) {
        this.porsi = porsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getCara_masak() {
        return cara_masak;
    }

    public void setCara_masak(String cara_masak) {
        this.cara_masak = cara_masak;
    }

    private String penulis;
    private String waktu;
    private String porsi;
    private String deskripsi;
    private String bahan;
    private String cara_masak;

    public SaranResponse(String nama, String penulis, String waktu, String porsi, String deskripsi, String bahan, String cara_masak) {
        this.nama = nama;
        this.penulis = penulis;
        this.waktu = waktu;
        this.porsi = porsi;
        this.deskripsi = deskripsi;
        this.bahan = bahan;
        this.cara_masak = cara_masak;
    }
}
