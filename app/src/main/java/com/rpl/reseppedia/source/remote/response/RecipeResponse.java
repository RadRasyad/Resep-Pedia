package com.rpl.reseppedia.source.remote.response;


import java.util.ArrayList;

public class RecipeResponse {

    private String id;
    private String nama;
    private String penulis;
    private String ditulis;
    private String waktu;
    private String porsi;
    private String kesulitan;
    private String kategori;
    private String deskripsi;
    private String foto;
    private ArrayList<String> bahan;
    private ArrayList<String> cara_masak;

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

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getDitulis() {
        return ditulis;
    }

    public void setDitulis(String ditulis) {
        this.ditulis = ditulis;
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

    public String getKesulitan() {
        return kesulitan;
    }

    public void setKesulitan(String kesulitan) {
        this.kesulitan = kesulitan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ArrayList<String> getBahan() {
        return bahan;
    }

    public void setBahan(ArrayList<String> bahan) {
        this.bahan = bahan;
    }

    public ArrayList<String> getCara_masak() {
        return cara_masak;
    }

    public void setCara_masak(ArrayList<String> cara_masak) {
        this.cara_masak = cara_masak;
    }



}
