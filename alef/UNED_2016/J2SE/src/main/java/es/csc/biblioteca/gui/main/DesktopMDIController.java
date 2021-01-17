package es.csc.biblioteca.gui.main;

import es.csc.biblioteca.books.dao.BooksDAOException;
import es.csc.biblioteca.books.gui.BooksManagementController;
import es.csc.biblioteca.jdbc.ConnectionsManager;
import es.csc.biblioteca.customers.gui.CustomersManagementController;
import es.csc.biblioteca.customers.dao.CustomersDAOException;
import es.csc.biblioteca.i18n.LocaleManager;
import es.csc.biblioteca.log.LoggerFactory;
import es.csc.biblioteca.loans.dao.LoansDAOException;
import es.csc.biblioteca.loans.gui.LoansManagementController;
import es.csc.biblioteca.util.Config;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class DesktopMDIController implements ActionListener, WindowListener, Observer {

    private final DesktopMDI view;
    private final Connection connnection;

    private int nextFrameX;
    private int nextFrameY;
    private int frameDistance = 0;
    
    protected static final Config config = Config.getInstance();
    protected static final Logger logger = Logger.getLogger(DesktopMDIController.class);
    
    private static final String ACTION_SOCIOS = "Socios";
    private static final String ACTION_LIBROS = "Libros";
    private static final String ACTION_PRESTAMOS = "Prestamos";
    private static final String ACTION_EXIT = "Exit";
    private static final String ACTION_ABOUT = "About";
    private static final String ACTION_LANGUAGE = "Language";
    private static final String ACTION_LOOKANDFEEL = "Look&Feel";
    private static final String ACTION_DEBUG = "Debug";
    private static final String ACTION_CASCADE_WINDOWS = "Cascade";
    private static final String ACTION_TILE_WINDOWS = "Tile";
    private static final String ACTION_DRAGMODE = "DragOutline";
    private static final String ACTION_CLOSE_ALL = "CloseAll";
    private static final String ACTION_CLOSE_CURRENT = "CloseCurrent";
    private static final String ACTION_CLOSE_OTHERS = "CloseOthers";
    
    private static final String SUFFIX_DEBUG = " - [DEBUG ON]";
    
    public DesktopMDIController() throws SQLException {
        this.connnection = ConnectionsManager.getConnection();
        this.view = new DesktopMDI();
        initDebug();
        addListeners();
        setDefaultLookAndFeel();
        this.view.changeLanguage(LocaleManager.getInstance().getLocale());
        this.view.setLocationRelativeTo(null);
        this.view.setExtendedState(this.view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.view.setVisible(true);
        LocaleManager.getInstance().addObserver(this);
    }
    
    private void addListeners() {
        this.view.setSociosMenuItemListener(this, ACTION_SOCIOS);
        this.view.setLibrosMenuItemListener(this, ACTION_LIBROS);
        this.view.setPrestamosMenuItemListener(this, ACTION_PRESTAMOS);
        this.view.setExitMenuItemListener(this, ACTION_EXIT);
        this.view.setAboutMenuItemListener(this, ACTION_ABOUT);
        this.view.setLanguageMenuItemListener(this, ACTION_LANGUAGE);
        this.view.setLookAndFeelMenuItemListener(this, ACTION_LOOKANDFEEL);
        this.view.setDebugMenuItemListener(this, ACTION_DEBUG);
        this.view.setCascadeMenuItemListener(this, ACTION_CASCADE_WINDOWS);
        this.view.setTileMenuItemListener(this, ACTION_TILE_WINDOWS);
        this.view.setDragModeMenuItemListener(this, ACTION_DRAGMODE);
        this.view.setCloseAllMenuItemListener(this, ACTION_CLOSE_ALL);
        this.view.setCloseOthersMenuItemListener(this, ACTION_CLOSE_OTHERS);
        this.view.setCloseCurrentMenuItemListener(this, ACTION_CLOSE_CURRENT);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_SOCIOS:
                openCustomersManagement();
                break;
            case ACTION_LIBROS:
                openBooksManagement();
                break;
            case ACTION_PRESTAMOS:
                openRentalsManagement();
                break;
            case ACTION_ABOUT:
                About about = new About(this.view);
                break;
            case ACTION_LANGUAGE:
                String language = ((JMenuItem) e.getSource()).getName();
                changeLanguage(language);
                break;
            case ACTION_LOOKANDFEEL:
                String lookAndFeel = ((JMenuItem) e.getSource()).getName();
                changelookAndFeel(lookAndFeel);
                break;
            case ACTION_DEBUG:
                Boolean isDebugSelected = ((JCheckBoxMenuItem) e.getSource()).isSelected();
                setDebugSelected(isDebugSelected);
                break;
            case ACTION_CASCADE_WINDOWS:
                cascadeWindows();
                break;
            case ACTION_TILE_WINDOWS:
                tileWindows();
                break;
            case ACTION_DRAGMODE:
                Boolean isDragModeSelected = ((JCheckBoxMenuItem) e.getSource()).isSelected();
                setDragMode(isDragModeSelected);
                break;
            case ACTION_CLOSE_ALL:
                closeAllWindows();
                break;
            case ACTION_CLOSE_CURRENT:
                closeCurrentWindow();
                break;
            case ACTION_CLOSE_OTHERS:
                closeOthersWindows();
                break;
            case ACTION_EXIT:
                System.exit(0);
                break;
        }
    }

    private void openCustomersManagement() {
        try {
            CustomersManagementController c = new CustomersManagementController(connnection);
            addJInternalFrame(c.getView());
        } catch (CustomersDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    private void openBooksManagement() {
        try {
            BooksManagementController c = new BooksManagementController(connnection);
            addJInternalFrame(c.getView());
        } catch (BooksDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    private void openRentalsManagement() {
        try {
            LoansManagementController c = new LoansManagementController(connnection);
            addJInternalFrame(c.getView());
        } catch (LoansDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    private void addJInternalFrame(JInternalFrame frame) {
        JDesktopPane desktopPane = this.view.getDesktopPane();
        
        // Añadimos el JInternalFrame al JDesktopPane y lo seleccionamos
        desktopPane.add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            logger.error(ex.toString());
        }
        
        // Posicionamos el JInternalFrame
        int width = desktopPane.getWidth() / 2;
        int height = desktopPane.getHeight() / 2;
        frame.reshape(this.nextFrameX, this.nextFrameY, width, height);
      
        if (this.frameDistance == 0) {
            this.frameDistance = frame.getHeight() - frame.getContentPane().getHeight();
        }

        // calculamos la posición del siguiente JInternalFrame
        this.nextFrameX += this.frameDistance;
        this.nextFrameY += this.frameDistance;
        if (nextFrameX + width > desktopPane.getWidth()) {
            nextFrameX = 0;
        }
        if (nextFrameY + height > desktopPane.getHeight()) {
            nextFrameY = 0;
        }
    }
    
    /**
     * Cambia el aspecto 'Look And Feel' de la aplicación.
     * 
     * @param lookAndFeel 
     */
    private void changelookAndFeel(String lookAndFeel) {
        try {
            // Aplicamos el LookAndFeel seleccionado
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this.view);
            // Guardamos en el fichero properties el LookAndFeel seleccionado
            config.setProperty("lookandfeel", lookAndFeel);
            config.save();
        } catch (Exception ex) {
        }
    }
    
    /**
     * Establece el aspecto 'Look And Feel' por defecto de la aplicación.
     */
    private void setDefaultLookAndFeel() {
        // Obtenemos el LookAndFeel actual desde el fichero properties,
        // si no está establecido usamos el establecido en el sistema
        String lookAndFeel = config.getProperty("lookandfeel");
        if (lookAndFeel == null) {
            lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        }
        this.view.setLookAndFeel(lookAndFeel);
    }
    
    /**
     * Cambia el idioma a usar en la aplicación.
     * 
     * @param language 
     */
    private void changeLanguage(String language) {
        switch (language) {
            case "es_ES":
                LocaleManager.getInstance().setLocale(new Locale("es", "ES"));
                break;
            case "en_US":
                LocaleManager.getInstance().setLocale(new Locale("en", "US"));
                break;
            default:
                LocaleManager.getInstance().setLocale(new Locale("en", "US"));
                break;
        }
        config.setProperty("language", language);
        config.save();
    }
    
    /**
     * Inicializa el log de la aplicación.
     */
    private void initDebug() {
        // Establecemos el aspecto del menú debug
        if (Logger.getRootLogger().getLevel().equals(Level.OFF)) {
            this.view.setDebugSelected(false);
        } else {
            this.view.setDebugSelected(true);
            this.view.setTitle(LocaleManager.getInstance().getText("TITLE") + SUFFIX_DEBUG);
        }
    }
    
    /**
     * Activa/desactiva el log de la aplicación.
     * 
     * @param selected 
     */
    private void setDebugSelected(Boolean selected) {
        if (selected) {
            LoggerFactory.activate();
            this.view.setTitle(LocaleManager.getInstance().getText("TITLE") + SUFFIX_DEBUG);
            if (logger.isDebugEnabled()) {
                logger.debug("Debug activated");
                logger.debug("jMoodleExport v1.0.0 build 003");
                logger.debug(System.getProperties().toString());
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Debug deactivated");
            }
            LoggerFactory.deactivate();
            this.view.setTitle(LocaleManager.getInstance().getText("TITLE"));
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocaleManager) {
            this.view.changeLanguage(LocaleManager.getInstance().getLocale());
        }
    }
    
    
    public void cascadeWindows() {  
        JInternalFrame[] frames;
        frames = this.view.getDesktopPane().getAllFrames();
          int x = 0;
          int y = 0;
          int width = this.view.getWidth() / 2;
          int height = this.view.getHeight() / 2;

          for (int i = 0; i < frames.length; i++) {
              if (!frames[i].isIcon()) {
                  try {
                      /* try to make maximized frames resizable
                      this might be vetoed
                      */

                      frames[i].setMaximum(false);
                      frames[i].reshape(x, y, width, height);

                      x += frameDistance;
                      y += frameDistance;

                      // wrap around at the desktop edge
                      if (x + width > this.view.getWidth()) x = 0;
                      if (y + height > this.view.getHeight()) y = 0;
                    frames[i].setSelected(true);
                  } catch(PropertyVetoException e) {}
              } else {
              }

        }

    }
    
    
    public void tileWindows() {
        JInternalFrame[] frames = this.view.getDesktopPane().getAllFrames();
        if (frames.length == 0) {
            return;
        }
        
        // count frames that aren't iconized
        int frameCount = 0;
        for (int i = 0; i < frames.length; i++) {
            if (!frames[i].isIcon())
                frameCount++;
        }

        int rows = (int)Math.sqrt(frameCount);
        int cols = frameCount / rows;
        int extra = frameCount % rows;

        // number of columns with an extra row
        int width = this.view.getWidth() / cols;
        int height = this.view.getHeight() / rows;

        int r = 0;
        int c = 0;

        for (int i = 0; i < frames.length; i++) {
            if (!frames[i].isIcon()) {
                try {
                    frames[i].setMaximum(false);
                    frames[i].reshape(c * width, r * height, width, height);
                    r++;
                    if (r == rows) {
                        r = 0;
                        c++;
                        if (c == cols - extra) {
                            // start adding an extra row
                            rows++;
                            height = this.view.getHeight() / rows;
                        }
                    }

                } catch(PropertyVetoException e) {}
            }
        }
    }
    
    private void setDragMode(Boolean isSelected) {
        this.view.getDesktopPane().putClientProperty("JDesktopPane.dragMode", isSelected ? "outline" : null);
    }
    
    private void closeAllWindows() {
        JInternalFrame[] frames = this.view.getDesktopPane().getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            frames[i].dispose();
        }
    }
    
    private void closeCurrentWindow() {
        JInternalFrame[] frames = this.view.getDesktopPane().getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].isSelected()) {
                frames[i].dispose();
            }
        }
    }
    
    private void closeOthersWindows() {
        JInternalFrame[] frames = this.view.getDesktopPane().getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            if (!frames[i].isSelected()) {
                frames[i].dispose();
            }
        }
    }
    
}
