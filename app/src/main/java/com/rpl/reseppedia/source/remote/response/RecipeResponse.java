package com.rpl.reseppedia.source.remote.response;


import java.util.ArrayList;

public class RecipeResponse {

    private String id;
    private String name;
    private String penulis;
    private String dirilis;
    private String waktu;
    private String porsi;
    private String kesulitan;
    private String deksripsi;
    private String foto;
    private ArrayList<String> bahan;
    private ArrayList<String> caraMasak;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getDirilis() {
        return dirilis;
    }

    public void setDirilis(String dirilis) {
        this.dirilis = dirilis;
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

    public String getDeksripsi() {
        return deksripsi;
    }

    public void setDeksripsi(String deksripsi) {
        this.deksripsi = deksripsi;
    }

    public ArrayList<String> getBahan() {
        return bahan;
    }

    public void setBahan(ArrayList<String> bahan) {
        this.bahan = bahan;
    }

    public ArrayList<String> getCaraMasak() {
        return caraMasak;
    }

    public void setCaraMasak(ArrayList<String> caraMasak) {
        this.caraMasak = caraMasak;
    }

}
