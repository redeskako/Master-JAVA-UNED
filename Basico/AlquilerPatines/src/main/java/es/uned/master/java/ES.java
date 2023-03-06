//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package es.uned.master.java;

import java.util.Scanner;

public class ES {
    public ES() {
    }

    public static int leeEntero() {
        boolean leido = false;
        int numero = 0;
        Scanner teclado = null;

        do {
            try {
                teclado = new Scanner(System.in);
                numero = teclado.nextInt();
                leido = true;
            } catch (Exception var4) {
                msgln("Error: No es un número entero válido. ");
            }
        } while(!leido);

        return numero;
    }

    public static int leeEntero(String mensaje) {
        int numero = 0;
        boolean leido = false;
        Scanner teclado = null;

        do {
            msgln(mensaje);

            try {
                teclado = new Scanner(System.in);
                numero = teclado.nextInt();
                leido = true;
            } catch (Exception var5) {
                msgln("Error: No es un número entero válido. ");
            }
        } while(!leido);

        return numero;
    }

    public static int leeEntero(int minimo) {
        int numero = 0;
        boolean leido = false;
        Scanner teclado = null;

        do {
            try {
                teclado = new Scanner(System.in);
                numero = teclado.nextInt();
                if (numero >= minimo) {
                    leido = true;
                } else {
                    msgln("Error: Debe ser un número entero mayor o igual que " + minimo + ". ");
                }
            } catch (Exception var5) {
                msgln("Error: No es un número entero válido. ");
            }
        } while(!leido);

        return numero;
    }

    public static int leeEntero(String mensaje, int minimo) {
        int numero = 0;
        boolean leido = false;
        Scanner teclado = null;

        do {
            msgln(mensaje);

            try {
                teclado = new Scanner(System.in);
                numero = teclado.nextInt();
                if (numero >= minimo) {
                    leido = true;
                } else {
                    msgln("Error: Debe ser un número entero mayor o igual que " + minimo + ".");
                }
            } catch (Exception var6) {
                msgln("Error: No es un número entero válido. ");
            }
        } while(!leido);

        return numero;
    }

    public static int leeEntero(int minimo, int maximo) throws IllegalArgumentException {
        int numero = 0;
        boolean leido = false;
        Scanner teclado = null;
        if (minimo > maximo) {
            throw new IllegalArgumentException("Rango imposible. El valor mínimo no puede ser mayor que el valor máximo");
        } else {
            do {
                try {
                    teclado = new Scanner(System.in);
                    numero = teclado.nextInt();
                    if (numero >= minimo && numero <= maximo) {
                        leido = true;
                    } else {
                        msgln("Error: Debe ser un número entero mayor o igual que " + minimo + " y menor o igual que " + maximo + ". ");
                    }
                } catch (Exception var6) {
                    msgln("Error: No es un número entero válido. ");
                }
            } while(!leido);

            return numero;
        }
    }

    public static int leeEntero(String mensaje, int minimo, int maximo) throws IllegalArgumentException {
        int numero = 0;
        boolean leido = false;
        Scanner teclado = null;
        if (minimo > maximo) {
            throw new IllegalArgumentException("Rango imposible. El valor mínimo no puede ser mayor que el valor máximo");
        } else {
            do {
                msgln(mensaje);

                try {
                    teclado = new Scanner(System.in);
                    numero = teclado.nextInt();
                    if (numero >= minimo && numero <= maximo) {
                        leido = true;
                    } else {
                        msgln("Error: Debe ser un número entero mayor o igual que " + minimo + " y menor o igual que " + maximo + ". ");
                    }
                } catch (Exception var7) {
                    msgln("Error: No es un número entero válido. ");
                }
            } while(!leido);

            return numero;
        }
    }

    public static double leeDoble(String mensaje, boolean positivo) {
        double numero = 0.0;
        boolean leido = false;
        Scanner teclado = null;

        do {
            msgln(mensaje);

            try {
                teclado = new Scanner(System.in);
                numero = teclado.nextDouble();
                if (numero >= 0.0 == positivo) {
                    leido = true;
                } else {
                    msgln("Error: debe ser positivo. ");
                }
            } catch (Exception var7) {
                msgln("Error: No es un número doble válido. ");
            }
        } while(!leido);

        return numero;
    }

    public static String leeCadena() {
        Scanner teclado = new Scanner(System.in);
        String cadena = "";

        try {
            cadena = teclado.nextLine();
        } catch (Exception var3) {
            msgln("Error: Ha fallado la entrada de datos.");
        }

        return cadena;
    }

    public static String leeCadena(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        String cadena = "";

        try {
            msgln(mensaje);
            cadena = teclado.nextLine();
        } catch (Exception var4) {
            msgln("Error: Ha fallado la entrada de datos.");
        }

        return cadena;
    }

    public static void msg(int entero) {
        System.out.print(entero);
    }

    public static void msg(long enteroLargo) {
        System.out.print(enteroLargo);
    }

    public static void msg(float real) {
        System.out.print(real);
    }

    public static void msg(double realLargo) {
        System.out.print(realLargo);
    }

    public static void msg(String cadenaAImprimir) {
        System.out.print(cadenaAImprimir);
    }

    public static void msgln() {
        System.out.println();
    }

    public static void msgln(int entero) {
        System.out.println(entero);
    }

    public static void msgln(long enteroLargo) {
        System.out.println(enteroLargo);
    }

    public static void msgln(float real) {
        System.out.println(real);
    }

    public static void msgln(double realLargo) {
        System.out.println(realLargo);
    }

    public static void msgln(String cadenaAImprimir) {
        System.out.println(cadenaAImprimir);
    }

    public static String leeRespuesta(String mensaje) {
        boolean correcta = false;
        String cadena = "";
        Scanner teclado = null;

        do {
            msgln(mensaje);

            try {
                teclado = new Scanner(System.in);
                cadena = teclado.nextLine();
                if (cadena == null || cadena.length() != 1 || !cadena.equalsIgnoreCase("S") && !cadena.equalsIgnoreCase("N")) {
                    msgln("Error: Solo se admite como respuesta un Ãºnico carÃ¡cter, que debe ser 's', 'S', 'n' o 'N'.");
                } else {
                    correcta = true;
                }
            } catch (Exception var5) {
                msgln("Error: Ha fallado la entrada de datos.");
            }
        } while(!correcta);

        return cadena.toUpperCase();
    }
}
