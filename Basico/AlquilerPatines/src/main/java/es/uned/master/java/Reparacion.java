package es.uned.master.java;

import java.time.LocalDate;

public class Reparacion {
    private LocalDate fechaReparacion = LocalDate.now();
    private double importe;

    public Reparacion(double importe) {
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return this.fechaReparacion;
    }

    public double getImporte() {
        return this.importe;
    }

    public String toString() {
        return "Reparación realizada el " + this.fechaReparacion + " por un importe de " + this.importe + "€";
    }
}
