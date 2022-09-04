package es.csc.biblioteca.gui.main;

import es.csc.biblioteca.i18n.LocaleManager;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class DesktopMDI extends JFrame {

    /**
     * Creates new form DesktopMDI
     */
    public DesktopMDI() {
        initComponents();
        initLookAndFeelMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        languageButtonGroup = new javax.swing.ButtonGroup();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        customersMenuItem = new javax.swing.JMenuItem();
        booksMenuItem = new javax.swing.JMenuItem();
        loansMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        printMenu = new javax.swing.JMenu();
        printCustomersMenuItem = new javax.swing.JMenuItem();
        printBooksMenuItem = new javax.swing.JMenuItem();
        printLoansMenuItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        languageMenu = new javax.swing.JMenu();
        spanishMenuItem = new javax.swing.JRadioButtonMenuItem();
        englishMenuItem = new javax.swing.JRadioButtonMenuItem();
        francaisMenuItem = new javax.swing.JRadioButtonMenuItem();
        lookAndFeelMenu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        cascadeMenuItem = new javax.swing.JMenuItem();
        tileHMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        closeCurrentMenuItem = new javax.swing.JMenuItem();
        closeOthersMenuItem = new javax.swing.JMenuItem();
        closeAllMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        dragModeMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        debugMenuItem = new javax.swing.JCheckBoxMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TITLE");

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        customersMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/id_cards16.png"))); // NOI18N
        customersMenuItem.setText("Customers");
        fileMenu.add(customersMenuItem);

        booksMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/books16.png"))); // NOI18N
        booksMenuItem.setText("Books");
        fileMenu.add(booksMenuItem);

        loansMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/box16.png"))); // NOI18N
        loansMenuItem.setText("Loans");
        fileMenu.add(loansMenuItem);
        fileMenu.add(jSeparator1);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        printMenu.setText("Print");

        printCustomersMenuItem.setText("Customers");
        printCustomersMenuItem.setEnabled(false);
        printMenu.add(printCustomersMenuItem);

        printBooksMenuItem.setText("Books");
        printBooksMenuItem.setEnabled(false);
        printMenu.add(printBooksMenuItem);

        printLoansMenuItem.setText("Loans");
        printLoansMenuItem.setEnabled(false);
        printMenu.add(printLoansMenuItem);

        menuBar.add(printMenu);

        toolsMenu.setText("Tools");

        languageMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/earth16.png"))); // NOI18N
        languageMenu.setText("Language");

        languageButtonGroup.add(spanishMenuItem);
        spanishMenuItem.setText("Spanish");
        spanishMenuItem.setName("es_ES"); // NOI18N
        languageMenu.add(spanishMenuItem);

        languageButtonGroup.add(englishMenuItem);
        englishMenuItem.setText("English");
        englishMenuItem.setName("en_US"); // NOI18N
        languageMenu.add(englishMenuItem);

        languageButtonGroup.add(francaisMenuItem);
        francaisMenuItem.setText("Francais");
        francaisMenuItem.setToolTipText("");
        francaisMenuItem.setName("fr_FR"); // NOI18N
        francaisMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                francaisMenuItemActionPerformed(evt);
            }
        });
        languageMenu.add(francaisMenuItem);

        toolsMenu.add(languageMenu);

        lookAndFeelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/masks16.png"))); // NOI18N
        lookAndFeelMenu.setText("Look And Feel");
        toolsMenu.add(lookAndFeelMenu);

        menuBar.add(toolsMenu);

        jMenu1.setText("Window");

        cascadeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/windows16.png"))); // NOI18N
        cascadeMenuItem.setText("Cascade");
        jMenu1.add(cascadeMenuItem);

        tileHMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/window_split_hor16.png"))); // NOI18N
        tileHMenuItem.setText("Tile");
        jMenu1.add(tileHMenuItem);
        jMenu1.add(jSeparator2);

        closeCurrentMenuItem.setText("Close Current");
        jMenu1.add(closeCurrentMenuItem);

        closeOthersMenuItem.setText("Close Others");
        jMenu1.add(closeOthersMenuItem);

        closeAllMenuItem.setText("Close All");
        jMenu1.add(closeAllMenuItem);
        jMenu1.add(jSeparator4);

        dragModeMenuItem.setText("Drag outline");
        dragModeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/selection16.png"))); // NOI18N
        jMenu1.add(dragModeMenuItem);

        menuBar.add(jMenu1);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        debugMenuItem.setText("Debug");
        debugMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/debug_run16.png"))); // NOI18N
        helpMenu.add(debugMenuItem);
        helpMenu.add(jSeparator3);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/gui/main/resources/about16.png"))); // NOI18N
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About...");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void francaisMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_francaisMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_francaisMenuItemActionPerformed

    /**
     * Inicializa las opciones de menú 'Look And Feel'.
     */
    private void initLookAndFeelMenu() {
        // Creamos cada una de los elementos el menú LookAndFeel
        ButtonGroup group = new ButtonGroup();
        UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lista1 : lista) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lista1.getName());
            item.setName(lista1.getClassName());
            lookAndFeelMenu.add(item);
            group.add(item);
        }
    }
    
    /**
     * Traduce los elementos de la ventana.
     */
    private void translate() {
        LocaleManager lm = LocaleManager.getInstance();
        this.setTitle(lm.getText("TITLE"));
        this.fileMenu.setText(lm.getText("MENU_FILE"));
        this.customersMenuItem.setText(lm.getText("MENU_FILE_CUSTOMERS"));
        this.booksMenuItem.setText(lm.getText("MENU_FILE_BOOKS"));
        this.loansMenuItem.setText(lm.getText("MENU_FILE_LOANS"));
        this.exitMenuItem.setText(lm.getText("MENU_FILE_EXIT"));
        this.printMenu.setText(lm.getText("MENU_PRINT"));
        this.printCustomersMenuItem.setText(lm.getText("MENU_PRINT_CUSTOMERS"));
        this.printBooksMenuItem.setText(lm.getText("MENU_PRINT_BOOKS"));
        this.printLoansMenuItem.setText(lm.getText("MENU_PRINT_LOANS"));
        this.toolsMenu.setText(lm.getText("MENU_TOOLS"));
        this.languageMenu.setText(lm.getText("MENU_TOOLS_LANGUAGE"));
        this.spanishMenuItem.setText(lm.getText("MENU_TOOLS_LANGUAGE_SPANISH"));
        this.englishMenuItem.setText(lm.getText("MENU_TOOLS_LANGUAGE_ENGLISH"));
        this.francaisMenuItem.setText(lm.getText("MENU_TOOLS_LANGUAGE_FRENCH"));
        this.lookAndFeelMenu.setText(lm.getText("MENU_TOOLS_LF"));
        this.jMenu1.setText("Window");
        this.cascadeMenuItem.setText("Cascade");
        this.closeCurrentMenuItem.setText("Close Current");
        this.closeOthersMenuItem.setText("Close Others");
        this.closeAllMenuItem.setText("Close All");
        this.helpMenu.setText(lm.getText("MENU_HELP"));
        this.aboutMenuItem.setText(lm.getText("MENU_HELP_ABOUT"));
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a la opción de menú Socios.
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setSociosMenuItemListener(ActionListener l, String command) {
        this.customersMenuItem.addActionListener(l);
        this.customersMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a la opción de menú Libros.
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setLibrosMenuItemListener(ActionListener l, String command) {
         this.booksMenuItem.addActionListener(l);
         this.booksMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a la opción de menú Prestamos.
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setPrestamosMenuItemListener(ActionListener l, String command) {
        this.loansMenuItem.addActionListener(l);
        this.loansMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a la opción de menú Salir.
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setExitMenuItemListener(ActionListener l, String command) {
        this.exitMenuItem.addActionListener(l);
        this.exitMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a la opción de menú "Acerca de...".
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setAboutMenuItemListener(ActionListener l, String command) {
        this.aboutMenuItem.addActionListener(l);
        this.aboutMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a las opciones de menú de idioma.
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setLanguageMenuItemListener(ActionListener l, String command) {
        this.englishMenuItem.addActionListener(l);
        this.englishMenuItem.setActionCommand(command);
        this.spanishMenuItem.addActionListener(l);
        this.spanishMenuItem.setActionCommand(command);
        this.francaisMenuItem.addActionListener(l);
        this.francaisMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a la opción de menú de debug.
     * 
     * @param l
     * @param command 
     */
    public void setDebugMenuItemListener(ActionListener l, String command) {
        this.debugMenuItem.addActionListener(l);
        this.debugMenuItem.setActionCommand(command);
    }
    
    public void setCascadeMenuItemListener(ActionListener l, String command) {
        this.cascadeMenuItem.addActionListener(l);
        this.cascadeMenuItem.setActionCommand(command);
    }
    
    public void setTileMenuItemListener(ActionListener l, String command) {
        this.tileHMenuItem.addActionListener(l);
        this.tileHMenuItem.setActionCommand(command);
    }
    
    public void setDragModeMenuItemListener(ActionListener l, String command) {
        this.dragModeMenuItem.addActionListener(l);
        this.dragModeMenuItem.setActionCommand(command);
    }
    
    public void setCloseAllMenuItemListener(ActionListener l, String command) {
        this.closeAllMenuItem.addActionListener(l);
        this.closeAllMenuItem.setActionCommand(command);
    }
    
    public void setCloseOthersMenuItemListener(ActionListener l, String command) {
        this.closeOthersMenuItem.addActionListener(l);
        this.closeOthersMenuItem.setActionCommand(command);
    }
    
    public void setCloseCurrentMenuItemListener(ActionListener l, String command) {
        this.closeCurrentMenuItem.addActionListener(l);
        this.closeCurrentMenuItem.setActionCommand(command);
    }
    
    /**
     * Establece el ActionListener y el ActionCommand a las opciones de menú de Look And Feel.
     * 
     * @param l El ActionListener que se añadirá.
     * @param command La acción para esta opción de menú.
     */
    public void setLookAndFeelMenuItemListener(ActionListener l, String command) {
        for (int i = 0; i < lookAndFeelMenu.getItemCount(); i++) {
            JMenuItem item = lookAndFeelMenu.getItem(i);
            item.addActionListener(l);
            item.setActionCommand(command);
        }
    }
    
    /**
     * Devuelve una referencia al JDesktopPane.
     * 
     * @return 
     */
    public JDesktopPane getDesktopPane() {
        return this.desktopPane;
    }
    
    /**
     * Establece como seleccionado el tema visual 'Look And Feel' indicado.
     * 
     * @param lookAndFeel 
     */
    public void setLookAndFeel(String lookAndFeel) {
        if (lookAndFeel != null) {
            for (int i = 0; i < lookAndFeelMenu.getItemCount(); i++) {
                JMenuItem item = lookAndFeelMenu.getItem(i);
                if (lookAndFeel.equalsIgnoreCase(item.getName())) {
                    item.setSelected(true);
                }
            }
        }
    }
    
    /**
     * Activa/desactiva la opción de menú 'Debug'.
     * 
     * @param selected 
     */
    public void setDebugSelected(Boolean selected) {
        this.debugMenuItem.setSelected(selected);
    }
    
    /**
     * Cambia el idioma de la aplicación.
     * 
     * @param locale 
     */
    public void changeLanguage(Locale locale) {
        if (locale == null) {
            this.englishMenuItem.setSelected(true);
        } else {
            String lang = locale.getLanguage() + "_" + locale.getCountry();
            if (null != lang) switch (lang) {
                case "es_ES":
                    this.spanishMenuItem.setSelected(true);
                    break;
                case "en_US":
                    this.englishMenuItem.setSelected(true);
                    break;
                case "fr_FR":
                    this.francaisMenuItem.setSelected(true);
                    break;
                default:
                    this.englishMenuItem.setSelected(true);
                    break;
            }
        }
        translate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem booksMenuItem;
    private javax.swing.JMenuItem cascadeMenuItem;
    private javax.swing.JMenuItem closeAllMenuItem;
    private javax.swing.JMenuItem closeCurrentMenuItem;
    private javax.swing.JMenuItem closeOthersMenuItem;
    private javax.swing.JMenuItem customersMenuItem;
    private javax.swing.JCheckBoxMenuItem debugMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JCheckBoxMenuItem dragModeMenuItem;
    private javax.swing.JRadioButtonMenuItem englishMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JRadioButtonMenuItem francaisMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.ButtonGroup languageButtonGroup;
    private javax.swing.JMenu languageMenu;
    private javax.swing.JMenuItem loansMenuItem;
    private javax.swing.JMenu lookAndFeelMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem printBooksMenuItem;
    private javax.swing.JMenuItem printCustomersMenuItem;
    private javax.swing.JMenuItem printLoansMenuItem;
    private javax.swing.JMenu printMenu;
    private javax.swing.JRadioButtonMenuItem spanishMenuItem;
    private javax.swing.JMenuItem tileHMenuItem;
    private javax.swing.JMenu toolsMenu;
    // End of variables declaration//GEN-END:variables

}
