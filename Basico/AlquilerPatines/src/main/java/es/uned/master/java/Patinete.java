package es.uned.master.java;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Patinete {
    private NumeroDeReferencia nRef;
    private Categoria categoria;
    private Estado estado;
    private LocalDate fechaAdquisicion;
    private Alquiler[] alquileres;
    private Reparacion[] reparaciones;
    private double kmAcumulados;
    private double precioVendido;
    private LocalDate fechaVenta;

    public Patinete() throws Exception {
        this(Categoria.BASICA);
    }

    public Patinete(Categoria categoria) throws Exception {
        this.nRef = new NumeroDeReferencia(categoria);
        this.categoria = categoria;
        this.estado = Patinete.Estado.DISPONIBLE;
        this.kmAcumulados = 0.0;
        this.fechaAdquisicion = LocalDate.now();
        this.precioVendido = 0.0;
    }

    public String getNumeroDeReferencia() {
        return this.nRef.toString();
    }

    public Estado getEstado() {
        return this.estado;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public double getPrecioVendido() {
        return this.precioVendido;
    }

    public double acumuladoAlquileres() {
        double acumulado = 0.0;

        try {
            Alquiler[] var3 = this.alquileres;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Alquiler alq = var3[var5];
                acumulado += alq.getPrecioAlquiler();
            }
        } catch (NullPointerException var7) {
        }

        return acumulado;
    }

    public double acumuladoReparaciones() {
        double acumulado = 0.0;

        try {
            Reparacion[] var3 = this.reparaciones;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Reparacion rep = var3[var5];
                acumulado += rep.getImporte();
            }
        } catch (NullPointerException var7) {
        }

        return acumulado;
    }

    public void Alquilar() throws Exception {
        if (this.estado == Patinete.Estado.DISPONIBLE) {
            Alquiler[] auxiliar;
            if (this.alquileres == null) {
                auxiliar = new Alquiler[1];
            } else {
                auxiliar = (Alquiler[])Arrays.copyOf(this.alquileres, this.alquileres.length + 1);
            }

            Alquiler nuevoAlquiler = new Alquiler(this.categoria);
            auxiliar[auxiliar.length - 1] = nuevoAlquiler;
            this.estado = Patinete.Estado.ALQUILADO;
            this.alquileres = auxiliar;
        } else {
            NumeroDeReferencia var10002 = this.nRef;
            throw new Exception("El patinete " + var10002 + " no se puede alquilar por estar " + this.getEstado());
        }
    }

    public void Devolver(double kmRecorridos) throws Exception {
        if (this.estado == Patinete.Estado.ALQUILADO) {
            this.estado = Patinete.Estado.DISPONIBLE;
            this.kmAcumulados += kmRecorridos;
            this.alquileres[this.alquileres.length - 1].Finalizar();
        } else {
            throw new Exception("No se puede devolver un patinete que está " + this.estado);
        }
    }

    public double Vender() throws Exception {
        if (this.estado == Patinete.Estado.DISPONIBLE) {
            this.estado = Patinete.Estado.VENDIDO;
            this.precioVendido = this.CalcularPrecioVenta();
            this.fechaVenta = LocalDate.now();
            return this.precioVendido;
        } else {
            throw new Exception("No se puede vender un patinete que está " + this.estado);
        }
    }

    public void Reparar(double importe) throws Exception {
        if (this.estado == Patinete.Estado.DISPONIBLE) {
            Reparacion[] auxiliar;
            if (this.reparaciones == null) {
                auxiliar = new Reparacion[1];
            } else {
                auxiliar = (Reparacion[])Arrays.copyOf(this.reparaciones, this.reparaciones.length + 1);
            }

            Reparacion nuevaReparacion = new Reparacion(importe);
            auxiliar[auxiliar.length - 1] = nuevaReparacion;
            this.reparaciones = auxiliar;
        } else {
            throw new Exception("No se puede reparar un patinete que está " + this.estado);
        }
    }

    public double CalcularPrecioVenta() {
        double pvp = this.categoria.getPrecioCoste();
        int mesesAntiguedad = Period.between(this.fechaAdquisicion, LocalDate.now()).getMonths();
        double valorReparaciones = 0.0;
        if (this.reparaciones != null) {
            Reparacion[] var6 = this.reparaciones;
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Reparacion rep = var6[var8];
                double valorReparacion = rep.getImporte() - (double)Period.between(rep.getFecha(), LocalDate.now()).getDays() * 0.2;
                if (valorReparacion > 0.0) {
                    valorReparaciones += valorReparacion;
                }
            }
        }

        pvp = pvp - (double)(mesesAntiguedad * 10) + valorReparaciones - this.kmAcumulados * 0.02;
        if (pvp < (double)this.categoria.getPVPMinimo()) {
            pvp = (double)this.categoria.getPVPMinimo();
        }

        return pvp;
    }

    public String historial() {
        StringBuilder movimientos = new StringBuilder("");
        int nAlquileres = 0;
        int nReparaciones = 0;
        int nVentas = this.estado == Patinete.Estado.VENDIDO ? 1 : 0;


        try {
            nAlquileres = this.alquileres.length;
        } catch (NullPointerException var11) {
            nAlquileres = 0;
        }

        try {
            nReparaciones = this.reparaciones.length;
        } catch (NullPointerException var10) {
            nReparaciones = 0;
        }

        String[] operaciones = new String[nAlquileres + nReparaciones + nVentas + 1];
        LocalDate var10002 = this.fechaAdquisicion;
        operaciones[0] = "" + var10002 + " Adquirido por " + this.getCategoria().getPrecioCoste();
        int indiceReparacion;
        int i;
        if (nAlquileres > 0) {
            indiceReparacion = 0;

            for(i = 1; i <= nAlquileres; ++i) {
                var10002 = this.alquileres[indiceReparacion].getFechaInicio();
                operaciones[i] = "" + var10002 + " Alquilado por " + this.alquileres[indiceReparacion].getPrecioAlquiler();
                ++indiceReparacion;
            }
        }

        if (nReparaciones > 0) {
            indiceReparacion = 0;

            for(i = 1 + nAlquileres; i <= nAlquileres + nReparaciones; ++i) {
                var10002 = this.reparaciones[indiceReparacion].getFecha();
                operaciones[i] = "" + var10002 + " Reparado por " + this.reparaciones[indiceReparacion].getImporte();
                ++indiceReparacion;
            }
        }

        if (nVentas > 0) {
            int var10001 = nAlquileres + nReparaciones + nVentas;
            var10002 = this.fechaVenta;
            operaciones[var10001] = "" + var10002 + " Vendido por " + this.getPrecioVendido();
        }

        Arrays.sort(operaciones);
        String[] var14 = operaciones;
        i = operaciones.length;

        for(int var8 = 0; var8 < i; ++var8) {
            String operacion = var14[var8];
            movimientos.append(operacion).append("\n");
        }

        return movimientos.toString().substring(0, movimientos.length() - 2);
    }

    public String toString() {
        return this.nRef + " " + this.estado + " Adquirido: " + this.fechaAdquisicion + " KM Recorridos: " + this.kmAcumulados;
    }

    public static enum Estado {
        DISPONIBLE,
        ALQUILADO,
        VENDIDO;

        private Estado() {
        }
    }
}
