package es.uned.master.java;

import java.util.Arrays;
import es.uned.master.java.Patinete.Estado;

public class Tienda {
    private Patinete[] patinetes;

    public Tienda() {
    }

    public int getNumeroPatinetes() {
        int nPatinetes;
        try {
            nPatinetes = this.patinetes.length;
        } catch (NullPointerException var3) {
            nPatinetes = 0;
        }

        return nPatinetes;
    }

    public int Comprar(int nPatinetes) {
        return this.Comprar(nPatinetes, Categoria.BASICA);
    }

    public int Comprar(int cuantos, Categoria categoria) {
        int comprados = 0;
        if (cuantos > 0) {
            int nPatinetesHay = this.getNumeroPatinetes();

            try {
                for(int i = nPatinetesHay; i < nPatinetesHay + cuantos; ++i) {
                    Patinete patAux = new Patinete(categoria);
                    ++comprados;
                    if (i == 0) {
                        this.patinetes = new Patinete[1];
                        this.patinetes[i] = patAux;
                    } else {
                        Patinete[] patinetesAux = (Patinete[])Arrays.copyOf(this.patinetes, this.patinetes.length + 1);
                        patinetesAux[i] = patAux;
                        this.patinetes = patinetesAux;
                    }
                }
            } catch (Exception var8) {
            }
        }

        return comprados;
    }

    public String Alquilar() throws Exception {
        boolean encontrado = false;
        String patineteAlquilado = "";

        for(int i = 0; i < Categoria.values().length; ++i) {
            try {
                patineteAlquilado = this.Alquilar(Categoria.values()[i]);
                i = Categoria.values().length;
                encontrado = true;
            } catch (Exception var5) {
            }
        }

        if (!encontrado) {
            throw new Exception("No hay patinetes disponibles para alquilar de ninguna categoría");
        } else {
            return patineteAlquilado;
        }
    }

    public String Alquilar(Categoria categoria) throws Exception {
        boolean encontrado = false;
        String patineteAlquilado = "";

        for(int i = 0; i < this.getNumeroPatinetes(); ++i) {
            if (this.patinetes[i].getEstado() == Estado.DISPONIBLE && this.patinetes[i].getCategoria() == categoria) {
                this.patinetes[i].Alquilar();
                patineteAlquilado = this.patinetes[i].getNumeroDeReferencia();
                i = this.getNumeroPatinetes();
                encontrado = true;
            }
        }

        if (!encontrado) {
            throw new Exception("No hay patinetes disponibles para alquilar de la categoria " + categoria.toString());
        } else {
            return patineteAlquilado;
        }
    }

    public String Reparar(String codigo, double precio) {
        String respuesta;
        try {
            int indice = this.IndicePatinete(codigo);
            this.patinetes[indice].Reparar(precio);
            respuesta = codigo;
        } catch (Exception var6) {
            respuesta = var6.getMessage();
        }

        return respuesta;
    }

    public String Devolver(String codigo, double kmRecorridos) {
        String respuesta;
        try {
            int indice = this.IndicePatinete(codigo);
            this.patinetes[indice].Devolver(kmRecorridos);
            respuesta = codigo;
        } catch (Exception var6) {
            respuesta = var6.getMessage();
        }

        return respuesta;
    }

    public double Vender(String codigo) throws Exception {
        double precioVendido = 0.0;
        int indice = this.IndicePatinete(codigo);
        precioVendido = this.patinetes[indice].Vender();
        return precioVendido;
    }

    public double getPrecioVenta(String codigo) throws Exception {
        double precioVenta = 0.0;
        int indice = this.IndicePatinete(codigo);
        precioVenta = this.patinetes[indice].CalcularPrecioVenta();
        return precioVenta;
    }

    private int IndicePatinete(String codigo) throws Exception {
        int indice = -1;
        int nPatinetes = this.getNumeroPatinetes();

        for(int i = 0; i < nPatinetes; ++i) {
            if (this.patinetes[i].getNumeroDeReferencia().equals(codigo)) {
                indice = i;
                i = nPatinetes;
            }
        }

        if (indice > -1) {
            return indice;
        } else {
            throw new Exception("El patinete " + codigo + " no existe");
        }
    }

    private double gastosTotalesPorCompras() {
        double gastos = 0.0;
        if (this.patinetes != null) {
            Patinete[] var3 = this.patinetes;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Patinete pat = var3[var5];
                gastos += pat.getCategoria().getPrecioCoste();
            }
        }

        return gastos;
    }

    private double ingresosTotalesPorVentas() {
        double ingresos = 0.0;
        if (this.patinetes != null) {
            Patinete[] var3 = this.patinetes;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Patinete pat = var3[var5];
                ingresos += pat.getPrecioVendido();
            }
        }

        return ingresos;
    }

    private double ingresosTotalesPorAlquileres() {
        double ingresos = 0.0;
        if (this.patinetes != null) {
            Patinete[] var3 = this.patinetes;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Patinete pat = var3[var5];
                ingresos += pat.acumuladoAlquileres();
            }
        }

        return ingresos;
    }

    private double gastosTotalesPorReparaciones() {
        double gastos = 0.0;
        if (this.patinetes != null) {
            Patinete[] var3 = this.patinetes;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Patinete pat = var3[var5];
                gastos += pat.acumuladoReparaciones();
            }
        }

        return gastos;
    }

    public String balancePatinete(String codigo) {
        String balance;
        try {
            int indice = this.IndicePatinete(codigo);
            double compra = this.patinetes[indice].getCategoria().getPrecioCoste();
            double reparaciones = this.patinetes[indice].acumuladoReparaciones();
            double alquileres = this.patinetes[indice].acumuladoAlquileres();
            double venta = this.patinetes[indice].getPrecioVendido();
            balance = "Balance del patinete " + this.patinetes[indice].getNumeroDeReferencia() + ":\n     Gastos en compra:       " + compra + "\n     Gastos en reparaciones:  " + reparaciones + "\n     Ingresos por alquileres: " + alquileres + "\n     Ingresos por venta:     " + venta + "\n     Total balance:           " + (venta + alquileres - compra - reparaciones);
        } catch (Exception var12) {
            balance = var12.getMessage();
        }

        return balance;
    }

    public String balanceGeneral() {
        double compras = this.gastosTotalesPorCompras();
        double reparaciones = this.gastosTotalesPorReparaciones();
        double alquileres = this.ingresosTotalesPorAlquileres();
        double ventas = this.ingresosTotalesPorVentas();
        String balance = "Balance general:\n     Gastos en compras:       " + compras + "\n     Gastos en reparaciones:  " + reparaciones + "\n     Ingresos por alquileres: " + alquileres + "\n     Ingresos por ventas:     " + ventas + "\n     Total balance:           " + (ventas + alquileres - compras - reparaciones);
        return balance;
    }

    public String historialPatinete(String codigo) {
        String historial;
        try {
            int indice = this.IndicePatinete(codigo);
            String var10000 = this.patinetes[indice].getNumeroDeReferencia();
            historial = "Historial del patinete " + var10000 + ":\n" + this.patinetes[indice].historial() + "\n" + this.balancePatinete(codigo);
        } catch (Exception var4) {
            historial = var4.getMessage();
        }

        return historial;
    }

    public String toString() {
        String toStr = "Tienda Vacia";
        int nPatinetes = this.getNumeroPatinetes();

        for(int i = 0; i < nPatinetes; ++i) {
            if (i == 0) {
                toStr = this.patinetes[i].toString();
            } else {
                toStr = toStr + "\n" + this.patinetes[i].toString();
            }
        }

        return toStr;
    }
}
