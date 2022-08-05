/**
 * Biblioteca v1.0 - Aplicación para la gestión básica de una biblioteca.
 *  - Alta, baja y modificación de socios.
 *  - Alta, baja y modificación de libros.
 *  - Alta, baja y modificación de préstamos.
 *  - Repositorio en base de datos (MySQL).
 *  - Con interfaz gráfico de usuario.
 * 
 * @author Antonio Dorado
 * @version 1.2 - 08/01/2016
 */

import com.csc.biblioteca.mdi.MDIControlador;
import javax.swing.*;
import javax.swing.UIManager.*;

public class Principal {

    /**
     * Método de inicio de la aplicación.
     * @param args Argumentos de entrada de la línea de comandos.
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("> Error al establecer el look & feel de la aplciación.");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MDIControlador();
            }
        });
    }
}
