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
import java.rmi.RemoteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import servlets.WSSoap;
import servlets.WSSoapService;


import com.jaxb.generated.Search;


public class ClientInterfaz extends javax.swing.JFrame {
	private static final QName SERVICE_NAME = new QName("http://servlets/", "WSSoapService");//para soap
	
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
    private javax.swing.JLabel jInfo;
    
    private int indice = 0; //Variable para paginaci�n
    private Search results = new Search();//Resultados de la b�squeda
    private int total = 0; //Almacena el n�mero total de resultados obtenidos en la b�squeda
    

    /**
     * Crea el formulario
     */
    public ClientInterfaz() {
        initComponents();
    }

    /**
     * Este m�todo inicializa el formulario
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
        jInfo = new javax.swing.JLabel();
        

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

        jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquier campo", "T�tulo", "Descripci�n", "Site", "Organizaci�n" }));

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
            

			@Override
			public void mouseClicked(MouseEvent e) {
				javax.swing.JTable jt = new javax.swing.JTable();
				jt = (javax.swing.JTable) e.getSource();
				//System.out.println("Bot�n clickado en la fila " + jt.getSelectedRow());
				mostrarURL(jt.getSelectedRow());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
        });
        jScrollPane1.setViewportView(jTable);
        
        jInfo.setFont(new java.awt.Font("Arial", 0, 12));
        jInfo.setForeground(new java.awt.Color(35, 88, 135));
        jInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jInfo.setEnabled(true);
        jInfo.setText("Inserta la palabra a buscar sin espacios.");

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
        
        
        //Alineaci�n y distribuci�n de los componentes
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
        
        //Fin de la alineaci�n y distribuci�n de los componentes

        pack();
    }

    
    /*
     * Selecciona los campos de b�squeda disponible por cada base de b�squeda
     */
    private void jCBoxBBDDActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	int filas=jTable.getRowCount();
        if (jCBoxBBDD.getSelectedItem().equals("Medline")) {
            jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Cualquier campo", "T�tulo", "Descripci�n", "Site", "Organizaci�n" }));
            //Cambiamos el t�tulo y borramos los resultados.
            for (int i = 0;filas>i; i++) {
            	jTable.setValueAt(null, i, 0);
            	jTable.setValueAt(null, i, 1);
            }
            jTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Healthtopic");
            jTable.repaint();
        }
        else{
            jCBoxCampo.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Cualquier campo", "Organizaci�n", "Estado", "Ciudad" }));
            //Cambiamos el t�tulo y borramos los resultados.
            for (int i = 0;filas>i; i++) {
            	jTable.setValueAt(null, i, 0);
            	jTable.setValueAt(null, i, 1);
            }
            jTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Organizaci�n");
            jTable.repaint();
        }
    }                                         
    
    //M�todos de b�squeda excluyentes
    private void jRButtonSoapActionPerformed(java.awt.event.ActionEvent evt) { 
    	if (jRButtonSoap.isSelected()){
    		jRButtonRest.setSelected(false);
    	}else{
    		jRButtonRest.setSelected(true);
    	}
    }   
    //M�todos de b�squeda excluyentes
    private void jRButtonRestActionPerformed(java.awt.event.ActionEvent evt) { 
    	if (jRButtonRest.isSelected()){
    		jRButtonSoap.setSelected(false);
    	}else{
    		jRButtonSoap.setSelected(true);
    	}
    }
   
    //Paginaci�n
    private void jRButtonMenosActionPerformed(ActionEvent evt) {
    	//Se actualiza el valor del �ndice de p�gina
    	indice-=10;
    	//Si se est� en la primera p�gina el bot�n menos se deshabilita
		if (indice==0) jButtonMenos.setEnabled(false);
		//Variable para marcar el fin de la p�gina actual
		int finalindice = indice + 10;
        //Carga la p�gina con los nuevos datos
		for (int i = indice; i<finalindice; i++) {
        	jTable.setValueAt(results.getTitle().toArray()[i], i-indice, 0);
        	jTable.setValueAt(results.getUrl().toArray()[i], i-indice, 1);
        }
        jTable.repaint();
        //Siempre que se pulsa sobre el bot�n menos el bot�n m�s debe
        //habilitarse. p.ej. si se llega a la p�gina final el bot�n mas
        //se deshabilita, al pulsar sobre el bot�n menos lo habilitadmos
        //de nuevo.
        jButtonMas.setEnabled(true);
        
		jInfo.setText("Mostrando los resultados del " + indice + " al " + (indice + 10) + " de un total de " + total + " resultados.");

		
	}
    
    private void jRButtonMasActionPerformed(ActionEvent evt) {
    	//Se aumenta el �ndice que marca el inicio de p�gina
    	indice+=10;
    	//Si se ha llegado a la �ltima p�gina el bot�n m�s se deshabilita
    	if (total-indice<10) jButtonMas.setEnabled(false);
    	//Variable para marcar el fin de la p�gina actual
    	int finalindice = indice + 10;
    	//Si se llega a la �ltima p�gina, el �ndice final debe ser el 
    	//del tama�o de los resultados.
    	if (finalindice > total) finalindice = total;
    	//Carga la p�gina con los nuevos datos
    	for (int i = indice; i<finalindice; i++) {
    		jTable.setValueAt(results.getTitle().toArray()[i], i-indice, 0);
    		jTable.setValueAt(results.getUrl().toArray()[i], i-indice, 1);
    	}
    	//En la �ltima p�gina borra los resultados de la p�gina anterior
    	//que no se han modificado al llegar a la p�gina nueva. Es decir
    	//si hay 23 resultados se borrar�an las filas 4,5,6,7,8,9,10 que
    	//contendr�an los resultados de la p�gina anterior.
    	if (finalindice % 10 != 0){
    		for (int i = finalindice; i<indice+10; i++) {
    			jTable.setValueAt(null, i-indice, 0);
    			jTable.setValueAt(null, i-indice, 1);
    		}
    	}
    	jTable.repaint();
    	//Siempre que se pulsa sobre el bot�n m�s el bot�n menos debe es-
    	//tar habilitado.
    	jButtonMenos.setEnabled(true);
    	
		jInfo.setText("Mostrando los resultados del " + indice + " al " + (indice + 10) + " de un total de " + total + " resultados.");


    }

    private void jRButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) { 
    	
    	String base = jCBoxBBDD.getSelectedItem().toString();
    	String palabra = jTextPalabra.getText().toString();
    	String campo = jCBoxCampo.getSelectedItem().toString();
    	if (campo.equalsIgnoreCase("T�tulo")){
    		campo = "Title";
    	}else if (campo.equalsIgnoreCase("Cualquier campo")){
    		campo ="*";
    	}else if (campo.equalsIgnoreCase("Descripci�n")){
    		campo ="FullSummary";
    	}else if (campo.equalsIgnoreCase("Organizaci�n")){
    		campo ="Organization";
    	}else if (campo.equalsIgnoreCase("Estado")){
    		campo ="State";
    	}else if (campo.equalsIgnoreCase("Ciudad")){
    		campo ="City";
    	}
    	//Los espacios no son admitidos en una URL. Se eliminan, la b�squeda debe ser de una
    	//palabra �nicamente. 
    	palabra = palabra.replaceAll(" ", "");
    	
    	//TODO Modificar URL en funci�n de la instalaci�n TOMCAT
    	String urltxt = "http://localhost:8080/MedLineBennett/rs/search?catalogo=" + base +
    			"&palabra=" + palabra + "&campo=" + campo;

    	String ws = "SOAP";
    	if (jRButtonRest.isSelected()) ws = "RESTFul";
    	System.out.println("Web Service: " + ws);

    	if (ws.equals("RESTFul")){
    		try
    		{
    			URL url = new URL(urltxt);
    			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    			conn.setRequestMethod("GET");
    			conn.setRequestProperty("Accept", "text/xml");
    			
    			System.out.println("getResponseCode = " + conn.getResponseCode());
    			System.out.println("getResponseMessage = " + conn.getResponseMessage());
    			
                if (conn.getResponseCode() != 200) 
                {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }
    			
    			//Se limpia la tabla
    			for (int i = 0;i<10; i++) {
    				jTable.setValueAt(null, i, 0);
    				jTable.setValueAt(null, i, 1);
    			}

    			jTable.repaint();

    			Search s = new Search();

    			results = s.inicio(conn.getInputStream());
    			
    			/*BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    	    	String apiOutput = br.readLine();
    	    	while ((apiOutput = br.readLine()) != null) {

    	    		System.out.println(apiOutput);

    	    	}*/

    			conn.disconnect();

    		} catch (MalformedURLException e) {
    			System.out.println("Error al direccionar la b�squeda.");
    			//e.printStackTrace();
    		} catch (IOException e) {
    			System.out.println("Error en la conexi�n.");
    			System.out.println("Compruebe que el servidor est� arrancado.");
    			System.out.println("Compruebe que la URL apunta al puerto correcto.");
    			//e.printStackTrace();
    		}
    	}

    	if (ws.equals("SOAP")){
    	/*
    	System.out.println("Bot�n pulsado");
    	System.out.println("Busqueda:");
    	System.out.println("Base de busqueda: " + jCBoxBBDD.getSelectedItem().toString());
    	System.out.println("Palabra a buscar: " + jTextPalabra.getText());
    	System.out.println("Campo de busqueda: " + jCBoxCampo.getSelectedItem().toString());
    	/*CLIENTE SOAP*/
    	
    		/*
    		try {
			URL url = new URL("http://localhost:9999/cal3?wsdl");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		QName qname = new QName("http://anil.hcl.com/","CalWebServiceImplService");

		//Service service = Service.create(url,qname);
		//CalWebservice calservice = service.getPort(CalWebservice.class);
		//codigo clase test

		WSSoapService ss = null;
		try {
			ss = new WSSoapService(new URL("http://localhost:8080/MedLineBennett/services/WSSoapPort?wsdl"), SERVICE_NAME);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     WSSoap port = ss.getWSSoapPort();  
	  
	    servlets.SoapSearch add0 = new servlets.SoapSearch();
		
		
		add0.setArg0(1);
		add0.setArg1("*");//campo
		add0.setArg2("");//texto
		add0.setArg3("Bennett"); //usar "Bennett" o "MedLine"
		
		servlets.Search finalvalue = new servlets.Search();
			
		finalvalue = port.soapSearch(add0.getArg0(), add0.getArg1(), add0.getArg2(), add0.getArg3());
		
		for (int i=0;  i < finalvalue.getTitle().size()-1;  i++){
			
			System.out.println(finalvalue.getTitle().get(i));
			System.out.println(finalvalue.getURL().get(i));
			
		}
		
			
		System.out.println("Hello2");
		System.out.println();
		System.out.println("Hello3");	
		
		*/
		
		//String[] resultados;
		
		//resultados = calservice.add(10, 18);
		//for (int i=0; i<resultados.length; i++){
		//System.out.println(resultados[i].toString());
		//}
    		

    	}	
    		
    	




    	total = results.getTitle().size();
    	//Si la b�squeda tiene m�s de 10 resultados se habilita el bot�n 10+
    	if (total>10) jButtonMas.setEnabled(true);
    	int finalindice = indice + 10;
    	if (finalindice > total) finalindice = total;
    	for (int i = indice; i<finalindice; i++) {
    		//jTable.setValueAt(results.getTitle().toArray()[i], i, 0);
    		jTable.setValueAt(results.getTitle().get(i), i, 0);
    		jTable.setValueAt(results.getUrl().get(i), i, 1);
    	}
    	jTable.repaint();

    	
    	

    	//TODO Incluir informaci�n en el JLabel pendiente de crear
    	if (total==0){
    		jInfo.setText("No hay ning�n resultado que contenga la palabra " + palabra + ".");
    	}
    	else{
    		jInfo.setText("Mostrando los resultados del " + indice + " al " + (indice + 10) + " de un total de " + total + " resultados.");
    	}


    


    }
    	

    private void mostrarURL(int fila){
    	fila = fila + indice;
    	// Create Desktop object
        Desktop d=Desktop.getDesktop();
        // Seleccionamos la URL en funci�n de la fila seleccionada
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
