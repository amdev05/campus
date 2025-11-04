/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pbo11.pkg10124433;

import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Ademuchl
 */
public class frm_pesanan extends javax.swing.JFrame {
    
    //DEKLARASI VARIABEL
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    /**
     * Creates new form frm_pesanan
     */
    public frm_pesanan() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_pesanan.setModel(tableModel);
        settableload();
        setcbbcari();
        setcbblevel();
        
        idpel();
        kd_menu();
        
    }
    
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
        //MEMBUAT JUDUL HEADER
        return new javax.swing.table.DefaultTableModel
        (
                new Object[][] {},
                new String [] {"KDP","ID PELANGGAN", "MENU","LEVEL", "JUMLAH", "TOTAL"}
        )
        //DISABLE PERUBAHAN PADA GRID
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
       
    }
    
    
    String data[]=new String[6];
    private void settableload(){
        String stat = "";
        try{
            
//            tabel_pesanan.getColumnModel().getColum/n(0).setPreferredWidth(100);  
           
            tabel_pesanan.getColumnModel().getColumn(1).setPreferredWidth(250);  
            tabel_pesanan.getColumnModel().getColumn(2).setPreferredWidth(250);  
            
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt = kon.createStatement();
            String SQL = "SELECT ps.*, pl.nama_pelanggan, mn.* "
                    + "FROM t_pesanan ps "
                    + "LEFT JOIN t_pelanggan pl ON ps.id_pelanggan = pl.id_pelanggan "
                    + "LEFT JOIN t_menu mn ON ps.kd_menu = mn.kd_menu "
                    + "WHERE status='proses' "
                    + "ORDER BY kd_pesanan DESC";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString("kd_pesanan");
                data[1] = res.getString("id_pelanggan")+"-"+res.getString("nama_pelanggan");
                data[2] = res.getString("kd_menu")+"-"+res.getString("nama_menu")+"-"+res.getString("harga_menu");
                data[3] = res.getString("level");
                data[4] = res.getString("jumlah");
                data[5] = res.getString("total");
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public void setcbbcari(){
        filter_pesanan.addItem("KD Pesanan");
        filter_pesanan.addItem("ID Pelanggan");
        filter_pesanan.addItem("Nama Pelanggan");
        filter_pesanan.addItem("KD Menu");
        filter_pesanan.setSelectedItem("KD Pesanan");
    }
    
    public void setcbblevel(){
        cbb_level.addItem("Lv 0");
        cbb_level.addItem("Lv 1");
        cbb_level.addItem("Lv 2");
        cbb_level.addItem("Lv 3");
        cbb_level.addItem("Lv 4");
        cbb_level.addItem("Lv 5");
        cbb_level.setSelectedItem(" ");
    }
    
    public void membersihkan_teks(){
        txt_kd_pesanan.setText("");
        cbb_id_pelanggan.setSelectedItem(" ");
        cbb_kd_menu.setSelectedItem(" ");
        cbb_level.setSelectedItem(" ");
        txt_jumlah.setText("");
        txt_total.setText("");
    }
     public void nonaktif_teks(){
        txt_kd_pesanan.setEnabled(false);
        cbb_id_pelanggan.setEnabled(false);
        cbb_level.setEnabled(false);
        cbb_kd_menu.setEnabled(false);
        txt_jumlah.setEnabled(false);
        txt_total.setEnabled(false);
    }
      public void aktif_teks(){
        cbb_id_pelanggan.setEnabled(true);
        cbb_kd_menu.setEnabled(true);
        cbb_level.setEnabled(true);
        txt_jumlah.setEnabled(true);       
    }
      
      //FUNSGI TAMPIL DATA SAAT DI KLIK
      int row = 0;
      public void tampil_field()
      {
          row = tabel_pesanan.getSelectedRow();
          txt_kd_pesanan.setText(tableModel.getValueAt(row, 0).toString());
          cbb_id_pelanggan.setSelectedItem(tableModel.getValueAt(row, 1).toString());
          cbb_kd_menu.setSelectedItem(tableModel.getValueAt(row, 2).toString());
          cbb_level.setSelectedItem(tableModel.getValueAt(row, 3).toString());
          txt_jumlah.setText(tableModel.getValueAt(row, 4).toString());
          txt_total.setText(tableModel.getValueAt(row, 5).toString());
         
          aktif_teks();
          btn_simpan.setEnabled(false);
          btn_pel_baru.setEnabled(true);
          btn_tambah.setEnabled(true);
          btn_ubah.setEnabled(true);
          btn_hapus.setEnabled(true);
          btn_batal.setEnabled(true);
          btn_hitung.setEnabled(true);
      }
    
    private void idpel(){
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt = kon.createStatement();
            String SQL = "SELECT id_pelanggan, nama_pelanggan FROM t_pelanggan";
            ResultSet res = stt.executeQuery(SQL);
            
            while(res.next()){
                String id = res.getString("id_pelanggan");
                String nama = res.getString("nama_pelanggan");
                cbb_id_pelanggan.addItem(id + "-" + nama);
            }
            
            res.close();
            stt.close();
            kon.close();        
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
    }
    private void kd_menu(){
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt = kon.createStatement();
            String SQL = "SELECT kd_menu, nama_menu, harga_menu FROM t_menu";
            ResultSet res = stt.executeQuery(SQL);
            
            while(res.next()){
                String id = res.getString("kd_menu");
                String nama = res.getString("nama_menu");
                String harga = res.getString("harga_menu");
                cbb_kd_menu.addItem(id + "-" + nama + "-" + harga);
            }
            
            res.close();
            stt.close();
            kon.close();        
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
    }
    public String buatkdp()
    {
        String kdp = "KP001";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT kd_pesanan FROM t_pesanan ORDER BY kd_pesanan DESC LIMIT 1";
            ResultSet res = stt.executeQuery(SQL);
            
            if(res.next()){
                String kode_akhir = res.getString("kd_pesanan");
                int angka = Integer.parseInt(kode_akhir.substring(2));
                angka++;
                kdp = String.format("KP%03d", angka);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return kdp;
        
    }
    
    public String hitungtotal(){
        
           String harga_menu = cbb_kd_menu.getSelectedItem().toString().split("-")[2];
           int jumlah = Integer.parseInt(txt_jumlah.getText());
           int harga = Integer.parseInt(harga_menu);

           int total = harga*jumlah;           
           return String.valueOf(total);
                   
    }
    
//    public String idpterpilih(){
//        String terpilih = cbb_idp.getSelectedItem()
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_menu = new javax.swing.JButton();
        btn_pelanggan = new javax.swing.JButton();
        btn_pesanan = new javax.swing.JButton();
        btn_status = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlabel1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlabel2 = new javax.swing.JLabel();
        jlabel3 = new javax.swing.JLabel();
        jlabel4 = new javax.swing.JLabel();
        jlabel5 = new javax.swing.JLabel();
        txt_kd_pesanan = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_pel_baru = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        filter_pesanan = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pesanan = new javax.swing.JTable();
        txt_src_pesanan = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_batal_cari = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbb_kd_menu = new javax.swing.JComboBox<>();
        cbb_id_pelanggan = new javax.swing.JComboBox<>();
        btn_hitung = new javax.swing.JButton();
        jlabel6 = new javax.swing.JLabel();
        cbb_level = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btn_menu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_menu.setText("DAFTAR MENU");
        btn_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menuActionPerformed(evt);
            }
        });

        btn_pelanggan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_pelanggan.setText("DAFTAR PELANGGAN");
        btn_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pelangganActionPerformed(evt);
            }
        });

        btn_pesanan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_pesanan.setText("DAFTAR PESANAN");
        btn_pesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesananActionPerformed(evt);
            }
        });

        btn_status.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_status.setText("STATUS PESANAN");
        btn_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_statusActionPerformed(evt);
            }
        });

        btn_kembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_kembali.setText("KEMBALI");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_pesanan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btn_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_pesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_status, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jlabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlabel1.setText("KDP");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Daftar Pesanan");

        jlabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlabel2.setText("ID Pelanggan");

        jlabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlabel3.setText("Kode Menu");

        jlabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlabel4.setText("Total");

        jlabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlabel5.setText("Jumlah");

        btn_tambah.setText("Tambah Pesanan");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_pel_baru.setText("Pelanggan Baru");
        btn_pel_baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pel_baruActionPerformed(evt);
            }
        });

        btn_ubah.setText("Ubah Pesanan");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus Pesanan");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        tabel_pesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_pesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pesanan);

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        btn_batal_cari.setText("Batal");
        btn_batal_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batal_cariActionPerformed(evt);
            }
        });

        jLabel5.setText("Cari Pesanan");

        cbb_kd_menu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        cbb_id_pelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        btn_hitung.setText("Hitung");
        btn_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitungActionPerformed(evt);
            }
        });

        jlabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlabel6.setText("Level");

        cbb_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jlabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbb_kd_menu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbb_id_pelanggan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_jumlah)
                            .addComponent(txt_kd_pesanan)
                            .addComponent(cbb_level, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(131, 131, 131)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(btn_pel_baru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(filter_pesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_src_pesanan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_batal_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
                .addGap(37, 37, 37))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kd_pesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pel_baru, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbb_id_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbb_kd_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_src_pesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filter_pesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_batal_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_level, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        nonaktif_teks();        
        btn_simpan.setEnabled(false);
        btn_pel_baru.setEnabled(true);
        btn_tambah.setEnabled(true);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_batal.setEnabled(false);
        btn_hitung.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void tabel_pesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pesananMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            tampil_field();
        }
    }//GEN-LAST:event_tabel_pesananMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        cbb_id_pelanggan.requestFocus();
       
        txt_kd_pesanan.setText(buatkdp());
        
        aktif_teks();        
        btn_simpan.setEnabled(true);
        btn_tambah.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_batal.setEnabled(true);
        btn_hitung.setEnabled(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[6];
        if((cbb_id_pelanggan.getSelectedItem()==" ") || (cbb_kd_menu.getSelectedItem()==" ") || (cbb_level.getSelectedItem()==" ") || (txt_jumlah.getText().isEmpty()) )
        {
            JOptionPane.showMessageDialog(null, "data belum lengkap, silahkan lengkapi data.");
            cbb_id_pelanggan.requestFocus();
        }
        else 
        {
            try
            {   
                
                String id_pelanggan = cbb_id_pelanggan.getSelectedItem().toString();
                String kd_menu = cbb_kd_menu.getSelectedItem().toString();
                String level = cbb_level.getSelectedItem().toString();
                
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_pesanan(kd_pesanan, id_pelanggan,kd_menu,level,jumlah,total,status) VALUES" 
                        + "('" + txt_kd_pesanan.getText() + "',"
                        + "'" + id_pelanggan.split("-")[0] + "',"
                        + "'" + kd_menu.split("-")[0] + "',"
                        + "'" + level + "',"
                        + "'" + txt_jumlah.getText() + "',"
                        + "'" + hitungtotal() + "',"
                        + "'" + "proses" + "')";
                
                stt.executeUpdate(SQL);
                data[0] = txt_kd_pesanan.getText();
                data[1] = cbb_id_pelanggan.getSelectedItem().toString();
                data[2] = cbb_kd_menu.getSelectedItem().toString();
                data[3] = cbb_level.getSelectedItem().toString();
                data[4] = txt_jumlah.getText();
                data[5] = hitungtotal();
                tableModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                nonaktif_teks();
                btn_simpan.setEnabled(false);
                btn_tambah.setEnabled(true);
                btn_ubah.setEnabled(false);
                btn_hapus.setEnabled(false);
                btn_batal.setEnabled(false);
                btn_hitung.setEnabled(false);
            } 
            catch(Exception ex)
            {
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                  JOptionPane.showMessageDialog(null, "Masukkan data dengan benar!");
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        String kd_pesanan = txt_kd_pesanan.getText();
        String id_pelanggan = cbb_id_pelanggan.getSelectedItem().toString();
        String kd_menu = cbb_kd_menu.getSelectedItem().toString();
        String level = cbb_level.getSelectedItem().toString();
        String jumlah = txt_jumlah.getText();
        String total = hitungtotal();
        
        if((cbb_id_pelanggan.getSelectedItem()==" ") || (cbb_kd_menu.getSelectedItem()==" ") || (cbb_level.getSelectedItem()==" ") || (txt_jumlah.getText().isEmpty()) )
        {
            JOptionPane.showMessageDialog(null, "data belum lengkap, silahkan lengkapi data.");
            cbb_id_pelanggan.requestFocus();
        }
        else 
        {
            try
            {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE `t_pesanan` SET "
                        + "`id_pelanggan`='" + id_pelanggan.split("-")[0] + "',"
                        + "`kd_menu`='" + kd_menu.split("-")[0] + "',"
                        + "`level`='" + level + "',"
                        + "`jumlah`='" + jumlah + "',"
                        + "`total`='" + total + "' "
                        + "WHERE "
                        + "kd_pesanan='"+tableModel.getValueAt(row, 0).toString()+"';";
                
                stt.executeUpdate(SQL);
                data[0] = kd_pesanan;
                data[1] = id_pelanggan;
                data[2] = kd_menu;
                data[3] = level;
                data[4] = jumlah;
                data[5] = total;
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
               
                membersihkan_teks();
                nonaktif_teks();
                btn_simpan.setEnabled(false);
                btn_tambah.setEnabled(true);
                btn_ubah.setEnabled(false);
                btn_hapus.setEnabled(false);
                btn_batal.setEnabled(false);
                btn_hitung.setEnabled(false);
            }
            catch(Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitungActionPerformed
        // TODO add your handling code here:
        txt_total.setText(hitungtotal());
    }//GEN-LAST:event_btn_hitungActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try
        {
            int jawab = JOptionPane.showConfirmDialog(
              null,
              "Apakah kamu yakin ingin menghapus data ini?",
              "Konfirmasi Hapus",
              JOptionPane.YES_NO_OPTION);
            
            if (jawab == JOptionPane.YES_OPTION) {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "DELETE FROM t_pesanan WHERE "
                        +"kd_pesanan='"+tableModel.getValueAt(row,0).toString()+"'";
                stt.executeUpdate(SQL);
                tableModel.removeRow(row);
                stt.close();
                kon.close();

                membersihkan_teks();
                nonaktif_teks();
                btn_simpan.setEnabled(false);
                btn_tambah.setEnabled(true);
                btn_ubah.setEnabled(false);
                btn_hapus.setEnabled(false);
                btn_batal.setEnabled(false);
                btn_hitung.setEnabled(false);

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } else {
                // Penghapusan dibatalkan
                JOptionPane.showMessageDialog(null, "Penghapusan dibatalkan.");
            }
           
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        nonaktif_teks();
        btn_simpan.setEnabled(false);
        btn_tambah.setEnabled(true);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_batal.setEnabled(false);
        btn_hitung.setEnabled(false);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menuActionPerformed
        // TODO add your handling code here:
        frm_menu menu = new frm_menu();
        menu.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_menuActionPerformed

    private void btn_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pelangganActionPerformed
        // TODO add your handling code here:
        frm_pelanggan pelanggan = new frm_pelanggan();
        pelanggan.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_pelangganActionPerformed

    private void btn_pesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesananActionPerformed
        // TODO add your handling code here:
        frm_pesanan pesanan = new frm_pesanan();
        pesanan.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_pesananActionPerformed

    private void btn_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_statusActionPerformed
        // TODO add your handling code here:
        frm_status status = new frm_status();
        status.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_statusActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        if((txt_src_pesanan.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Isi kolom pencarian.");
            txt_src_pesanan.requestFocus();
        }
        
          
            
        else 
        {
            try
            {
                tableModel.setRowCount(0);
                
                String filter = "";
                if (filter_pesanan.getSelectedItem().toString().equals("KD Pesanan")) {
                    filter = "ps.kd_pesanan";
                } else if (filter_pesanan.getSelectedItem().toString().equals("ID Pelanggan")) {
                    filter = "ps.id_pelanggan";
                } else if (filter_pesanan.getSelectedItem().toString().equals("Nama Pelanggan")) {
                    filter = "pl.nama_pelanggan";
                } else if (filter_pesanan.getSelectedItem().toString().equals("KD Menu")) {
                    filter = "mn.kd_menu";
                }
            
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "SELECT ps.*, pl.nama_pelanggan, mn.* "
                        + "FROM t_pesanan ps "
                        + "LEFT JOIN t_pelanggan pl ON ps.id_pelanggan = pl.id_pelanggan "
                        + "LEFT JOIN t_menu mn ON ps.kd_menu = mn.kd_menu "
                        + "WHERE "
                        + "status='proses' AND "
                        + filter
                        + "='"
                        + txt_src_pesanan.getText()
                        + "'";

                ResultSet res = stt.executeQuery(SQL); 
                boolean ditemukan = false;
                while(res.next())
                {
                    data[0] = res.getString("kd_pesanan");
                    data[1] = res.getString("id_pelanggan")+"-"+res.getString("nama_pelanggan");
                    data[2] = res.getString("kd_menu")+"-"+res.getString("nama_menu")+"-"+res.getString("harga_menu");
                    data[3] = res.getString("level");
                    data[4] = res.getString("jumlah");
                    data[5] = res.getString("total");
                    tableModel.addRow(data);
                    
                    ditemukan=true;
                }
                
                res.close();
                stt.close();
                kon.close();   
                if(!ditemukan){
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                    settableload();
                }
            }
            catch(Exception ex)
            {
                System.err.println(ex.getMessage());
                
            }
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_batal_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batal_cariActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        settableload();
        txt_src_pesanan.setText("");
    }//GEN-LAST:event_btn_batal_cariActionPerformed

    private void btn_pel_baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pel_baruActionPerformed
        // TODO add your handling code here:
        frm_pelanggan pelanggan = new frm_pelanggan();
        pelanggan.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btn_pel_baruActionPerformed

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
            java.util.logging.Logger.getLogger(frm_pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_pesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_batal_cari;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_menu;
    private javax.swing.JButton btn_pel_baru;
    private javax.swing.JButton btn_pelanggan;
    private javax.swing.JButton btn_pesanan;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_status;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cbb_id_pelanggan;
    private javax.swing.JComboBox<String> cbb_kd_menu;
    private javax.swing.JComboBox<String> cbb_level;
    private javax.swing.JComboBox filter_pesanan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel jlabel2;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JLabel jlabel4;
    private javax.swing.JLabel jlabel5;
    private javax.swing.JLabel jlabel6;
    private javax.swing.JTable tabel_pesanan;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kd_pesanan;
    private javax.swing.JTextField txt_src_pesanan;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
