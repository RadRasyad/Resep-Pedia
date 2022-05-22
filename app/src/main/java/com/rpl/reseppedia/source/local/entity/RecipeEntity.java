package com.rpl.reseppedia.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipe")
public class RecipeEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String nama;

    @ColumnInfo(name = "penulis")
    private String penulis;

    @ColumnInfo(name = "ditulis")
    private String ditulis;

    @ColumnInfo(name = "waktu")
    private String waktu;

    @ColumnInfo(name = "porsi")
    private String porsi;

    @ColumnInfo(name = "kesulitan")
    private String kesulitan;

    @ColumnInfo(name = "kategori")
    private String kategori;

    @ColumnInfo(name = "deskripsi")
    private String deksripsi;

    @ColumnInfo(name = "foto")
    private String foto;

    @ColumnInfo(name = "bahan")
    private ArrayList<String> bahan;

    @ColumnInfo(name = "caraMasak")
    private ArrayList<String> caraMasak;

    public RecipeEntity(@NonNull String id, String nama, String penulis, String ditulis, String waktu, String porsi, String kesulitan, String kategori, String deksripsi, String foto, ArrayList<String> bahan, ArrayList<String> caraMasak) {
        this.id = id;
        this.nama = nama;
        this.penulis = penulis;
        this.ditulis = ditulis;
        this.waktu = waktu;
        this.porsi = porsi;
        this.kesulitan = kesulitan;
        this.kategori = kategori;
        this.deksripsi = deksripsi;
        this.foto = foto;
        this.bahan = bahan;
        this.caraMasak = caraMasak;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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
