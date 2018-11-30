/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataasistentetap;

import java.sql.*;
/**
 *
 * @author yohan
 */
public class Asisten extends Koneksi {
    int nip;
    String nama;
    char jk;
    String tanggal_lahir;
    String unit_kerja;
    
    public Asisten(String nama, char jk, String tanggal_lahir, String unit_kerja) {
        this.nama = nama;
        this.jk = jk;
        this.tanggal_lahir = tanggal_lahir;
        this.unit_kerja = unit_kerja;
    }
    
    public Asisten(int nip, String nama, char jk, String tanggal_lahir, String unit_kerja) {
        this.nip = nip;
        this.nama = nama;
        this.jk = jk;
        this.tanggal_lahir = tanggal_lahir;
        this.unit_kerja = unit_kerja;
    }
    
    
}
