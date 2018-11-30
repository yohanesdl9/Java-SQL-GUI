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
public class Mod_Asisten extends Koneksi {

    public Asisten getSelectedAsisten(String nama) throws Exception {
        Asisten ast = null;
        String sql = "SELECT * FROM asisten WHERE nama = '" + nama + "'";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int nip = rs.getInt("nip");
            char jk = rs.getString("jk").charAt(0);
            String tanggal_lahir = rs.getString("tanggal_lahir");
            String unit_kerja = rs.getString("unit_kerja");
            ast = new Asisten(nip, nama, jk, tanggal_lahir, unit_kerja);
        }
        return ast;
    }
    
    public void insertAsisten(Asisten ast) throws Exception {
        String sql = "INSERT INTO asisten SET nama = '" + ast.nama + "', jk = '" + ast.jk
                + "', tanggal_lahir = '" + ast.tanggal_lahir + "', unit_kerja = '" 
                + ast.unit_kerja + "';";
        stmt.executeUpdate(sql);
    }

    public void updateAsisten(int nip, Asisten ast) throws Exception {
        String sql = "UPDATE asisten SET nama = '" + ast.nama + "', jk = '" + ast.jk
                + "', tanggal_lahir = '" + ast.tanggal_lahir + "', unit_kerja = '" 
                + ast.unit_kerja + "' WHERE nip = '" + nip + "';";
        stmt.executeUpdate(sql);
    }

    public void deleteAsisten(String nama) throws Exception {
        String sql = "DELETE FROM asisten WHERE nama = '" + nama + "'";
        stmt.executeUpdate(sql);
    }
}
