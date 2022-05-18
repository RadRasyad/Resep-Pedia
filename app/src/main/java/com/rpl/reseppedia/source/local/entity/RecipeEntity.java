package com.rpl.reseppedia.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.ArrayList;

@Entity(tableName = "recipeentity")
public class RecipeEntity {

    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "penulis")
    private String penulis;

    @ColumnInfo(name = "dirilis")
    private String dirilis;

    @ColumnInfo(name = "waktu")
    private String waktu;

    @ColumnInfo(name = "porsi")
    private String porsi;

    @ColumnInfo(name = "kesulitan")
    private String kesulitan;

    @ColumnInfo(name = "deskripsi")
    private String deksripsi;

    @ColumnInfo(name = "foto")
    private String foto;

    @ColumnInfo(name = "bahan")
    private ArrayList<String> bahan;

    @ColumnInfo(name = "caraMasak")
    private ArrayList<String> caraMasak;

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

    public ArrayList<String> getCaraMasak() {
        return caraMasak;
    }

    public void setCaraMasak(ArrayList<String> caraMasak) {
        this.caraMasak = caraMasak;
    }
}
