
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alejandro
 */
public class Principal extends javax.swing.JFrame {
    
    private Connection Conexion =null;
    private Statement st;
    //evita que la informacion adicional se meta a la base de datos
    private PreparedStatement pr;
    private ResultSet rs;
    private String Qry;
    private DefaultTableModel modelo;
    private int id;
   
    private Connection conexion = null; 
    private PreparedStatement pt;
    public String usuario;
    private Point point;
    private int row;
    private String tipousuario;
    private String claveu;
    private int AT;
    private String idf;
    /**
     * Creates new form Principal
     */
    public Principal(String nombre,String clave) {
        initComponents();
        tipousuario=nombre;
        claveu=clave;
        usuario = Usuario.getText();
        ConectaDB();
        ActualizaTabla(0);
    }
    
    public void ConectaDB() {
        String URL, Nombre;
        URL = "jdbc:postgresql://localhost:5432/Proyecto";
        Nombre = usuario;
        
        try {
            if("administrador".equals(tipousuario))
            {
                conexion = DriverManager.getConnection(URL, "administrador","123");
                if(conexion != null)
                {   
                }
            }
            else if("gerente".equals(tipousuario))
            {
                conexion = DriverManager.getConnection(URL, "gerente","123");
                if(conexion != null)
                {   
                }
            }
            else if("empleado".equals(tipousuario))
            {
                conexion = DriverManager.getConnection(URL, "empleado","123");
                if(conexion != null)
                {   
                }
            }
            //conexion = DriverManager.getConnection(URL, tipousuario, claveu);
            
        }
        catch(Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error en la conexión Principal");
        }
    }
    
    /*FUNCION CARGA LAS TABLAS EN CADA PAGINA*/
    public void ActualizaTabla(int AT){
         switch(AT){
            case 0:
                modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("Nombre");
                modelo.addColumn("Domicilio");
                modelo.addColumn("Email");
                modelo.addColumn("Telefono");
                modelo.addColumn("FechaNac");
                modelo.addColumn("Edad");

                try{
                    Qry = "SELECT * FROM Transaccion.Cliente";
                    st = conexion.createStatement();
                    rs = st.executeQuery(Qry);
                    String aux[] = new String[7];
                    while(rs.next()){
                        aux[0] = rs.getString(1);
                        aux[1] = rs.getString(2);                
                        aux[2] = rs.getString(3);
                        aux[3] = rs.getString(4);
                        aux[4] = rs.getString(5);
                        aux[5] = rs.getString(6);
                        aux[6] = rs.getString(7);
                        modelo.addRow(aux);
                    }
                    TablaClientes.setModel(modelo);
                }
                catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
            break;
            case 1:
                modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("Nombre");
                modelo.addColumn("Domicilio");
                modelo.addColumn("Email");
                modelo.addColumn("Telefono");
                modelo.addColumn("FechaNac");
                modelo.addColumn("Edad");

                try{
                    Qry = "SELECT * FROM Almacen.Vendedor";
                    st = conexion.createStatement();
                    rs = st.executeQuery(Qry);
                    String aux[] = new String[7];
                    while(rs.next()){
                        aux[0] = rs.getString(1);
                        aux[1] = rs.getString(2);                
                        aux[2] = rs.getString(3);
                        aux[3] = rs.getString(4);
                        aux[4] = rs.getString(5);
                        aux[5] = rs.getString(6);
                        aux[6] = rs.getString(7);
                        modelo.addRow(aux);
                    }
                    TablaVendedor.setModel(modelo);
                }
                catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
            break;
                
        }
    }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabClientes = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        TextNombre = new javax.swing.JTextField();
        TextDomicilio = new javax.swing.JTextField();
        TextEmail = new javax.swing.JTextField();
        TextTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BtnAgregarCliente = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        Usuario = new javax.swing.JTextField();
        diaC = new com.toedter.components.JSpinField();
        mesC = new com.toedter.calendar.JMonthChooser();
        añoC = new com.toedter.calendar.JYearChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TextNombreV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextDomicilioV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TextEmailV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TextTelefonoV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        BtnAgregarVendedor = new javax.swing.JButton();
        BtnModificaVendedor = new javax.swing.JButton();
        BtnEliminarVendedor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaVendedor = new javax.swing.JTable();
        diaV = new com.toedter.components.JSpinField();
        mesV = new com.toedter.calendar.JMonthChooser();
        añoV = new com.toedter.calendar.JYearChooser();
        Usuario1 = new javax.swing.JTextField();
        Usuario2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TextNombreP = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TextIdTP = new javax.swing.JTextField();
        TextPrecio = new javax.swing.JTextField();
        TextStock = new javax.swing.JTextField();
        ComboTamaño = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProdDetalleD = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        TablaProdDetalleD1 = new javax.swing.JTable();
        BTNAgregarDD = new javax.swing.JButton();
        BTNModificaDD = new javax.swing.JButton();
        BTNEliminaDD = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TablaProdDV = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        TablaDetalleV = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        BTNAgregarDV = new javax.swing.JButton();
        BTNModificarDV = new javax.swing.JButton();
        BTNEliminaDV = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabClientes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabClientesStateChanged(evt);
            }
        });

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TablaClientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(TablaClientes);

        jLabel1.setText("Nombre Completo");

        jLabel2.setText("Domicilio");

        jLabel3.setText("Correo Electrónico");

        jLabel4.setText("Telefono");

        jLabel5.setText("Fecha de Nacimiento");

        BtnAgregarCliente.setText("Agregar");
        BtnAgregarCliente.setMaximumSize(new java.awt.Dimension(75, 23));
        BtnAgregarCliente.setMinimumSize(new java.awt.Dimension(75, 23));
        BtnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarClienteActionPerformed(evt);
            }
        });

        BtnModificar.setText("Modificar");
        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });

        BtnEliminar.setText("Eliminar");
        BtnEliminar.setMaximumSize(new java.awt.Dimension(75, 23));
        BtnEliminar.setMinimumSize(new java.awt.Dimension(75, 23));
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        diaC.setMaximum(31);
        diaC.setMinimum(1);
        diaC.setValue(1);

        añoC.setEndYear(2019);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(299, Short.MAX_VALUE)
                .addComponent(BtnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(diaC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(mesC, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(añoC, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextDomicilio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(añoC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mesC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(diaC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnModificar)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        TabClientes.addTab("Clientes", jPanel1);

        jLabel6.setText("Nombre Completo");

        jLabel7.setText("Domicilio");

        jLabel8.setText("Correo Electrónico");

        jLabel9.setText("Telefono");

        jLabel10.setText("Fecha de Nacimiento");

        BtnAgregarVendedor.setText("Agregar");
        BtnAgregarVendedor.setMaximumSize(new java.awt.Dimension(75, 23));
        BtnAgregarVendedor.setMinimumSize(new java.awt.Dimension(75, 23));
        BtnAgregarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarVendedorActionPerformed(evt);
            }
        });

        BtnModificaVendedor.setText("Modificar");
        BtnModificaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificaVendedorActionPerformed(evt);
            }
        });

        BtnEliminarVendedor.setText("Eliminar");
        BtnEliminarVendedor.setMaximumSize(new java.awt.Dimension(75, 23));
        BtnEliminarVendedor.setMinimumSize(new java.awt.Dimension(75, 23));
        BtnEliminarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarVendedorActionPerformed(evt);
            }
        });

        TablaVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaVendedor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TablaVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TablaVendedorMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(TablaVendedor);

        diaV.setMaximum(31);
        diaV.setMinimum(1);
        diaV.setValue(1);

        añoV.setEndYear(2019);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(diaV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Usuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(mesV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(añoV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(TextTelefonoV)
                            .addComponent(TextEmailV)
                            .addComponent(TextDomicilioV)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(TextNombreV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnAgregarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(BtnModificaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(BtnEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(339, 339, 339)
                    .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(340, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextNombreV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextDomicilioV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextEmailV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextTelefonoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(diaV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mesV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(añoV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAgregarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnModificaVendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(Usuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(181, 181, 181)
                    .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(181, Short.MAX_VALUE)))
        );

        TabClientes.addTab("Vendedores", jPanel4);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable3);

        jLabel11.setText("Tipos de Productos");

        jLabel12.setText("Productos");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane6.setViewportView(jTextArea1);

        jLabel13.setText("Nombre");

        jLabel14.setText("Descripción");

        ComboTamaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual", "Matrimonial", "Queen size" }));

        jLabel15.setText("Tipo Producto");

        jLabel16.setText("Precio");

        jLabel17.setText("Stock");

        jLabel18.setText("Tamaño");

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(55, 55, 55)))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(58, 58, 58)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextIdTP)
                            .addComponent(TextPrecio)
                            .addComponent(TextStock)
                            .addComponent(ComboTamaño, 0, 175, Short.MAX_VALUE))
                        .addGap(99, 99, 99)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(TextNombreP))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        TabClientes.addTab("Productos", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        TabClientes.addTab("Ventas", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        TabClientes.addTab("Devoluciones", jPanel7);

        TablaProdDetalleD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaProdDetalleD);

        TablaProdDetalleD1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(TablaProdDetalleD1);

        BTNAgregarDD.setText("Agregar");

        BTNModificaDD.setText("Modificar");

        BTNEliminaDD.setText("Eliminar");

        jLabel19.setText("Producto");

        jLabel20.setText("Cantidad");

        jLabel21.setText("Productos");

        jLabel22.setText("Detalle de la Devolución");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel20))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(BTNAgregarDD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNModificaDD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNEliminaDD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNAgregarDD)
                    .addComponent(BTNModificaDD)
                    .addComponent(BTNEliminaDD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TabClientes.addTab("Detalle  Devolución", jPanel2);

        TablaProdDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(TablaProdDV);

        TablaDetalleV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(TablaDetalleV);

        jLabel23.setText("Productos");

        jLabel24.setText("Detalle de Ventas");

        BTNAgregarDV.setText("Agregar");

        BTNModificarDV.setText("Modificar");

        BTNEliminaDV.setText("Eliminar");

        jLabel25.setText("Cantidad");

        jLabel26.setText("Producto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(BTNAgregarDV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTNModificarDV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTNEliminaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNAgregarDV)
                    .addComponent(BTNModificarDV)
                    .addComponent(BTNEliminaDV))
                .addGap(8, 8, 8)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TabClientes.addTab("Detalle de Venta", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabClientes)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*PARA SABER QUE TABLA ES LA QUE SE VA A ACTUALIZAR  DEPENDIENDO LA VENTANA*/
    private void TabClientesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabClientesStateChanged
        switch(TabClientes.getSelectedIndex()){
            case 0://Cliente
            ConectaDB();
            ActualizaTabla(0);
            break;
            case 1://Vendedor
            ConectaDB();
            ActualizaTabla(1);
            break;
            case 2://Productor
            ConectaDB();
            ActualizaTabla(2);
            break;
            case 3://Venta
            ConectaDB();
            ActualizaTabla(3);
            break;
            case 4://Detalle de Venta
            ConectaDB();
            ActualizaTabla(4);
            break;
            case 5://Devolucion
            ConectaDB();
            ActualizaTabla(5);
            break;
            case 6://Detalle Devolucion
            ConectaDB();
            ActualizaTabla(6);
            break;
        }
    }//GEN-LAST:event_TabClientesStateChanged

    /*BOTON ELIMINA UN VENDEDOR*/
    private void BtnAgregarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarVendedorActionPerformed
        Qry = "INSERT INTO Almacen.Vendedor(Nombre,Domicilio,Email,Telefono,FechaNac)"
        + "VALUES(?,?,?,?,?)";
        String Aux = TextTelefonoV.getText();
        int day = diaV.getValue();
        int month = mesV.getMonth();
        int year = añoV.getYear();
        String fecha = String.valueOf(year + "/" + month + "/" + day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        try{
            Date date = formatter.parse(fecha);
            pt = conexion.prepareCall(Qry);
            pt.setString(1, TextNombreV.getText());
            pt.setString(2, TextDomicilioV.getText());
            pt.setString(3, TextEmailV.getText());
            pt.setInt(4,Integer.parseInt(Aux));
            pt.setDate(5,new java.sql.Date(date.getTime()));//FECHA
            int registro = pt.executeUpdate();
            ActualizaTabla(1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No se Ingreso " + e.getMessage());
        }
    }//GEN-LAST:event_BtnAgregarVendedorActionPerformed

    /*BOTON ELIMINA UN CLIENTE*/
    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        Qry = "DELETE FROM Transaccion.Cliente WHERE IdCliente = ?";

        try{
            pt = conexion.prepareCall(Qry);
            pt.setInt(1, Integer.parseInt(TablaClientes.getValueAt(row,0).toString()));
            int registro = pt.executeUpdate();
            if(registro > 0)
            {
                ActualizaTabla(0);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No se Elimino " + e.getMessage());
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    /*BOTON MODIFICA UN CLIENTE*/
    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
        Qry = "UPDATE Transaccion.Cliente SET "
        + "Nombre = ? ,"
        + "Domicilio = ? ,"
        + "Email = ? ,"
        + "Telefono = ? ,"
        + "FechaNac = ? "
        + "Where IdCliente = ?";
        String Aux = TextTelefono.getText();
        int day = diaC.getValue();
        int month = mesC.getMonth();
        int year = añoC.getYear();
        String fecha = String.valueOf(year + "/" + month + "/" + day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        try{
            Date date = formatter.parse(fecha);
            pt = conexion.prepareCall(Qry);
            pt.setString(1, TextNombre.getText());
            pt.setString(2, TextDomicilio.getText());
            pt.setString(3, TextEmail.getText());
            pt.setInt(4,Integer.parseInt(Aux));
            pt.setDate(5,new java.sql.Date(date.getTime()));//FECHA
            pt.setInt(6, id);
            int registro = pt.executeUpdate();
            if(registro > 0)
            {
                ActualizaTabla(0);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No se Modifico " + e.getMessage());
        }
    }//GEN-LAST:event_BtnModificarActionPerformed

    /*BOTON AGREGAR UN NUEVO CLIENTE*/
    private void BtnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarClienteActionPerformed
        Qry = "INSERT INTO Transaccion.Cliente(Nombre,Domicilio,Email,Telefono,FechaNac)"
        + "VALUES(?,?,?,?,?)";
        String Aux = TextTelefono.getText();
        int day = diaC.getValue();
        int month = mesC.getMonth();
        int year = añoC.getYear();
        String fecha = String.valueOf(year + "/" + month + "/" + day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        try{
            Date date = formatter.parse(fecha);
            pt = conexion.prepareCall(Qry);
            pt.setString(1, TextNombre.getText());
            pt.setString(2, TextDomicilio.getText());
            pt.setString(3, TextEmail.getText());
            pt.setInt(4,Integer.parseInt(Aux));
            pt.setDate(5,new java.sql.Date(date.getTime()));//FECHA
            int registro = pt.executeUpdate();
            ActualizaTabla(0);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No se Ingreso " + e.getMessage());
        }
    }//GEN-LAST:event_BtnAgregarClienteActionPerformed

    /*AL DARLE CLICK A UNA COSA EN LA TABLA DE CLIENTES*/
    private void TablaClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaClientesMousePressed
        point = evt.getPoint();
        row = TablaClientes.rowAtPoint(point);
        TablaClientes.getModel();
        id = Integer.parseInt(TablaClientes.getValueAt(row,0).toString());
        String NombreCl = TablaClientes.getValueAt(row,1).toString();
        String DomicilioCl = TablaClientes.getValueAt(row,2).toString();
        String EmailCl = TablaClientes.getValueAt(row,3).toString();
        String Telefono = TablaClientes.getValueAt(row,4).toString();
        String Fecha = TablaClientes.getValueAt(row,5).toString();

        TextNombre.setText(NombreCl);
        TextDomicilio.setText(DomicilioCl);
        TextEmail.setText(EmailCl);
        TextTelefono.setText(Telefono);
        String [] fechaD = Fecha.split("-");
        diaC.setValue(Integer.parseInt(fechaD[2]));
        mesC.setMonth(Integer.parseInt(fechaD[1]));
        añoC.setYear(Integer.parseInt(fechaD[0]));
    }//GEN-LAST:event_TablaClientesMousePressed

    /*BOTON MODIFICA UN VENDEDOR*/
    private void BtnModificaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificaVendedorActionPerformed
        Qry = "UPDATE Almacen.Vendedor SET "
        + "Nombre = ? ,"
        + "Domicilio = ? ,"
        + "Email = ? ,"
        + "Telefono = ? ,"
        + "FechaNac = ? "
        + "Where IdVendedor = ?";
        String Aux = TextTelefonoV.getText();
        int day = diaV.getValue();
        int month = mesV.getMonth();
        int year = añoV.getYear();
        String fecha = String.valueOf(year + "/" + month + "/" + day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        try{
            Date date = formatter.parse(fecha);
            pt = conexion.prepareCall(Qry);
            pt.setString(1, TextNombreV.getText());
            pt.setString(2, TextDomicilioV.getText());
            pt.setString(3, TextEmailV.getText());
            pt.setInt(4,Integer.parseInt(Aux));
            pt.setDate(5,new java.sql.Date(date.getTime()));//FECHA
            pt.setInt(6, id);
            int registro = pt.executeUpdate();
            if(registro > 0)
            {
                ActualizaTabla(1);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No se Modifico " + e.getMessage());
        }
    }//GEN-LAST:event_BtnModificaVendedorActionPerformed

    /*BOTON ELIMINA UN VENDEDOR*/
    private void BtnEliminarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarVendedorActionPerformed
       Qry = "DELETE FROM Almacen.Vendedor WHERE IdVendedor = ?";

        try{
            pt = conexion.prepareCall(Qry);
            pt.setInt(1, Integer.parseInt(TablaVendedor.getValueAt(row,0).toString()));
            int registro = pt.executeUpdate();
            if(registro > 0)
            {
                ActualizaTabla(1);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No se Elimino " + e.getMessage());
        }
    }//GEN-LAST:event_BtnEliminarVendedorActionPerformed

    /*AL DARLE CLICK A UNA COSA EN LA TABLA DE VENDEDOR*/
    private void TablaVendedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVendedorMousePressed
           point = evt.getPoint();
        row = TablaVendedor.rowAtPoint(point);
        TablaVendedor.getModel();
        id = Integer.parseInt(TablaVendedor.getValueAt(row,0).toString());
        String NombreCl = TablaVendedor.getValueAt(row,1).toString();
        String DomicilioCl = TablaVendedor.getValueAt(row,2).toString();
        String EmailCl = TablaVendedor.getValueAt(row,3).toString();
        String Telefono = TablaVendedor.getValueAt(row,4).toString();
        String Fecha = TablaVendedor.getValueAt(row,5).toString();

        TextNombreV.setText(NombreCl);
        TextDomicilioV.setText(DomicilioCl);
        TextEmailV.setText(EmailCl);
        TextTelefonoV.setText(Telefono);
        String [] fechaD = Fecha.split("-");
        diaV.setValue(Integer.parseInt(fechaD[2]));
        mesV.setMonth(Integer.parseInt(fechaD[1]));
        añoV.setYear(Integer.parseInt(fechaD[0]));
    }//GEN-LAST:event_TablaVendedorMousePressed

   
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal("","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAgregarDD;
    private javax.swing.JButton BTNAgregarDV;
    private javax.swing.JButton BTNEliminaDD;
    private javax.swing.JButton BTNEliminaDV;
    private javax.swing.JButton BTNModificaDD;
    private javax.swing.JButton BTNModificarDV;
    private javax.swing.JButton BtnAgregarCliente;
    private javax.swing.JButton BtnAgregarVendedor;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnEliminarVendedor;
    private javax.swing.JButton BtnModificaVendedor;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JComboBox<String> ComboTamaño;
    private javax.swing.JTabbedPane TabClientes;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTable TablaDetalleV;
    private javax.swing.JTable TablaProdDV;
    private javax.swing.JTable TablaProdDetalleD;
    private javax.swing.JTable TablaProdDetalleD1;
    private javax.swing.JTable TablaVendedor;
    private javax.swing.JTextField TextDomicilio;
    private javax.swing.JTextField TextDomicilioV;
    private javax.swing.JTextField TextEmail;
    private javax.swing.JTextField TextEmailV;
    private javax.swing.JTextField TextIdTP;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JTextField TextNombreP;
    private javax.swing.JTextField TextNombreV;
    private javax.swing.JTextField TextPrecio;
    private javax.swing.JTextField TextStock;
    private javax.swing.JTextField TextTelefono;
    private javax.swing.JTextField TextTelefonoV;
    public static javax.swing.JTextField Usuario;
    public static javax.swing.JTextField Usuario1;
    public static javax.swing.JTextField Usuario2;
    private com.toedter.calendar.JYearChooser añoC;
    private com.toedter.calendar.JYearChooser añoV;
    private com.toedter.components.JSpinField diaC;
    private com.toedter.components.JSpinField diaV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JMonthChooser mesC;
    private com.toedter.calendar.JMonthChooser mesV;
    // End of variables declaration//GEN-END:variables
}
