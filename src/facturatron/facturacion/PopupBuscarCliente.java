/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextField;
import com.alee.managers.popup.PopupWay;
import com.alee.managers.popup.WebButtonPopup;
import facturatron.Dominio.Persona;
import facturatron.cliente.ClienteTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author octavioruizcastillo
 */
public class PopupBuscarCliente {
    FacturaControl controller;
    TimerBusqueda  timerBusqueda;
    WebTextField   field;
    ClienteTableModel tableModel;
    WebTable          table;
    WebButtonPopup    popup;
    
    public PopupBuscarCliente(FacturaControl c) {
        controller = c;
    }
    
    public void install() {
         // Button that calls for popup
        WebButton showPopup = controller.getView().getBtnBuscarCliente();

        // Popup itself
        popup = new WebButtonPopup ( showPopup, PopupWay.downLeft );

        // Popup content
        WebLabel label = new WebLabel ( "Buscador de clientes", WebLabel.CENTER );
        field      = new WebTextField ( "", 10 );
        table      = new WebTable();
        tableModel = new ClienteTableModel();
        table.setModel( tableModel );
        table.getColumnModel().getColumn(0).setMaxWidth(40); //Ancho del ID
        table.getColumnModel().getColumn(1).setMinWidth(280); //Ancho de la razón social
        table.getColumnModel().getColumn(2).setMinWidth(120); //Ancho del RFC
        table.getColumnModel().getColumn(3).setMinWidth(120); //Ancho de la calle
        table.getColumnModel().getColumn(4).setMinWidth(120); //Ancho del colonia
        table.getColumnModel().getColumn(5).setMinWidth(120); //Ancho del municipio
        field.setHorizontalAlignment ( SwingConstants.CENTER );
        GroupPanel content = new GroupPanel ( 5, false, label, field, table );
        content.setMinimumWidth(800);
        content.setMargin ( 15 );
        
        // Setup events
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        table.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectCliente();
            }
        });
        content.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        content.getActionMap().put("Escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                popup.hidePopup();
            }
        });
        field.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) selectCliente();
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    int sigFila = table.getSelectedRow()+1;
                    if(sigFila < table.getRowCount())
                    {
                        table.setRowSelectionInterval(sigFila, sigFila);
                        table.scrollRectToVisible(table.getCellRect(sigFila, 1, true));
                    }
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    int antFila = table.getSelectedRow()-1;
                    if(antFila >= 0) {
                        table.setRowSelectionInterval(antFila, antFila);
                        table.scrollRectToVisible(table.getCellRect(antFila, 1, true));
                    }
                }
                else preBuscar();
            }
        } );

        // Setup popup content
        popup.setContent ( content );

        // Component focused by default
        popup.setDefaultFocusComponent ( field );
    }
    
    private void selectCliente() {
        int selected = table.getSelectedRow();
        Persona persona = tableModel.getData().get(selected);
        controller.getView().getTxtIdCliente().setText(persona.getId().toString());
        field.setText("");
        buscar();
        popup.hidePopup();
        controller.cargarCliente();
        controller.getView().getTxtIdCliente().requestFocus();
    }
    
    private void preBuscar()
    {
        if(timerBusqueda != null && timerBusqueda.isAlive()) { timerBusqueda.cancelar(); }
        this.timerBusqueda = new TimerBusqueda();
        timerBusqueda.start();
       
    }
    
    private void buscar() {
        String searchString = field.getText();
        tableModel.search(searchString);
        tableModel.fireTableDataChanged();
    }
    
    class TimerBusqueda extends Thread
    {
        boolean busquedaActiva = true;

        TimerBusqueda() {  }
        public void run()
        {
            synchronized(this)
            {
                busquedaActiva = true;
                try { this.wait(500); } catch(InterruptedException e) { 
                    Logger.getLogger(PopupBuscarCliente.class.getName()).log(Level.SEVERE, "Error en el timer de búsqueda automática", e);
                }
                if(busquedaActiva) { PopupBuscarCliente.this.buscar(); }
            }
        }
        void cancelar()
        {
            busquedaActiva = false;
            try { this.notify(); } catch(Exception e) {}
        }
    }

    
}
