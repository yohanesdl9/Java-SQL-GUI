/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataasistentetap;

import helper.Tanggal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ButtonModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author yohan
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    int form_mode = 0; // mode form secara default, yaitu 0 untuk insert, 1 untuk update
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    static String selectedData;
    DefaultTableModel dtm;
    Koneksi conn = new Koneksi();
    Object[] row = {"Nama", "Tanggal Lahir", "Unit Kerja"};
    String date_now = sdf.format(now);
    Mod_Asisten mod = new Mod_Asisten();
    int id;
    Tanggal tgl = new Tanggal();

    public MainPage() {
        initComponents();
        dtm = new DefaultTableModel(null, row);
        tbAsisten.setModel(dtm);
        loadDataAsisten();
        setFormTanggal();
        setUnitKerja();
    }

    public void loadDataAsisten() {
        if (dtm.getRowCount() != 0) {
            resetTable();
        }
        try {
            String sql = "SELECT * FROM asisten;";
            ResultSet rs = conn.stmt.executeQuery(sql);
            while (rs.next()) {
                String nama = rs.getString("nama");
                String ttl = formatTanggal(rs.getString("tanggal_lahir"));
                String unit = rs.getString("unit_kerja");
                String[] data = {nama, ttl, unit};
                dtm.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetTable() {
        int row_count = dtm.getRowCount();
        for (int i = 0; i < row_count; i++) {
            dtm.removeRow(0);
        }
    }

    public String formatTanggal(String date) {
        String[] data = date.split("-");
        int tanggal = Integer.parseInt(data[2]);
        int bulan = Integer.parseInt(data[1]);
        int tahun = Integer.parseInt(data[0]);
        return tanggal + " " + tgl.getNamaBulan(bulan) + " " + tahun;
    }

    public void setFormTanggal() {
        setComboBoxTahun();
        setComboBoxTanggal();
        setFormTanggal(date_now);
    }

    public void setFormTanggal(String date) {
        String[] dates = date.split("-");
        cbBulan.setSelectedIndex(Integer.parseInt(dates[1]) - 1);
        cbTahun.setSelectedItem(dates[0]);
        cbTanggal.setSelectedIndex(Integer.parseInt(dates[2]) - 1);
    }

    public void setComboBoxTahun() {
        for (int i = 1900; i <= 2050; i++) {
            cbTahun.addItem(Integer.toString(i));
        }
    }

    /*
    * Function ini untuk mengeset combo-box tanggal secara dinamis sehingga 
    dapat berubah-ubah isinya berdasarkan bulan dan tahun yang dipilih
     */
    public void setComboBoxTanggal() {
        cbBulan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cur_tanggal = cbTanggal.getSelectedIndex();
                cbTanggal.removeAllItems();
                int bulan = cbBulan.getSelectedIndex() + 1;
                int tahun = Integer.parseInt(cbTahun.getSelectedItem().toString());
                for (int i = 1; i <= tgl.jumlahHari(bulan, tahun); i++) {
                    cbTanggal.addItem(Integer.toString(i));
                }
                if (cur_tanggal <= cbTanggal.getItemCount()){
                    cbTanggal.setSelectedIndex(cur_tanggal);
                }
            }
        });
    }

    /* Function untuk memastikan bahwa semua form telah diisi secara lengkap
     */
    public boolean validateForm() {
        return txtNama.getText().equals("") || (radioL.isSelected() || radioP.isSelected()) || cbUnitKerja.getSelectedIndex() == 0;
    }

    public void setUnitKerja() {
        if (cbUnitKerja.getItemCount() > 1) {
            for (int i = 1; i < cbUnitKerja.getItemCount(); i++) {
                cbUnitKerja.removeItemAt(i);
            }
        }
        for (int i = 0; i < DataAsistenTetap.UNIT_KERJA.length; i++) {
            cbUnitKerja.addItem(DataAsistenTetap.UNIT_KERJA[i]);
        }
    }
    
    public void resetForm(){
        txtNama.setText("");
        setFormTanggal();
        jk.clearSelection();
        cbUnitKerja.setSelectedIndex(0);
        form_mode = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jk = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsisten = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        cbUnitKerja = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioL = new javax.swing.JRadioButton();
        radioP = new javax.swing.JRadioButton();
        cbBulan = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        cbTanggal = new javax.swing.JComboBox<>();
        cbTahun = new javax.swing.JComboBox<>();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIASTAP STIKI");

        tbAsisten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama", "Tanggal Lahir", "Unit Kerja"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbAsisten);
        if (tbAsisten.getColumnModel().getColumnCount() > 0) {
            tbAsisten.getColumnModel().getColumn(0).setResizable(false);
            tbAsisten.getColumnModel().getColumn(1).setResizable(false);
            tbAsisten.getColumnModel().getColumn(2).setResizable(false);
        }

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sistem Informasi Asisten Tetap STIKI Malang");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setText("Untuk update/delete data, pilih data yang akan diubah/dihapus lalu klik Update/Delete");

        cbUnitKerja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Unit Kerja" }));

        jLabel5.setText("Unit Kerja");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nama");

        jLabel6.setText("Jenis Kelamin");

        jLabel7.setText("Tanggal Lahir");

        jk.add(radioL);
        radioL.setText("Laki-Laki");

        jk.add(radioP);
        radioP.setText("Perempuan");

        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbUnitKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTahun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioL)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioP)
                                        .addGap(62, 62, 62))
                                    .addComponent(txtNama)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(radioL)
                            .addComponent(radioP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUnitKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnReset)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnPrint))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // cek apakah form telah diisi lengkap ataukan tidak
        if (!validateForm()) {
            JOptionPane.showMessageDialog(null, "Harap masukkan data dengan benar!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                // ambil data dari isian form
                String nama = txtNama.getText();
                char jenis = ' ';
                if (radioL.isSelected()) {
                    jenis = 'L';
                } else if (radioP.isSelected()) {
                    jenis = 'P';
                }
                String tgl_lahir = cbTahun.getSelectedItem().toString() + "-" + (cbBulan.getSelectedIndex() + 1) + "-" + cbTanggal.getSelectedItem().toString();
                String unit_kerja = cbUnitKerja.getSelectedItem().toString();
                // assign data yang telah diambil ke dalam class Asisten untuk dimanipulasi dalam database
                Asisten ast = new Asisten(nama, jenis, tgl_lahir, unit_kerja);
                if (form_mode == 0){
                    mod.insertAsisten(ast);
                    JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    mod.updateAsisten(id, ast);
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }
                // reset form sehingga isian kembali kosong dan muat ulang data asisten di tabel
                resetForm();
                loadDataAsisten();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // mendapatkan ButtonModel dari masing-masing radio button
        ButtonModel btn_L = radioL.getModel();
        ButtonModel btn_P = radioP.getModel();
        if (tbAsisten.getSelectedRow() == -1) {
            // script ini akan dijalankan bila tidak ada baris dari tabel yang dipilih
            JOptionPane.showMessageDialog(null, "Tidak ada baris tabel yang dipilih", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            // ambil data dari kolom pertama dan baris yang dipilih
            selectedData = tbAsisten.getValueAt(tbAsisten.getSelectedRow(), 0).toString();
            // ubah mode form ke dalam mode update
            form_mode = 1;
            try {
                Asisten ast = mod.getSelectedAsisten(selectedData);
                id = ast.nip;
                // set form sehingga terisi dengan data yang diambil dari tabel
                txtNama.setText(ast.nama);
                if (ast.jk == 'L') {
                    jk.setSelected(btn_L, true);
                } else {
                    jk.setSelected(btn_P, true);
                }
                setFormTanggal(ast.tanggal_lahir);
                cbUnitKerja.setSelectedItem(ast.unit_kerja);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (tbAsisten.getSelectedRow() == -1) {
            // script ini akan dijalankan bila tidak ada baris dari tabel yang dipilih
            JOptionPane.showMessageDialog(null, "Tidak ada baris tabel yang dipilih", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            selectedData = tbAsisten.getValueAt(tbAsisten.getSelectedRow(), 0).toString();
            // munculkan dialog konfirmasi untuk menghapus data
            int konfirm_hapus = JOptionPane.showConfirmDialog(null,
                    "Apakah Anda yakin ingin menghapus jadwal yang dipilih?", "Konfirmasi Hapus",
                    JOptionPane.OK_CANCEL_OPTION);
            if (konfirm_hapus == JOptionPane.OK_OPTION) {
                try {
                    mod.deleteAsisten(selectedData);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            resetForm();
            loadDataAsisten();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try {
            HashMap parameters = new HashMap();
            File file = new File("src/dataasistentetap/DataAsisten.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn.koneksi);
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JComboBox<String> cbTanggal;
    private javax.swing.JComboBox<String> cbUnitKerja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup jk;
    private javax.swing.JRadioButton radioL;
    private javax.swing.JRadioButton radioP;
    private javax.swing.JTable tbAsisten;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
