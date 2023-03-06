import es.uned.master.java.ES;
import es.uned.master.java.Categoria;
import es.uned.master.java.Tienda;

public class Driver {
    static Tienda miTienda = new Tienda();

    public Driver() {
    }

    public static void main(String[] args) {
        ES.msgln("Gestión de Tienda\n\n");
        int opcion = 0;
        do {
            opcion = ES.leeEntero("***** Menú principal *****\n1. Comprar patinete\n2. Alquilar patinete\n3. Devolver patinete alquilado\n4. Reparar patinete\n5. Vender patinete\n6. Balance general de la tienda\n7. Balance de un patinete\n8. Historial de un patinete\n0. Fin", 0, 8);
            switch (opcion) {
                case 0:
                default:
                    break;
                case 1:
                    Comprar();
                    break;
                case 2:
                    Alquilar();
                    break;
                case 3:
                    Devolver();
                    break;
                case 4:
                    Reparar();
                    break;
                case 5:
                    Vender();
                    break;
                case 6:
                    BalanceGeneral();
                    break;
                case 7:
                    BalancePatinete();
                    break;
                case 8:
                    HistorialPatinete();
            }
        } while(opcion != 0);

    }

    static void Comprar() {
        int opcion = 0;

        do {
            opcion = ES.leeEntero("***** Submenú Comprar *****\n1. Categoría Básica\n2. Categoría Estándar\n3. Categoría Premium\n0. Salir Submenú Comprar", 0, 3);
            if (opcion == 0) {
                ES.msgln("Se ha anulado la compra\n");
            } else {
                Categoria cat = Categoria.values()[opcion - 1];
                int cuantos = ES.leeEntero("***** Submenú comprar patinete de categoría " + cat + " *****\n¿Cuántos quiere comporar (0 para anular)? ", 0);
                int compradosEfectivamente = miTienda.Comprar(cuantos, cat);
                ES.msgln("######     Se han comprado " + compradosEfectivamente + " de categoría " + cat);
                MostrarEstadoTienda();
            }
        } while(opcion != 0);

    }

    static void Alquilar() {
        int opcion = 0;

        do {
            opcion = ES.leeEntero("***** Submenú Alquilar *****\n1. Categoría Básica\n2. Categoría Estándar\n3. Categoría Premium\n4. Cualquier categoria\n0. Salir o Submenú Alquilar", 0, 4);
            if (opcion == 0) {
                ES.msgln("Se ha anulado el alquiler\n");
            } else {
                String patAlquilado;
                if (opcion == 4) {
                    try {
                        patAlquilado = miTienda.Alquilar();
                    } catch (Exception var4) {
                        patAlquilado = var4.getMessage();
                    }
                } else {
                    try {
                        Categoria cat = Categoria.values()[opcion - 1];
                        patAlquilado = miTienda.Alquilar(cat);
                    } catch (Exception var3) {
                        patAlquilado = var3.getMessage();
                    }
                }

                ES.msgln("######     Se ha alquilado: " + patAlquilado);
                MostrarEstadoTienda();
            }
        } while(opcion != 0);

    }

    static void Devolver() {
        String respuesta = "Se ha anulado la devolución";
        String codigo = ES.leeCadena("Introduzca el Número de Referencia del patinete a devolver: ");
        double kmRecorridos = ES.leeDoble("Introduzca el número de KMs que ha recorrido (0 para cancelar): ", true);
        if (kmRecorridos == 0.0) {
            ES.msgln(respuesta);
        } else if (kmRecorridos > 0.0) {
            respuesta = miTienda.Devolver(codigo, kmRecorridos);
            if (respuesta.equals(codigo)) {
                ES.msgln(respuesta + " ha sido devuelto");
            } else {
                ES.msgln(respuesta);
            }
        }

        MostrarEstadoTienda();
    }

    static void Reparar() {
        String respuesta = "Se ha anulado la reparación";
        String codigo = ES.leeCadena("Introduzca el Número de Referencia del patinete a reparar: ");
        double coste = ES.leeDoble("Introduzca el coste de la reparación (0 para cancelar): ", true);
        if (coste == 0.0) {
            ES.msgln(respuesta);
        } else if (coste > 0.0) {
            respuesta = miTienda.Reparar(codigo, coste);
            if (respuesta.equals(codigo)) {
                ES.msgln(respuesta + " ha sido reparado por " + coste + "€");
            } else {
                ES.msgln(respuesta);
            }
        }

        MostrarEstadoTienda();
    }

    static void Vender() {
        String respuesta = "Se ha anulado la venta";
        String codigo = ES.leeCadena("Introduzca el Número de Referencia del patinete a vender: ");

        try {
            double precio = miTienda.getPrecioVenta(codigo);
            String deAcuerdo = ES.leeCadena("¿Está de acuerdo en venderlo por " + precio + "€? (s/n): ");
            if (deAcuerdo.equalsIgnoreCase("s")) {
                miTienda.Vender(codigo);
                respuesta = codigo + " ha sido vendido por " + precio + "€";
            }
        } catch (Exception var5) {
            respuesta = respuesta + ". " + var5.getMessage();
        }

        ES.msgln(respuesta);
        MostrarEstadoTienda();
    }

    static void BalanceGeneral() {
        String respuesta = miTienda.balanceGeneral();
        MostrarMensajeConAlmohadillas(respuesta);
        MostrarEstadoTienda();
    }

    static void BalancePatinete() {
        String respuesta = "Se ha cancelado";
        String codigo = ES.leeCadena("Introduzca el Número de Referencia del patinete (0 para cancelar): ");
        if (!codigo.equals("0")) {
            respuesta = miTienda.balancePatinete(codigo);
        }

        MostrarMensajeConAlmohadillas(respuesta);
        MostrarEstadoTienda();
    }

    static void HistorialPatinete() {
        String respuesta = "Se ha cancelado";
        String codigo = ES.leeCadena("Introduzca el Número de Referencia del patinete (0 para cancelar): ");
        if (!codigo.equals("0")) {
            respuesta = miTienda.historialPatinete(codigo);
        }

        MostrarMensajeConAlmohadillas(respuesta);
        MostrarEstadoTienda();
    }

    private static void MostrarEstadoTienda() {
        ES.msgln("######     Estado de la tienda      ######");
        ES.msgln(miTienda.toString());
        ES.msgln("######     Fin estado de la tienda  ######");
    }

    private static void MostrarMensajeConAlmohadillas(String mensaje) {
        ES.msgln("#####################################################");
        ES.msgln(mensaje);
        ES.msgln("#####################################################");
    }
}
