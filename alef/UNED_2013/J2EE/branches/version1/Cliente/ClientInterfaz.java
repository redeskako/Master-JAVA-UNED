import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jaxb.generated.Search;


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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextPalabra;
    private javax.swing.JLabel titulo;
    private javax.swing.JTable jTable;
    
    private int indice = 0; //Variable para paginación
    private Search results = new Search();//Resultados de la búsqueda
    

    /**
     * Crea el formulario
     */
    public ClientInterfaz() {
        initComponents();
    }

    /**
     * Este método inicializa el formulario
     */
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
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBackground(new java.awt.Color(230, 230, 225));
        jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        titulo.setFont(new java.awt.Font("Arial Black", 1, 24));
        titulo.setForeground(Color.lightGray);
        titulo.setText("MedlineBennet Web Service Search");

        jCBoxBBDD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Medline", "Bennett" }));
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
        jTable.addMouseListener(new java.awt.event.MouseListener() {
            /*public void actionPerformed(java.awt.event.MouseEvent evt) {
                jTableActionPerformed(evt);
            }*/

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				javax.swing.JTable jt = new javax.swing.JTable();
				jt = (javax.swing.JTable) e.getSource();
				System.out.println("Botón clickado en la fila " + jt.getSelectedRow());
				mostrarURL(jt.getSelectedRow());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        jScrollPane1.setViewportView(jTable);

        jButtonBuscar.setBackground(new java.awt.Color(194, 191, 191));
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRButtonBuscarActionPerformed(evt);
            }
        });

        jButtonMenos.setBackground(new java.awt.Color(194, 191, 191));
        jButtonMenos.setText("10 -");
        jButtonMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRButtonMenosActionPerformed(evt);
            }
        });
        jButtonMenos.setEnabled(false);

        jButtonMas.setBackground(new java.awt.Color(194, 191, 191));
        jButtonMas.setText("10 +");
        jButtonMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRButtonMasActionPerformed(evt);
            }
        });
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
                                .addGap(443, 443, 443)
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
            	jTable.setValueAt(null, i, 0);
            	jTable.setValueAt(null, i, 1);
            }
            jTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Healthtopic");
            jTable.repaint();
        }
        else{
            jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Cualquier campo", "Organización", "Estado", "Ciudad" }));
            //Cambiamos el título y borramos los resultados.
            for (int i = 0;filas>i; i++) {
            	jTable.setValueAt(null, i, 0);
            	jTable.setValueAt(null, i, 1);
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
   
    //Paginación
    private void jRButtonMenosActionPerformed(ActionEvent evt) {
    	//Se actualiza el valor del índice de página
    	indice-=10;
    	//Si se está en la primera página el botón menos se deshabilita
		if (indice==0) jButtonMenos.setEnabled(false);
		//Variable para marcar el fin de la página actual
		int finalindice = indice + 10;
        //Carga la página con los nuevos datos
		for (int i = indice; i<finalindice; i++) {
        	jTable.setValueAt(results.getTitle().toArray()[i], i-indice, 0);
        	jTable.setValueAt(results.getUrl().toArray()[i], i-indice, 1);
        }
        jTable.repaint();
        //Siempre que se pulsa sobre el botón menos el botón más debe
        //habilitarse. p.ej. si se llega a la página final el botón mas
        //se deshabilita, al pulsar sobre el botón menos lo habilitadmos
        //de nuevo.
        jButtonMas.setEnabled(true);
		
	}
    
    private void jRButtonMasActionPerformed(ActionEvent evt) {
    	//Se aumenta el índice que marca el inicio de página
    	indice+=10;
    	//Si se ha llegado a la última página el botón más se deshabilita
		if (results.getTitle().size()-indice<10) jButtonMas.setEnabled(false);
		//Variable para marcar el fin de la página actual
		int finalindice = indice + 10;
		//Si se llega a la última página, el índice final debe ser el 
		//del tamaño de los resultados.
        if (finalindice > results.getTitle().size()) finalindice = results.getTitle().size();
        //Carga la página con los nuevos datos
		for (int i = indice; i<finalindice; i++) {
        	jTable.setValueAt(results.getTitle().toArray()[i], i-indice, 0);
        	jTable.setValueAt(results.getUrl().toArray()[i], i-indice, 1);
        }
		//En la última página borra los resultados de la página anterior
		//que no se han modificado al llegar a la página nueva. Es decir
		//si hay 23 resultados se borrarían las filas 4,5,6,7,8,9,10 que
		//contendrían los resultados de la página anterior.
		if (finalindice % 10 != 0){
			for (int i = finalindice; i<indice+10; i++) {
	        	jTable.setValueAt(null, i-indice, 0);
	        	jTable.setValueAt(null, i-indice, 1);
	        }
		}
        jTable.repaint();
        //Siempre que se pulsa sobre el botón más el botón menos debe es-
        //tar habilitado.
        jButtonMenos.setEnabled(true);
		
	}
    
    private void jRButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) { 
    	String base = jCBoxBBDD.getSelectedItem().toString();
    	String palabra = jTextPalabra.getText().toString();
    	String campo = jCBoxCampo.getSelectedItem().toString();
    	if (campo.equalsIgnoreCase("Título")){
    		campo = "Title";
    	}else if (campo.equalsIgnoreCase("Cualquier campo")){
    		campo ="*";
    	}else if (campo.equalsIgnoreCase("Descripción")){
    		campo ="FullSummary";
    	}else if (campo.equalsIgnoreCase("Organización")){
    		campo ="Organization";
    	}else if (campo.equalsIgnoreCase("Estado")){
    		campo ="State";
    	}else if (campo.equalsIgnoreCase("Ciudad")){
    		campo ="City";
    	}
    	//Los espacios no son admitidos en una URL. Se eliminan, la búsqueda debe ser de una
    	//palabra únicamente. 
    	palabra = palabra.replaceAll(" ", "");
    	//Modificar URL en función de la instalación TOMCAT
    	String urltxt = "http://localhost:9999/MedLineBennett/rs/search?catalogo=" + base +
    			"&palabra=" + palabra + "&campo=" + campo;
    	System.out.println("Botón pulsado");
    	System.out.println("Busqueda:");
    	System.out.println("Base de busqueda: " + jCBoxBBDD.getSelectedItem().toString());
    	System.out.println("Palabra a buscar: " + jTextPalabra.getText());
    	System.out.println("Campo de busqueda: " + jCBoxCampo.getSelectedItem().toString());
    	String ws = "SOAP";
    	if (jRButtonRest.isSelected()) ws = "RESTFul";
    	System.out.println("Web Service: " + ws);
    	
    	try
        {
            URL url = new URL(urltxt);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/xml");
            /*
            if (conn.getResponseCode() != 200) 
            {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
 			*/
            //Se limpia la tabla
            for (int i = 0;i<10; i++) {
            	jTable.setValueAt(null, i, 0);
            	jTable.setValueAt(null, i, 1);
            }
            
            jTable.repaint();
            
            Search s = new Search();
            
            results = s.inicio(conn.getInputStream());
            //Si la búsqueda tiene más de 10 resultados se habilita el botón 10+
            if (results.getTitle().size()>10) jButtonMas.setEnabled(true);
            int finalindice = indice + 10;
            if (finalindice > results.getTitle().size()) finalindice = results.getTitle().size();
            for (int i = indice; i<finalindice; i++) {
            	jTable.setValueAt(results.getTitle().toArray()[i], i, 0);
            	jTable.setValueAt(results.getUrl().toArray()[i], i, 1);
            }
            jTable.repaint();
            
         
  
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            /*String apiOutput = br.readLine();
            while ((apiOutput = br.readLine()) != null) {
            	
            	    System.out.println(apiOutput);
            	
            	  }*/
            System.out.println("Mostrando los resultados del " + indice + " al " + (indice + 10) + " de un total de " + results.getTitle().size() + " resultados.");
            //System.out.println("dkjkl" + results.getTitle().toArray()[0]);
           
            conn.disconnect();
 
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    	

    private void mostrarURL(int fila){
    	fila = fila + indice;
    	// Create Desktop object
        Desktop d=Desktop.getDesktop();
        // Seleccionamos la URL en función de la fila seleccionada
        String web = results.getUrl().toArray()[fila].toString();
        // Browse a URL, say google.com
        if (web!=""&&web!=null){
        try {
			d.browse(new URI(web));
		} catch (URISyntaxException | IOException e) {
			System.out.println("Error al intentar acceder a la URL seleccionada");
			e.printStackTrace();
		}
        }
    	
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
