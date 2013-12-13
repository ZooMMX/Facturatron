/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.Principal;

import facturatron.config.ConfigFiscalControl;
import facturatron.MVC.Controller;
import facturatron.MVC.Model;
import facturatron.acercade.AcercaDeControl;
import facturatron.cliente.ClienteControl;
import facturatron.config.ConfigPACControl;
import facturatron.config.ConfiguracionControl;
import facturatron.facturacion.FacturaControl;
import facturatron.facturacion.InformeMensual.InformeControl;
import facturatron.facturacion.ListadoControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Main extends Controller<Model, MainForm> {

    public Main() {
        setView(new MainForm());
        getView().setVisible(true);
        init();
    }

    @Override
    public void asignarEventos() {

        asignarLinkFactura();
        asignarLinkClientes();
        asignarLinkFacturasEmitidas();
        asignarLinkInformeMensual();
        asignarLinkCfgFiscal();
        asignarLinkCfgSistema();
        asignarLinkCfgPAC();
        asignarLinkAcercaDe();
        asignarLinkSalir();

    }

    private void facturar() throws Exception {
        FacturaControl fs = new FacturaControl();
        fs.setBusyHandler(new ProgressPanelBusyHandler(getView()));
        ((MainForm) getView()).addTab("Facturar", fs.getView());
    }

    private void clientes() throws Exception {
        ClienteControl fs = new ClienteControl();
        fs.setBusyHandler(new ProgressPanelBusyHandler(getView()));
        ((MainForm) getView()).addTab("Clientes", fs.getView());
    }

    private void facturasEmitidas() throws Exception {
        ListadoControl lc = new ListadoControl();
        lc.setBusyHandler(new ProgressPanelBusyHandler(getView()));
        ((MainForm) getView()).addTab("Facturas Emitidas", lc.getView());
        lc.init();
    }
    
    private void acercaDe() throws Exception {
        AcercaDeControl adc = new AcercaDeControl();
        ((MainForm) getView()).addTab("Acerca De", adc.getView());
    }

    private void informeMensual() throws Exception {
        InformeControl cfc = new InformeControl();
        ((MainForm) getView()).addTab("Informe Mensual SAT", cfc.getView());
    }

    private void configFiscal() throws Exception {
        ConfigFiscalControl cfc = new ConfigFiscalControl();
        ((MainForm) getView()).addTab("Configuración fiscal", cfc.getView());
    }

    private void configSistema() throws Exception {
        ConfiguracionControl cfc = new ConfiguracionControl();
        ((MainForm) getView()).addTab("Configuración sistema", cfc.getView());
    }    
    
    private void configPAC() throws Exception {
        ConfigPACControl cfc = new ConfigPACControl();
        ((MainForm) getView()).addTab("Configuración PAC", cfc.getView());
    }

    private void asignarLinkAcercaDe() {
        getView().getLinkCfgAcercaDe().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        try {
                            acercaDe();
                        } catch (Exception ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo facturación", ex);
                        }
                    }
                }.start();
            }
        });
    }

    private void asignarLinkFactura() {
        getView().getLinkFacturar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {

                    @Override
                    public void run() {
                        try {
                            facturar();
                        } catch (Exception ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo facturación", ex);
                        }
                    }
                }.start();
            }
        });
    }

    private void asignarLinkFacturasEmitidas() {
        getView().getLinkFacturasEmitidas().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {

                    public void run() {
                        try {
                            facturasEmitidas();
                        } catch (Exception ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo facturación", ex);
                        }
                    }
                }.start();
            }
        });
    }

    private void asignarLinkInformeMensual() {
        getView().getLinkInformeMensual().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {

                    public void run() {
                        try {
                            informeMensual();
                        } catch (Exception ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo facturación", ex);
                        }
                    }
                }.start();
            }
        });
    }

    private void asignarLinkClientes() {
        getView().getLinkCatalogo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientes();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo Catalogo", ex);
                }
            }
        });
    }

    private void asignarLinkCfgFiscal() {
        getView().getLinkCfgFiscal().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configFiscal();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Excepción en módulo ConfigFiscal", ex);
                }
            }
        });
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
    
    
    private void asignarLinkCfgPAC() {
        getView().getLinkCfgPAC().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configPAC();
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
        });
    }

    private void cerrarSistema() {
        System.exit(0);
    }

    @Override
    public void enlazarModeloVista() {
        //Éste controlador no tiene modelo
    }
}
