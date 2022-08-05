package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.customers.dao.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class CustomersDataSource implements JRDataSource {

    private List<CustomerDTO> customers;
    private int index = -1;

    public CustomersDataSource() {
        this.customers = new ArrayList<>();
    }

    public CustomersDataSource(List<CustomerDTO> customers) {
        this.customers = new ArrayList<>(customers);
    }
    
    public void addCustomer(CustomerDTO customer) {
        this.customers.add(customer);
    }
    
    @Override
    public boolean next() throws JRException {
        this.index = this.index + 1;
        return this.index < customers.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        String fieldName = jrf.getName();
        switch (fieldName) {
            case "idSocio":
                valor = this.customers.get(this.index).getIdSocio();
                break;
            case "dni":
                valor = this.customers.get(this.index).getDni();
                break;
            case "nombre":
                valor = this.customers.get(this.index).getNombre();
                break;
            case "apellidos":
                valor = this.customers.get(this.index).getApellidos();
                break;
            case "direccion":
                valor = this.customers.get(this.index).getDireccion();
                break;
            case "fechaAlta":
                valor = this.customers.get(this.index).getFechaAlta();
                break;
        }
        return valor;
    }
    
}
