
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
import java.text.ParseException;
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
    private String Qry, Qry2;
    private DefaultTableModel modelo;
    private int id;
   
    private Connection conexion = null; 
    private PreparedStatement pt, pt2;
    public String usuario;
    private Point point;
    private int row, AT, idVenta, idDevolucion, idTipoProd, idProd;
    private String tipousuario, claveu, idf;
    private boolean seleccionado;
    /**
     * Creates new form Principal
     */
    public Principal(String nombre,String clave) {
        initComponents();
        tipousuario=nombre;
        claveu=clave;
        ConectaDB();
        ActualizaTabla(0, TablaClientes);
        seleccionado = false;
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
        catch(SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error en la conexión Principal");
        }
    }
    
    /*FUNCION CARGA LAS TABLAS EN CADA PAGINA*/
    public void ActualizaTabla(int AT, JTable tabla){
         switch(AT){
            case 0:
                if("empleado".equals(tipousuario))
                {
                    BtnAgregarCliente.setVisible(false);
                    BtnModificar.setVisible(false);
                    BtnEliminar.setVisible(false);
                }
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
                    tabla.setModel(modelo);
                }
                catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
            break;
            case 1:
                if("empleado".equals(tipousuario))
                {
                    BtnAgregarVendedor.setVisible(false);
                    BtnModificaVendedor.setVisible(false);
                    BtnEliminarVendedor.setVisible(false);
                }
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
                    tabla.setModel(modelo);
                }
                catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
            break;
            case 2:
                if("gerente".equals(tipousuario))
                {
                    BTNAgregarTipoProd.setVisible(false);
                    BTNModificarTipoProd.setVisible(false);
                    BTNEliminarTipoProd.setVisible(false);
                    BTNAgregarProd.setVisible(false);
                    BTNModificaProd.setVisible(false);
                    BTNEliminaProd.setVisible(false);
                }
                else if("empleado".equals(tipousuario))
                {
                    BTNAgregarTipoProd.setVisible(false);
                    BTNModificarTipoProd.setVisible(false);
                    BTNEliminarTipoProd.setVisible(false);
                    BTNAgregarProd.setVisible(false);
                    BTNModificaProd.setVisible(false);
                    BTNEliminaProd.setVisible(false);
                }
                modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("Nombre");
                modelo.addColumn("Descripción");

                try{
                    Qry = "SELECT * FROM Almacen.TipoProducto";
                    st = conexion.createStatement();
                    rs = st.executeQuery(Qry);
                    String aux[] = new String[3];
                    while(rs.next()){
                        aux[0] = rs.getString(1);
                        aux[1] = rs.getString(2);                
                        aux[2] = rs.getString(3);
                        modelo.addRow(aux);
                    }
                    if(tabla == null){
                        TablaTiposProductos.setModel(modelo);
                    }
                    else{
                        tabla.setModel(modelo);
                    }
                }
                catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
            break;
            case 3:
                modelo = new DefaultTableModel();
                modelo.addColumn("IdVenta");
                modelo.addColumn("IdCliente");
                modelo.addColumn("IdVendedor");
                modelo.addColumn("Fecha");
                modelo.addColumn("Total");
                try{
                    Qry = "SELECT * FROM Transaccion.Venta";
                    st = conexion.createStatement();
                    rs = st.executeQuery(Qry);
                    String aux[] = new String[7];
                    while(rs.next()){
                        aux[0] = rs.getString(1);
                        aux[1] = rs.getString(2);                
                        aux[2] = rs.getString(3);
                        aux[3] = rs.getString(4);
                        aux[4] = rs.getString(5);
                        modelo.addRow(aux);
                    }
                    if(tabla == null){
                        jTableVentas.setModel(modelo);
                    }
                    else{
                        tabla.setModel(modelo);
                    }
                }
                catch(SQLException e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
            break;
            case 4:
                modelo = new DefaultTableModel();
                modelo.addColumn("IdDevolucion");
                modelo.addColumn("IdVenta");
                modelo.addColumn("Motivo");
                modelo.addColumn("Fecha");
                modelo.addColumn("Total");
                try{
                    Qry = "SELECT * FROM Almacen.Devolucion";
                    st = conexion.createStatement();
                    rs = st.executeQuery(Qry);
                    String aux[] = new String[7];
                    while(rs.next()){
                        aux[0] = rs.getString(1);
                        aux[1] = rs.getString(2);                
                        aux[2] = rs.getString(3);
                        aux[3] = rs.getString(4);
                        aux[4] = rs.getString(5);
                        modelo.addRow(aux);
                    }
                    if(tabla == null){
                        jTableDevoluciones.setModel(modelo);
                    }
                    else{
                        tabla.setModel(modelo);
                    }
                }
                catch(SQLException e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al Conectar con tabla en Actualizacion");
                }
                break;
            case 5:
                modelo = new DefaultTableModel();
                modelo.addColumn("IdProducto");
                modelo.addColumn("IdTipoProducto");
                modelo.addColumn("Precio");
                modelo.addColumn("Stock");
                modelo.addColumn("Tamaño");
                try{
                    Qry = "SELECT * FROM Almacen.Producto";
                    st = conexion.createStatement();
                    rs = st.executeQuery(Qry);
                    String aux[] = new String[5];
                    while(rs.next()){
                        aux[0] = rs.getString(1);
                        aux[1] = rs.getString(2);                
                        aux[2] = rs.getString(3);
                        aux[3] = rs.getString(4);
                        aux[4] = rs.getString(5);
                        modelo.addRow(aux);
                    }
                    if(tabla == null){
                        TablaProducto.setModel(modelo);
                    }
                    else{
                        tabla.setModel(modelo);
                    }
                }
                catch(SQLException e){
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
        TablaProducto = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaTiposProductos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TextNombreP = new javax.swing.JTextField();
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
        BTNAgregarTipoProd = new javax.swing.JButton();
        BTNModificarTipoProd = new javax.swing.JButton();
        BTNEliminarTipoProd = new javax.swing.JButton();
        BTNAgregarProd = new javax.swing.JButton();
        BTNModificaProd = new javax.swing.JButton();
        BTNEliminaProd = new javax.swing.JButton();
        Descripcion = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableVer = new javax.swing.JTable();
        jButtonVerVendedores = new javax.swing.JButton();
        jButtonVerClientes = new javax.swing.JButton();
        jTextFieldVendedor = new javax.swing.JTextField();
        jTextFieldCliente = new javax.swing.JTextField();
        jLabelVendedor = new javax.swing.JLabel();
        jLabelVendedor1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jDateChooserFechaVenta = new com.toedter.calendar.JDateChooser();
        jLabelFecha = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButtonVerVentas = new javax.swing.JButton();
        jLabelVendedor2 = new javax.swing.JLabel();
        jTextFieldVenta = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableVerVentas = new javax.swing.JTable();
        jLabelFecha1 = new javax.swing.JLabel();
        jDateChooserFechaDevolucion = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextAreaMotivo = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTableDevoluciones = new javax.swing.JTable();
        jButtonAgregarDevolucion = new javax.swing.JButton();
        jButtonModificarDevolucion = new javax.swing.JButton();
        jButtonEliminarDevolucion = new javax.swing.JButton();
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
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(diaC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mesC, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(añoC, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(TextEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextDomicilio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
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
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnModificar)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
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
                .addGap(53, 53, 53)
                .addComponent(Usuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(diaV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(añoV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextTelefonoV)
                    .addComponent(TextEmailV)
                    .addComponent(TextDomicilioV)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(TextNombreV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAgregarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(BtnModificaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(BtnEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAgregarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnModificaVendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Usuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        TabClientes.addTab("Vendedores", jPanel4);

        TablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaProducto);

        TablaTiposProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaTiposProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaTiposProductosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaTiposProductos);

        jLabel11.setText("Tipos de Productos");

        jLabel12.setText("Productos");

        jLabel13.setText("Nombre");

        jLabel14.setText("Descripción");

        ComboTamaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual", "Matrimonial", "Queen size" }));

        jLabel15.setText("Tipo Producto");

        jLabel16.setText("Precio");

        jLabel17.setText("Stock");

        jLabel18.setText("Tamaño");

        BTNAgregarTipoProd.setText("Agregar");
        BTNAgregarTipoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAgregarTipoProdActionPerformed(evt);
            }
        });

        BTNModificarTipoProd.setText("Modificar");
        BTNModificarTipoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNModificarTipoProdActionPerformed(evt);
            }
        });

        BTNEliminarTipoProd.setText("Eliminar");
        BTNEliminarTipoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEliminarTipoProdActionPerformed(evt);
            }
        });

        BTNAgregarProd.setText("Agregar");
        BTNAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAgregarProdActionPerformed(evt);
            }
        });

        BTNModificaProd.setText("Modificar");
        BTNModificaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNModificaProdActionPerformed(evt);
            }
        });

        BTNEliminaProd.setText("Eliminar");
        BTNEliminaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEliminaProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(18, 18, 18))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(55, 55, 55)))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addGap(58, 58, 58))))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextIdTP, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                    .addComponent(TextPrecio)
                                    .addComponent(TextStock)
                                    .addComponent(ComboTamaño, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(BTNAgregarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTNModificaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTNEliminaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(29, 29, 29)
                                .addComponent(TextNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(BTNAgregarTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(BTNModificarTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(BTNEliminarTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTNAgregarTipoProd)
                            .addComponent(BTNModificarTipoProd)
                            .addComponent(BTNEliminarTipoProd)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTNAgregarProd)
                            .addComponent(BTNModificaProd)
                            .addComponent(BTNEliminaProd))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        TabClientes.addTab("Productos", jPanel5);

        jTableVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVerMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTableVer);

        jButtonVerVendedores.setText("Ver Vendedores");
        jButtonVerVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVerVendedoresMouseClicked(evt);
            }
        });

        jButtonVerClientes.setText("Ver Clientes");
        jButtonVerClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVerClientesMouseClicked(evt);
            }
        });

        jLabelVendedor.setText("ID Vendedor:");

        jLabelVendedor1.setText("ID Cliente:");

        jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVentasMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTableVentas);

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jLabelFecha.setText("Fecha:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabelVendedor)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jButtonVerVendedores)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(jButtonVerClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabelVendedor1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabelFecha)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooserFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonVerVendedores)
                            .addComponent(jButtonVerClientes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelVendedor)
                            .addComponent(jLabelVendedor1)
                            .addComponent(jTextFieldVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFecha)))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(115, 115, 115))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );

        TabClientes.addTab("Ventas", jPanel6);

        jButtonVerVentas.setText("Ver Ventas");
        jButtonVerVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVerVentasMouseClicked(evt);
            }
        });

        jLabelVendedor2.setText("ID Venta:");

        jTableVerVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableVerVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVerVentasMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTableVerVentas);

        jLabelFecha1.setText("Fecha:");

        jLabel27.setText("Motivo:");

        jTextAreaMotivo.setColumns(20);
        jTextAreaMotivo.setRows(5);
        jScrollPane13.setViewportView(jTextAreaMotivo);

        jTableDevoluciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableDevoluciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDevolucionesMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTableDevoluciones);

        jButtonAgregarDevolucion.setText("Agregar");
        jButtonAgregarDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarDevolucionActionPerformed(evt);
            }
        });

        jButtonModificarDevolucion.setText("Modificar");
        jButtonModificarDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarDevolucionActionPerformed(evt);
            }
        });

        jButtonEliminarDevolucion.setText("Eliminar");
        jButtonEliminarDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarDevolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAgregarDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonModificarDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminarDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jTextFieldVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooserFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabelVendedor2)
                                        .addGap(86, 86, 86)
                                        .addComponent(jLabelFecha1))
                                    .addComponent(jButtonVerVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButtonVerVentas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelVendedor2)
                                    .addComponent(jLabelFecha1))
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButtonAgregarDevolucion)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificarDevolucion)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarDevolucion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(310, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            ActualizaTabla(0, TablaClientes);
            break;
            case 1://Vendedor
            ConectaDB();
            ActualizaTabla(1, TablaVendedor);
            break;
            case 2://Productor
            ConectaDB();
            ActualizaTabla(2, null);
            break;
            case 3://Venta
            ConectaDB();
            ActualizaTabla(3, null);
            break;
            /*case 4://Detalle de Venta
            ConectaDB();
            ActualizaTabla(4);
            break;*/
            case 4://Devolucion
            ConectaDB();
            ActualizaTabla(4, null);
            break;
            /*case 6://Detalle Devolucion
            ConectaDB();
            ActualizaTabla(6);
            break;*/
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
            ActualizaTabla(1, TablaVendedor);
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
                ActualizaTabla(0, TablaClientes);
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
                ActualizaTabla(0, TablaClientes);
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
            ActualizaTabla(0, TablaClientes);
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
                ActualizaTabla(1, TablaVendedor);
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
                ActualizaTabla(1, TablaVendedor);
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

    private void jButtonVerVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVerVendedoresMouseClicked
        // TODO add your handling code here:
        seleccionado = false;
        ActualizaTabla(1, jTableVer);
    }//GEN-LAST:event_jButtonVerVendedoresMouseClicked

    private void jButtonVerClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVerClientesMouseClicked
        // TODO add your handling code here:
        seleccionado = true;
        ActualizaTabla(0, jTableVer);
    }//GEN-LAST:event_jButtonVerClientesMouseClicked

    private void jTableVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVerMouseClicked
        // TODO add your handling code here:
        int fila = jTableVer.getSelectedRow();
        if(fila != -1){
            int columns = jTableVer.getColumnCount();
            ArrayList<String> valores = new ArrayList<>();
            for(int i = 0; i < columns - 1; i++){
                valores.add(jTableVer.getValueAt(fila, i).toString());
            }
            if(seleccionado){
                jTextFieldCliente.setText(valores.get(0));
            }
            else{
                jTextFieldVendedor.setText(valores.get(0));
            }
        }
    }//GEN-LAST:event_jTableVerMouseClicked

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        // TODO add your handling code here:
        if(!"".equals(jTextFieldCliente.getText()) && !"".equals(jTextFieldVendedor.getText()) && !"".equals(jDateChooserFechaVenta.getDate().toString())){
            Qry = "INSERT INTO Transaccion.Venta(IdCliente,IdVendedor,Fecha)"
                + "VALUES(?,?,?)";
            Date date = new Date();
            date = jDateChooserFechaVenta.getDate();

            try{
                pt = conexion.prepareCall(Qry); 
                pt.setInt(1,  Integer.parseInt(jTextFieldCliente.getText()));
                pt.setInt(2, Integer.parseInt(jTextFieldVendedor.getText()));
                pt.setDate(3,new java.sql.Date(date.getTime()));//FECHA
                int registro = pt.executeUpdate(); 
                ActualizaTabla(3, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }   
        }
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jTableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVentasMouseClicked
        // TODO add your handling code here:
        int fila = jTableVentas.getSelectedRow();
        if(fila != -1){
            int columns = jTableVentas.getColumnCount();
            ArrayList<String> valores = new ArrayList<>();
            for(int i = 0; i < columns - 1; i++){
                valores.add(jTableVentas.getValueAt(fila, i).toString());
            }
            jTextFieldCliente.setText(valores.get(2));
            jTextFieldVendedor.setText(valores.get(1));
            idVenta = Integer.parseInt(valores.get(0));
            Date fecha = getFecha(valores.get(3));
            if(fecha != null){
                jDateChooserFechaVenta.setDate(fecha);
            }
        }else{
            idVenta = -1;
        }
    }//GEN-LAST:event_jTableVentasMouseClicked

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        if(idVenta != -1){
            Qry = "UPDATE Transaccion.Venta SET"
                    + " IdCliente = ? ,"
                    + " IdVendedor = ? ,"
                    + " Fecha = ? "
                    + "WHERE IdVenta = ?;";
             Date date = new Date();
            date = jDateChooserFechaVenta.getDate();
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1,Integer.parseInt(jTextFieldCliente.getText()));
                pt.setInt(2,Integer.parseInt(jTextFieldCliente.getText()));
                pt.setDate(3,new java.sql.Date(date.getTime()));//FECHA
                pt.setInt(4, idVenta);
                int registro = pt.executeUpdate();
                ActualizaTabla(3, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        if(idVenta != -1){
            Qry = "DELETE FROM Transaccion.Venta WHERE IdVenta = ?";
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1, idVenta);
                int registro = pt.executeUpdate();
                if(registro > 0)
                ActualizaTabla(3, null);
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "No se Elimino " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonVerVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVerVentasMouseClicked
        // TODO add your handling code here:
        ActualizaTabla(3, jTableVerVentas);
    }//GEN-LAST:event_jButtonVerVentasMouseClicked

    private void jTableVerVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVerVentasMouseClicked
        // TODO add your handling code here:
        int fila = jTableVerVentas.getSelectedRow();
        if(fila != -1){
            int columns = jTableVerVentas.getColumnCount();
            ArrayList<String> valores = new ArrayList<>();
            for(int i = 0; i < columns - 1; i++){
                valores.add(jTableVerVentas.getValueAt(fila, i).toString());
            }
            jTextFieldVenta.setText(valores.get(0));
        }
        
    }//GEN-LAST:event_jTableVerVentasMouseClicked

    private void jButtonAgregarDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarDevolucionActionPerformed
        // TODO add your handling code here:
        if(jTextFieldVenta.getText() != "" && jTextAreaMotivo.getText() != ""){
            Qry = "INSERT INTO Almacen.Devolucion(IdVenta,Motivo,Fecha)"
                + " VALUES(?,?,?)";
            Date date = new Date();
            date = jDateChooserFechaVenta.getDate();

            try{
                pt = conexion.prepareCall(Qry); 
                pt.setInt(1,  Integer.parseInt(jTextFieldVenta.getText()));
                pt.setString(2, jTextAreaMotivo.getText());
                pt.setDate(3,new java.sql.Date(date.getTime()));//FECHA
                int registro = pt.executeUpdate(); 
                ActualizaTabla(4, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonAgregarDevolucionActionPerformed

    private void jTableDevolucionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDevolucionesMouseClicked
        // TODO add your handling code here:
        int fila = jTableDevoluciones.getSelectedRow();
        if(fila != -1){
            int columns = jTableDevoluciones.getColumnCount();
            ArrayList<String> valores = new ArrayList<>();
            for(int i = 0; i < columns - 1; i++){
                valores.add(jTableDevoluciones.getValueAt(fila, i).toString());
            }
            idDevolucion = Integer.parseInt(valores.get(0));
            jTextFieldVenta.setText(valores.get(1));
            jTextAreaMotivo.setText(valores.get(2));
            Date fecha = getFecha(valores.get(3));
            if(fecha != null){
                jDateChooserFechaDevolucion.setDate(fecha);
            }
        }
        else{
            idDevolucion = -1;
        }
    }//GEN-LAST:event_jTableDevolucionesMouseClicked

    private Date getFecha(String fecha){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatoDelTexto.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }
    private void jButtonModificarDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarDevolucionActionPerformed
        if(jTextFieldVenta.getText() != "" && jTextAreaMotivo.getText() != "" && idDevolucion != -1){
            Qry = "UPDATE Almacen.Devolucion SET"
                    + " IdVenta = ? ,"
                    + " Motivo = ? ,"
                    + " Fecha = ? "
                    + "WHERE IdDevolucion = ?;";
            Date date = new Date();
            date = jDateChooserFechaVenta.getDate();
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1,Integer.parseInt(jTextFieldVenta.getText()));
                pt.setString(2, jTextAreaMotivo.getText());
                pt.setDate(3,new java.sql.Date(date.getTime()));//FECHA
                pt.setInt(4, idDevolucion);
                int registro = pt.executeUpdate();
                ActualizaTabla(4, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButtonModificarDevolucionActionPerformed

    private void jButtonEliminarDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarDevolucionActionPerformed
        if(jTextFieldVenta.getText() != "" && jTextAreaMotivo.getText() != "" && idDevolucion != -1){
            Qry = "DELETE FROM Almacen.Devolucion WHERE IdDevolucion = ?";
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1, idDevolucion);
                int registro = pt.executeUpdate();
                ActualizaTabla(4, null);   
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "No se Elimino " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonEliminarDevolucionActionPerformed

    private void BTNAgregarTipoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAgregarTipoProdActionPerformed
        if(TextNombreP.getText() != "" && Descripcion.getText() != "" && idTipoProd != -1){
            Qry = "INSERT INTO Almacen.TipoProducto(Nombre,Descripcion) VALUES(?,?)";

            try{
                pt = conexion.prepareCall(Qry); 
                pt.setString(1, TextNombreP.getText());
                pt.setString(2, Descripcion.getText());//FECHA
                int registro = pt.executeUpdate();
                ActualizaTabla(2, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "A completar los dos campos");
        }
    }//GEN-LAST:event_BTNAgregarTipoProdActionPerformed

    private void BTNModificarTipoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModificarTipoProdActionPerformed
        if(TextNombreP.getText() != "" && Descripcion.getText() != "" && idTipoProd != -1){
            Qry = "UPDATE Almacen.TipoProducto SET"
                    + " Nombre = ? ,"
                    + " Descripcion "
                    + "WHERE IdTipoProducto = ?;";
            try{
                pt = conexion.prepareCall(Qry);
                pt.setString(1, TextNombreP.getText());
                pt.setString(2, Descripcion.getText());
                pt.setInt(3, idTipoProd);
                int registro = pt.executeUpdate();
                ActualizaTabla(2, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Rellana todos los campos");
        }
    }//GEN-LAST:event_BTNModificarTipoProdActionPerformed

    private void BTNEliminarTipoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEliminarTipoProdActionPerformed
        Qry = "DELETE FROM Almacen.TipoProducto WHERE IdTipoProducto = ?";
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1, idTipoProd);
                int registro = pt.executeUpdate();
                ActualizaTabla(2, null);   
            }
            catch(Exception e)
            {
                try{
                Qry2 = "SELECT COUNT(*) FROM Almacen.Producto WHERE IdTipoProducto = ?";
                pt2 = conexion.prepareCall(Qry2);
                pt2.setInt(1, idTipoProd);
                int registro = pt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Elimina primero los productos");
                }
                catch(Exception q) {
                    
                }
            }
    }//GEN-LAST:event_BTNEliminarTipoProdActionPerformed

    private void BTNAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAgregarProdActionPerformed
        if(TextIdTP.getText() != "" && TextPrecio.getText() != "" && TextStock.getText() != "" && idProd != -1){
            Qry = "INSERT INTO Almacen.Producto(IdTipoProducto, Stock, Tamaño, Precio) VALUES(?,?,?,?)";

            try{
                pt = conexion.prepareCall(Qry); 
                pt.setInt(1, Integer.parseInt(TextIdTP.getText()));
                pt.setInt(2, Integer.parseInt(TextPrecio.getText()));
                pt.setInt(3, Integer.parseInt(TextStock.getText()));
                pt.setString(4, ComboTamaño.getSelectedItem().toString());
                int registro = pt.executeUpdate(); 
                ActualizaTabla(5, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Acompletar los dos campos");
        }
    }//GEN-LAST:event_BTNAgregarProdActionPerformed

    private void TablaTiposProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaTiposProductosMouseClicked
        int fila = TablaTiposProductos.getSelectedRow();
        if(fila != -1){
            int columns = TablaTiposProductos.getColumnCount();
            ArrayList<String> valores = new ArrayList<>();
            for(int i = 0; i < columns; i++){
                valores.add(TablaTiposProductos.getValueAt(fila, i).toString());
            }
            idTipoProd = Integer.parseInt(valores.get(0));
            TextIdTP.setText(valores.get(0));
            TextNombreP.setText(valores.get(1));
            Descripcion.setText(valores.get(2));
            ActualizaTabla(5, null);
        }
        else{
            idTipoProd = -1;
        }
    }//GEN-LAST:event_TablaTiposProductosMouseClicked

    private void BTNModificaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModificaProdActionPerformed
        if(TextIdTP.getText() != "" && TextPrecio.getText() != "" && TextStock.getText() != "" && idProd != -1){
            Qry = "UPDATE Almacen.Producto SET"
                    + " IdTipoProducto = ?"
                    + " Precio = ? ,"
                    + " Stock = ? "
                    + " Tamaño = ? "
                    + "WHERE IdProducto = ?;";
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1, Integer.parseInt(TextIdTP.getText()));
                pt.setInt(2, Integer.parseInt(TextPrecio.getText()));
                pt.setInt(3, Integer.parseInt(TextStock.getText()));
                pt.setString(4, ComboTamaño.getSelectedItem().toString());
                pt.setInt(5, idProd);
                int registro = pt.executeUpdate();
                ActualizaTabla(5, null);
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Rellana todos los campos");
        }
    }//GEN-LAST:event_BTNModificaProdActionPerformed

    private void BTNEliminaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEliminaProdActionPerformed
        Qry = "DELETE FROM Almacen.TipoProducto WHERE IdTipoProducto = ?";
            try{
                pt = conexion.prepareCall(Qry);
                pt.setInt(1, idTipoProd);
                int registro = pt.executeUpdate();
                ActualizaTabla(5, null);   
            }
            catch(Exception e)
            {
             
            }
    }//GEN-LAST:event_BTNEliminaProdActionPerformed

    private void TablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductoMouseClicked
        int fila = TablaProducto.getSelectedRow();
        if(fila != -1){
            int columns = TablaProducto.getColumnCount();
            ArrayList<String> valores = new ArrayList<>();
            for(int i = 0; i < columns - 1; i++){
                valores.add(TablaProducto.getValueAt(fila, i).toString());
            }
            idProd = Integer.parseInt(valores.get(0));
            TextIdTP.setText(valores.get(1));
            TextPrecio.setText(valores.get(2));
            TextStock.setText(valores.get(3).toString());
        }
        else{
            idProd = -1;
        }
    }//GEN-LAST:event_TablaProductoMouseClicked

   
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
    private javax.swing.JButton BTNAgregarProd;
    private javax.swing.JButton BTNAgregarTipoProd;
    private javax.swing.JButton BTNEliminaDD;
    private javax.swing.JButton BTNEliminaDV;
    private javax.swing.JButton BTNEliminaProd;
    private javax.swing.JButton BTNEliminarTipoProd;
    private javax.swing.JButton BTNModificaDD;
    private javax.swing.JButton BTNModificaProd;
    private javax.swing.JButton BTNModificarDV;
    private javax.swing.JButton BTNModificarTipoProd;
    private javax.swing.JButton BtnAgregarCliente;
    private javax.swing.JButton BtnAgregarVendedor;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnEliminarVendedor;
    private javax.swing.JButton BtnModificaVendedor;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JComboBox<String> ComboTamaño;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JTabbedPane TabClientes;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTable TablaDetalleV;
    private javax.swing.JTable TablaProdDV;
    private javax.swing.JTable TablaProdDetalleD;
    private javax.swing.JTable TablaProdDetalleD1;
    private javax.swing.JTable TablaProducto;
    private javax.swing.JTable TablaTiposProductos;
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
    public static javax.swing.JTextField Usuario1;
    public static javax.swing.JTextField Usuario2;
    private com.toedter.calendar.JYearChooser añoC;
    private com.toedter.calendar.JYearChooser añoV;
    private com.toedter.components.JSpinField diaC;
    private com.toedter.components.JSpinField diaV;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonAgregarDevolucion;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminarDevolucion;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonModificarDevolucion;
    private javax.swing.JButton jButtonVerClientes;
    private javax.swing.JButton jButtonVerVendedores;
    private javax.swing.JButton jButtonVerVentas;
    private com.toedter.calendar.JDateChooser jDateChooserFechaDevolucion;
    private com.toedter.calendar.JDateChooser jDateChooserFechaVenta;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFecha1;
    private javax.swing.JLabel jLabelVendedor;
    private javax.swing.JLabel jLabelVendedor1;
    private javax.swing.JLabel jLabelVendedor2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableDevoluciones;
    private javax.swing.JTable jTableVentas;
    private javax.swing.JTable jTableVer;
    private javax.swing.JTable jTableVerVentas;
    private javax.swing.JTextArea jTextAreaMotivo;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldVendedor;
    private javax.swing.JTextField jTextFieldVenta;
    private com.toedter.calendar.JMonthChooser mesC;
    private com.toedter.calendar.JMonthChooser mesV;
    // End of variables declaration//GEN-END:variables
}
