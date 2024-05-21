/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Barang;


/**
 *
 * @author user
 */
public class daoBarang {
    Connection connection;
    final String insert = "INSET INTO barang (kode, nama, jumlah, harga, merk) VALUE (?,?,?,?,?)";
    final String update = "UPDATE barang SET nama=?, jumlah=?, harga=?, merk=? WHERE kode=?;" ;
    final String delete = "DELETE FROM barang WHERE kode=?;";
    final String select = "SELECT * FROM barang ORDER BY kode ASC;";
    final String selectData = "SELECT * FROM barang where kode=?;";
    final String cekKode = "SELECT * FROM barang where kode=?;";

    public daoBarang() {
        connection = koneksi.connection();
}
    public void tambah(Barang brg) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, brg.getKode());
            statement.setString(2, brg.getNama());
            statement.setInt(3, brg.getJumlah());
            statement.setInt(4, brg.getHarga());
            statement.setString(5, brg.getMerek());
            statement.executeUpdate();
        }  catch (Exception e) {
            
        }
    }
    
    public void ubah(Barang brg) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, brg.getNama());
            statement.setInt(2, brg.getJumlah());
            statement.setInt(3, brg.getHarga());
            statement.setString(4, brg.getMerek());
            statement.setString(5, brg.getKode());
            statement.executeUpdate();
        } catch (Exception e) {
            
        }
    }
    
    public void hapus(Barang brg) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, brg.getKode());
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void tampil (Barang brg) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(selectData);
            statement.setString(1, brg.getKode());
            statement.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
        
    }
    
    public List<Barang> getData() {
        List<Barang> listBrg = null;
        try {
            listBrg = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Barang brg = new Barang();
                brg.setKode(rs.getString("kode"));
                brg.setNama(rs.getString("nama"));
                brg.setJumlah(rs.getInt("jumlah"));
                brg.setHarga(rs.getInt("harga"));
                brg.setMerek(rs.getString("merek"));
                listBrg.add(brg);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(null, e);
        }
        return listBrg;
    }
    public int cekKode (String kode) {
        PreparedStatement statement = null;
        int ketemu = 0;
        try {
            statement = connection.prepareStatement(selectData);
            statement.setString(1, kode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ketemu++;
            }
        } catch (Exception e) {
    }
    return ketemu;
}
}

