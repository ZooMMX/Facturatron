/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Principal;

import com.jidesoft.swing.TabEditingEvent;
import com.jidesoft.swing.TabEditingListener;
import facturatron.config.ConfigFiscalControl;
import facturatron.MVC.Controller;
import facturatron.MVC.Model;
import facturatron.cliente.ClienteControl;
import facturatron.config.ConfiguracionControl;
import facturatron.facturacion.FacturaControl;
import facturatron.facturacion.ListadoControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.swingfx.waitwithstyle.InfiniteProgressPanel;

/**

 */
public class Main extends Controller<Model, MainForm> {
    
    public Main() {
        setView(new MainForm());
        getView().setVisible(true);
        init();
        //InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
        //getView().setGlassPane(glasspane);
        //glasspane.start();
        
    }

    @Override
    public void asignarEventos() {
        
        asignarLinkFactura();
        asignarLinkClientes();
        asignarLinkFacturasEmitidas();
        asignarLinkCfgFiscal();
        asignarLinkCfgSistema();
        asignarLinkSalir();
        asignarMenuSalir();
        
    }

    private void facturar() throws Exception {
        FacturaControl fs = new FacturaControl();
        ((MainForm)getView()).addTab("Facturar", fs.getView());
    }


     private void clientes() throws Exception {
        ClienteControl fs = new ClienteControl();
        ((MainForm)getView()).addTab("Clientes", fs.getView());
    }

     private void facturasEmitidas() throws Exception {
        ListadoControl lc = new ListadoControl();
        ((MainForm)getView()).addTab("Facturas Emitidas", lc.getView());
    }

     private void configFiscal() throws Exception {
        ConfigFiscalControl cfc = new ConfigFiscalControl();
        ((MainForm)getView()).addTab("Configuración fiscal", cfc.getView());
    }

     private void configSistema() throws Exception {
        ConfiguracionControl cfc = new ConfiguracionControl();
        ((MainForm)getView()).addTab("Configuración sistema", cfc.getView());
    }

    private void asignarLinkFactura() {
        getView().getLinkFacturar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    facturar();
                } catch (Exception ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo facturación", ex);
                }            }
        }
        );
    }

    private void asignarLinkFacturasEmitidas() {
        getView().getLinkFacturasEmitidas().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    facturasEmitidas();
                } catch (Exception ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo facturación", ex);
                }            }
        }
        );
    }

    private void asignarLinkClientes() {
        getView().getLinkCatalogo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientes();
                } catch (Exception ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo Catalogo", ex);
                }            }
        }
        );
    }

    private void asignarLinkCfgFiscal() {
        getView().getLinkCfgFiscal().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configFiscal();
                } catch (Exception ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo ConfigFiscal", ex);
                }            }
        }
        );
    }

    private void asignarLinkCfgSistema() {
        getView().getLinkCfgSystem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configSistema();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo ConfigSistema", ex);
                }
            }
        });
    }

    private void asignarLinkSalir() {
        getView().getLinkSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSistema();
            }
        }
        );
    }


    private void cerrarSistema() {
        System.exit(0);
    }

    private void asignarMenuSalir() {
        getView().getMenuSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSistema();
            }
        }
        );
    }

    @Override
    public void enlazarModeloVista() {
        //Éste controlador no tiene modelo
    }


}
