/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacturaForm.java
 *
 * Created on 27/07/2010, 12:23:19 PM
 */

package com.phesus.facturatron.presentation.mvc.view;

import com.phesus.facturatron.presentation.mvc.model.FacturaTableModel;
import com.alee.laf.button.WebButton;
import facturatron.Dominio.FormaDePago;
import facturatron.Dominio.MetodoDePagoEnum;
import facturatron.Dominio.UsoCFDIEnum;
import com.phesus.facturatron.persistence.dao.FacturaDao;
import facturatron.facturacion.TipoComprobante;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Octavio
 */
public class FacturaForm extends javax.swing.JPanel implements Observer {

    /**
     * @return the comboMetodoDePago
     */
    public javax.swing.JComboBox<MetodoDePagoEnum> getComboMetodoDePago() {
        return comboMetodoDePago;
    }

    /**
     * @param comboMetodoDePago the comboMetodoDePago to set
     */
    public void setComboMetodoDePago(javax.swing.JComboBox<MetodoDePagoEnum> comboMetodoDePago) {
        this.comboMetodoDePago = comboMetodoDePago;
    }

    /**
     * @return the comboUsoCFDI
     */
    public javax.swing.JComboBox<UsoCFDIEnum> getComboUsoCFDI() {
        return comboUsoCFDI;
    }

    /**
     * @param comboUsoCFDI the comboUsoCFDI to set
     */
    public void setComboUsoCFDI(javax.swing.JComboBox<UsoCFDIEnum> comboUsoCFDI) {
        this.comboUsoCFDI = comboUsoCFDI;
    }

    /**
     * @return the jComboBoxFormaPago
     */
    public javax.swing.JComboBox<FormaDePago> getjComboBoxFormaPago() {
        return jComboBoxFormaPago;
    }

    /**
     * @param jComboBoxFormaPago the jComboBoxFormaPago to set
     */
    public void setjComboBoxFormaPago(javax.swing.JComboBox<FormaDePago> jComboBoxFormaPago) {
        this.jComboBoxFormaPago = jComboBoxFormaPago;
    }

    private FacturaDao modelo;

    public JFrame getJFrameParent() {
        return (JFrame) JFrame.getFrames()[0];
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.getTxtSerie().setText(getModelo().getSerie());
        this.getTxtFolio().setText(String.format("%d", getModelo().getFolio()));
        this.getjComboBoxFormaPago().setSelectedItem(getModelo().getFormaDePago());
        this.getComboMetodoDePago().setSelectedItem(getModelo().getMetodoDePago());
        
        this.getTxtNombre().setText(String.valueOf(getModelo().getReceptor().getNombre()));
        this.getTxtRfc().setText(String.valueOf(getModelo().getReceptor().getRfc()));
        this.getTxtDireccion().setText(getModelo().getReceptor().getCalle() +
                " # " + getModelo().getReceptor().getNoExterior() +
                "\r\n" + getModelo().getReceptor().getColonia() +
                "\r\n" + getModelo().getReceptor().getMunicipio() +
                ", " + getModelo().getReceptor().getEstado() +
                "\r\nCP " + getModelo().getReceptor().getCodigoPostal());

        String subtotal = String.format("%,.2f", getModelo().getSubtotal());
        this.getTxtSubtotal().setText(subtotal);
        String iva  = String.format("%,.2f", getModelo().getIvaTrasladado());
        this.getTxtIva().setText(iva);
        String ieps = String.format("%,.2f", getModelo().getIEPSTrasladado());
        this.getTxtIEPS().setText(ieps);
        String descuento = String.format("%,.2f", getModelo().getDescuento());
        this.getTxtDescuento().setText(descuento);
        String total = String.format("%,.2f", getModelo().getTotal());
        this.getTxtTotal().setText(total);
    }
    
   

    /** Creates new form FacturaForm */
    public FacturaForm() {
        initComponents();
        tabConceptos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                tabConceptos.editCellAt(
                        tabConceptos.getSelectedRow(),
                        tabConceptos.getSelectedColumn()
                );
            }
        });
        txtDireccion.setEditable(false);
        tabConceptos.setSortable(false);  
        
    }
    
    public void setTableWidth() {                
        tabConceptos.getColumn(0).setMaxWidth(110);
        tabConceptos.getColumn(1).setMaxWidth(110);
        tabConceptos.getColumn(2).setMaxWidth(140);
        tabConceptos.getColumn(3).setPreferredWidth(400);
        tabConceptos.getColumn(4).setMaxWidth(110);
        tabConceptos.getColumn(5).setMaxWidth(110);
        tabConceptos.getColumn(6).setMaxWidth(110);
        tabConceptos.getColumn(7).setMaxWidth(110);
        tabConceptos.getColumn(8).setMaxWidth(110);
        tabConceptos.getColumn(9).setMaxWidth(110);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jXTitledPanel2 = new org.jdesktop.swingx.JXTitledPanel();
        jLabel9 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        comboTipoComprobante = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnBuscarCliente = new com.alee.laf.button.WebButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboUsoCFDI = new javax.swing.JComboBox<>();
        jComboBoxFormaPago = new javax.swing.JComboBox<>();
        comboMetodoDePago = new javax.swing.JComboBox<>();
        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabConceptos = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMotivoDescuento = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtIEPS = new javax.swing.JTextField();
        jXTitledPanel3 = new org.jdesktop.swingx.JXTitledPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBorrarPartida = new javax.swing.JButton();
        btnObservaciones = new org.jdesktop.swingx.JXButton();
        btnFacturaDia = new javax.swing.JButton();
        btnTicket = new javax.swing.JButton();
        btnFacturasRelacionadas = new javax.swing.JButton();
        btnVistaPrevia = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(640, 480));

        jXTitledPanel2.setTitle("Datos Fiscales");
        jXTitledPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Id Cliente:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("RFC:");
        jLabel3.setPreferredSize(new java.awt.Dimension(64, 14));

        txtRfc.setEditable(false);
        txtRfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRfcActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dirección:");
        jLabel2.setMaximumSize(new java.awt.Dimension(64, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(64, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(64, 14));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Razón Social:");

        txtNombre.setEditable(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Folio:");

        txtFolio.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Serie:");

        txtSerie.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tipo:");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(4);
        txtDireccion.setPreferredSize(new java.awt.Dimension(40, 80));
        jScrollPane2.setViewportView(txtDireccion);

        comboTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(TipoComprobante.values()));
        comboTipoComprobante.setSelectedItem(TipoComprobante.FACTURA);
        comboTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoComprobanteActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Método de pago:");

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Uso CFDI");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Forma:");

        comboUsoCFDI.setModel(new javax.swing.DefaultComboBoxModel(UsoCFDIEnum.values()));
        comboUsoCFDI.setSelectedItem(TipoComprobante.FACTURA);
        comboUsoCFDI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsoCFDIActionPerformed(evt);
            }
        });

        jComboBoxFormaPago.setModel(new javax.swing.DefaultComboBoxModel(FormaDePago.values()));

        comboMetodoDePago.setModel(new javax.swing.DefaultComboBoxModel(MetodoDePagoEnum.values()));
        comboMetodoDePago.setSelectedItem(MetodoDePagoEnum.PUE);
        comboMetodoDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMetodoDePagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXTitledPanel2Layout = new javax.swing.GroupLayout(jXTitledPanel2.getContentContainer());
        jXTitledPanel2.getContentContainer().setLayout(jXTitledPanel2Layout);
        jXTitledPanel2Layout.setHorizontalGroup(
            jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRfc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel2Layout.createSequentialGroup()
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jXTitledPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMetodoDePago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(7, 7, 7)))
                .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel2Layout.createSequentialGroup()
                                .addComponent(txtIdCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFolio)
                            .addComponent(txtSerie)
                            .addComponent(comboTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboUsoCFDI, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxFormaPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jXTitledPanel2Layout.setVerticalGroup(
            jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(comboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboMetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jXTitledPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboUsoCFDI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jXTitledPanel1.setTitle("Conceptos");
        jXTitledPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tabConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabConceptos);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Descuento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel12, gridBagConstraints);

        txtDescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtDescuento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDescuento.setMinimumSize(new java.awt.Dimension(14, 24));
        txtDescuento.setPreferredSize(new java.awt.Dimension(14, 24));
        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtDescuento, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel13, gridBagConstraints);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTotal.setMinimumSize(new java.awt.Dimension(14, 24));
        txtTotal.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtTotal, gridBagConstraints);

        txtIva.setEditable(false);
        txtIva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIva.setMinimumSize(new java.awt.Dimension(14, 24));
        txtIva.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtIva, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("IVA (16%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSubtotal.setMinimumSize(new java.awt.Dimension(14, 24));
        txtSubtotal.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtSubtotal, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Subtotal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Motivo Descuento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        txtMotivoDescuento.setMinimumSize(new java.awt.Dimension(14, 24));
        txtMotivoDescuento.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtMotivoDescuento, gridBagConstraints);

        jLabel15.setText("IEPS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel15, gridBagConstraints);

        txtIEPS.setEditable(false);
        txtIEPS.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIEPS.setMinimumSize(new java.awt.Dimension(14, 24));
        txtIEPS.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtIEPS, gridBagConstraints);

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jXTitledPanel3.setTitle("Acciones");
        jXTitledPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnBorrarPartida.setText("Borrar partida");
        btnBorrarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPartidaAction(evt);
            }
        });
        jPanel1.add(btnBorrarPartida, new java.awt.GridBagConstraints());

        btnObservaciones.setText("Observaciones");
        btnObservaciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservacionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnObservaciones, new java.awt.GridBagConstraints());

        btnFacturaDia.setText("Factura globalizada");
        jPanel1.add(btnFacturaDia, new java.awt.GridBagConstraints());

        btnTicket.setText("Añadir ticket");
        jPanel1.add(btnTicket, new java.awt.GridBagConstraints());

        btnFacturasRelacionadas.setText("Facturas Relacionadas");
        btnFacturasRelacionadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasRelacionadasActionPerformed(evt);
            }
        });
        jPanel1.add(btnFacturasRelacionadas, new java.awt.GridBagConstraints());

        btnVistaPrevia.setText("Vista previa");
        jPanel1.add(btnVistaPrevia, new java.awt.GridBagConstraints());

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar factura");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout jXTitledPanel3Layout = new javax.swing.GroupLayout(jXTitledPanel3.getContentContainer());
        jXTitledPanel3.getContentContainer().setLayout(jXTitledPanel3Layout);
        jXTitledPanel3Layout.setHorizontalGroup(
            jXTitledPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXTitledPanel3Layout.setVerticalGroup(
            jXTitledPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTitledPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTitledPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTitledPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jXTitledPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    

    private void txtRfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRfcActionPerformed

    private void comboTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoComprobanteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnObservacionesActionPerformed

    private void borrarPartidaAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPartidaAction
        if(tabConceptos.getSelectedRowCount() > 0 && tabConceptos.getRowCount() > 1) {
            Integer selected = tabConceptos.getSelectionModel().getMinSelectionIndex();
            ((FacturaTableModel)tabConceptos.getModel()).removeRow(selected);
        }
    }//GEN-LAST:event_borrarPartidaAction

    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void comboUsoCFDIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsoCFDIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboUsoCFDIActionPerformed

    private void comboMetodoDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMetodoDePagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMetodoDePagoActionPerformed

    private void btnFacturasRelacionadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasRelacionadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturasRelacionadasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarPartida;
    private com.alee.laf.button.WebButton btnBuscarCliente;
    private javax.swing.JButton btnFacturaDia;
    private javax.swing.JButton btnFacturasRelacionadas;
    private javax.swing.JButton btnGuardar;
    private org.jdesktop.swingx.JXButton btnObservaciones;
    private javax.swing.JButton btnTicket;
    private javax.swing.JButton btnVistaPrevia;
    private javax.swing.JComboBox<MetodoDePagoEnum> comboMetodoDePago;
    private javax.swing.JComboBox<TipoComprobante> comboTipoComprobante;
    private javax.swing.JComboBox<UsoCFDIEnum> comboUsoCFDI;
    private javax.swing.JComboBox<FormaDePago> jComboBoxFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel2;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel3;
    private org.jdesktop.swingx.JXTable tabConceptos;
    private javax.swing.JFormattedTextField txtDescuento;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtIEPS;
    private javax.swing.JFormattedTextField txtIdCliente;
    private javax.swing.JTextField txtIva;
    private javax.swing.JFormattedTextField txtMotivoDescuento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables




    /**
     * @return the guarda
     */
    public javax.swing.JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * @param guarda the guarda to set
     */
    public void setBtnGuardar(javax.swing.JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    /**
     * @return the iva
     */
    public javax.swing.JTable getTabConceptos() {
        return tabConceptos;
    }

    /**
     * @param tabConceptos the tabConceptos to set
     */
    public void setTabConceptos(javax.swing.JTable tabConceptos) {
        this.tabConceptos = (JXTable) tabConceptos;
        
    }

    /**
     * @return the txtDescuento
     */
    public javax.swing.JFormattedTextField getTxtDescuentoTasa16() {
        return getTxtDescuento();
    }

    /**
     * @param txtDescuento the txtDescuento to set
     */
    public void setTxtDescuentoTasa16(javax.swing.JFormattedTextField txtDescuentoTasa16) {
        this.setTxtDescuento(txtDescuentoTasa16);
    }

    /**
     * @return the txtDireccion
     */
    public javax.swing.JTextArea getTxtDireccion() {
        return txtDireccion;
    }

    /**
     * @param txtDireccion the txtDireccion to set
     */
    public void setTxtDireccion(javax.swing.JTextArea txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    /**
     * @return the txtFolio
     */
    public javax.swing.JTextField getTxtFolio() {
        return txtFolio;
    }

    /**
     * @param txtFolio the txtFolio to set
     */
    public void setTxtFolio(javax.swing.JTextField txtFolio) {
        this.txtFolio = txtFolio;
    }
    
    /**
     * @return the txtIdCliente
     */
    public javax.swing.JTextField getTxtIdCliente() {
        return txtIdCliente;
    }

    /**
     * @param txtIdCliente the txtIdCliente to set
     */
    public void setTxtIdCliente(javax.swing.JFormattedTextField txtIdCliente) {
        this.txtIdCliente = txtIdCliente;
    }

    /**
     * @return the txtIva
     */
    public javax.swing.JTextField getTxtIva() {
        return txtIva;
    }

    /**
     * @param txtIva the txtIva to set
     */
    public void setTxtIva(javax.swing.JTextField txtIva) {
        this.txtIva = txtIva;
    }

    /**
     * @return the txtMotivoDescuento
     */
    public javax.swing.JTextField getTxtMotivoDescuento() {
        return txtMotivoDescuento;
    }

    /**
     * @param txtMotivoDescuento the txtMotivoDescuento to set
     */
    public void setTxtMotivoDescuento(javax.swing.JFormattedTextField txtMotivoDescuento) {
        this.txtMotivoDescuento = txtMotivoDescuento;
    }

    /**
     * @return the txtNombre
     */
    public javax.swing.JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @param txtNombre the txtNombre to set
     */
    public void setTxtNombre(javax.swing.JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * @return the txtRfc
     */
    public javax.swing.JTextField getTxtRfc() {
        return txtRfc;
    }

    /**
     * @param txtRfc the txtRfc to set
     */
    public void setTxtRfc(javax.swing.JTextField txtRfc) {
        this.txtRfc = txtRfc;
    }

    /**
     * @return the txtSerie
     */
    public javax.swing.JTextField getTxtSerie() {
        return txtSerie;
    }

    /**
     * @param txtSerie the txtSerie to set
     */
    public void setTxtSerie(javax.swing.JTextField txtSerie) {
        this.txtSerie = txtSerie;
    }

    /**
     * @return the txtSubtotal
     */
    public javax.swing.JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    /**
     * @param txtSubtotal the txtSubtotal to set
     */
    public void setTxtSubtotal(javax.swing.JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    /**
     * @return the txtTotal
     */
    public javax.swing.JTextField getTxtTotal() {
        return txtTotal;
    }

    /**
     * @param txtTotal the txtTotal to set
     */
    public void setTxtTotal(javax.swing.JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * @return the modelo
     */
    public FacturaDao getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(FacturaDao modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the btnBuscarCliente
     */
    public WebButton getBtnBuscarCliente() {
        return btnBuscarCliente;
    }

    /**
     * @param btnBuscarCliente the btnBuscarCliente to set
     */
    public void setBtnBuscarCliente(WebButton btnBuscarCliente) {
        this.btnBuscarCliente = btnBuscarCliente;
    }

    /**
     * @return the btnObservaciones
     */
    public org.jdesktop.swingx.JXButton getBtnObservaciones() {
        return btnObservaciones;
    }

    /**
     * @return the btnTicket
     */
    public javax.swing.JButton getBtnTicket() {
        return btnTicket;
    }

    /**
     * @param btnTicket the btnTicket to set
     */
    public void setBtnTicket(javax.swing.JButton btnTicket) {
        this.btnTicket = btnTicket;
    }

    /**
     * @return the btnFacturaDia
     */
    public javax.swing.JButton getBtnFacturaDia() {
        return btnFacturaDia;
    }
    
    public JComboBox<TipoComprobante> getComboTipoComprobante() {
        return comboTipoComprobante;
    }

    public TipoComprobante getTipoComprobante() {
        return (TipoComprobante) comboTipoComprobante.getSelectedItem();
    }

    /**
     * @return the txtIEPS
     */
    public javax.swing.JTextField getTxtIEPS() {
        return txtIEPS;
    }
    
    public javax.swing.JButton getBtnVistaPrevia() {
        return btnVistaPrevia;
    }

    /**
     * @return the txtDescuento
     */
    public javax.swing.JFormattedTextField getTxtDescuento() {
        return txtDescuento;
    }

    /**
     * @param txtDescuento the txtDescuento to set
     */
    public void setTxtDescuento(javax.swing.JFormattedTextField txtDescuento) {
        this.txtDescuento = txtDescuento;
    }

    /**
     * @return the btnFacturasRelacionadas
     */
    public javax.swing.JButton getBtnFacturasRelacionadas() {
        return btnFacturasRelacionadas;
    }

    /**
     * @param btnFacturasRelacionadas the btnFacturasRelacionadas to set
     */
    public void setBtnFacturasRelacionadas(javax.swing.JButton btnFacturasRelacionadas) {
        this.btnFacturasRelacionadas = btnFacturasRelacionadas;
    }
}