import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ClientInterfaz extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	// Variables                  
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonMas;
    private javax.swing.JButton jButtonMenos;
    private javax.swing.JComboBox<String> jCBoxBBDD;
    private javax.swing.JComboBox<String> jCBoxCampo;
    private javax.swing.JPanel jPanel;
    private javax.swing.JRadioButton jRButtonRest;
    private javax.swing.JRadioButton jRButtonSoap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextPalabra;
    private javax.swing.JLabel titulo;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel jInfo;

    /**
     * Crea el formulario
     */
    public ClientInterfaz() {
        initComponents();
    }

    /**
     * Este método inicializa el formulario
     */
    @SuppressWarnings("unchecked")          
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        jCBoxBBDD = new javax.swing.JComboBox<String>();
        jTextPalabra = new javax.swing.JTextField();
        jCBoxCampo = new javax.swing.JComboBox<String>();
        jRButtonSoap = new javax.swing.JRadioButton();
        jRButtonRest = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonBuscar = new javax.swing.JButton();
        jButtonMenos = new javax.swing.JButton();
        jButtonMas = new javax.swing.JButton();
        jInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBackground(new java.awt.Color(230, 230, 225));
        jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        titulo.setFont(new java.awt.Font("Arial Black", 1, 24));
        titulo.setForeground(Color.lightGray);
        titulo.setText("MedlineBennet Web Service Search");

        jCBoxBBDD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Medline", "Bennet" }));
        jCBoxBBDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxBBDDActionPerformed(evt);
            }
        });

        jTextPalabra.setText("Palabra a buscar");

        jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquier campo", "Título", "Descripción", "Site", "Organización" }));

        jRButtonSoap.setSelected(true);
        jRButtonSoap.setText("SOAP");
        jRButtonSoap.setSelected(true);
        jRButtonSoap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRButtonSoapActionPerformed(evt);
            }
        });

        jRButtonRest.setText("RESTFul");
        jRButtonRest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRButtonRestActionPerformed(evt);
            }
        });

        jTable.setFont(new java.awt.Font("Arial", 0, 12));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {"", null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null}
                },
                new String [] {
                    "Healthtopic", "URL"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });
        jTable.setMinimumSize(new java.awt.Dimension(30, 160));
        jScrollPane1.setViewportView(jTable);
        
        jInfo.setFont(new java.awt.Font("Arial", 0, 12)); 
        jInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonBuscar.setBackground(new java.awt.Color(194, 191, 191));
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRButtonBuscarActionPerformed(evt);
            }
        });

        jButtonMenos.setBackground(new java.awt.Color(194, 191, 191));
        jButtonMenos.setText("10 menos");
        jButtonMenos.setEnabled(false);

        jButtonMas.setBackground(new java.awt.Color(194, 191, 191));
        jButtonMas.setText("10 más");
        jButtonMas.setEnabled(false);
        
        //Alineación y distribución de los componentes
        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jButtonMenos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButtonMas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                        .addComponent(jCBoxBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCBoxCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(66, 66, 66))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jRButtonSoap)
                                .addGap(82, 82, 82)
                                .addComponent(jRButtonRest)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBoxBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBoxCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRButtonSoap)
                    .addComponent(jRButtonRest)
                    .addComponent(jButtonBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMenos)
                    .addComponent(jInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        //Fin de la alineación y distribución de los componentes

        pack();
    }

    /*
     * Selecciona los campos de búsqueda disponible por cada base de búsqueda
     */
    private void jCBoxBBDDActionPerformed(java.awt.event.ActionEvent evt) {
    	int filas=jTable.getRowCount();
        if (jCBoxBBDD.getSelectedItem().equals("Medline")) {
            jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Cualquier campo", "Título", "Descripción", "Site", "Organización" }));
            //Cambiamos el título y borramos los resultados.
            for (int i = 0;filas>i; i++) {
            	jTable.setValueAt(null, 0, 0);
            	jTable.setValueAt(null, 0, 1);
            }
            jTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Healthtopic");
            jTable.repaint();
        }
        else{
            jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Cualquier campo", "Organización", "Estado", "Ciudad" }));
            //Cambiamos el título y borramos los resultados.
            for (int i = 0;filas>i; i++) {
            	jTable.setValueAt(null, 0, 0);
            	jTable.setValueAt(null, 0, 1);
            }
            jTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Organización");
            jTable.repaint();
        }
    }                                         
    
    //Métodos de búsqueda excluyentes
    private void jRButtonSoapActionPerformed(java.awt.event.ActionEvent evt) { 
    	if (jRButtonSoap.isSelected()){
    		jRButtonRest.setSelected(false);
    	}else{
    		jRButtonRest.setSelected(true);
    	}
    }   
    //Métodos de búsqueda excluyentes
    private void jRButtonRestActionPerformed(java.awt.event.ActionEvent evt) { 
    	if (jRButtonRest.isSelected()){
    		jRButtonSoap.setSelected(false);
    	}else{
    		jRButtonSoap.setSelected(true);
    	}
    }
    
    private void jRButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) { 
    	System.out.println("Botón pulsado");
    	System.out.println("Busqueda:");
    	System.out.println("Base de busqueda: " + jCBoxBBDD.getSelectedItem().toString());
    	System.out.println("Palabra a buscar: " + jTextPalabra.getText());
    	System.out.println("Campo de busqueda: " + jCBoxCampo.getSelectedItem().toString());
    	String ws = "SOAP";
    	if (jRButtonRest.isSelected()) ws = "RESTFul";
    	System.out.println("Web Service: " + ws);
    }


     public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Crea y muestra el formulario */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientInterfaz().setVisible(true);
            }
        });
    }

                    
}
