package es.uned.master.java.grajeGUI.capaVista;

public class MyCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column ==0 ){
        	cellComponent.setBackground(java.awt.Color.LIGHT_GRAY);		
        } else if ((column >0 ) && (column <=4)){
        	cellComponent.setBackground(java.awt.Color.CYAN);	
        } else if ((column>4 ) && (column <=8 )){
        	cellComponent.setBackground(java.awt.Color.RED);	
        } else if ((column >8)&& (column <=12 )) {
        	cellComponent.setBackground(java.awt.Color.GREEN);
        }else if (column >12){
        	cellComponent.setBackground(java.awt.Color.YELLOW);
        }        
        return cellComponent;
    }
}