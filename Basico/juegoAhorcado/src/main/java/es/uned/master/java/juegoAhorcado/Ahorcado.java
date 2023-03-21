package es.uned.master.java.juegoAhorcado;


public class Ahorcado {
    private Palabra palabra;
    private Juego juego;

    public Ahorcado(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            throw new AhorcadoException("La palabra no puede ser nula o vacía");
        }
        this.palabra = new Palabra(palabra.toLowerCase());
        this.juego = new Juego();
    }

    public boolean intentar(char letra) {
        if (juego.getIntentosRestantes() == 0) {
            throw new AhorcadoException("Ya no tienes más intentos");
        }
        if (!Character.isLetter(letra)) {
            throw new AhorcadoException("Solo se pueden ingresar letras");
        }
        boolean adivino = palabra.intentar(letra);
        if (!adivino) {
            juego.incrementarIntentosRestantes();
        }
        return adivino;
    }

    public boolean adivino() {
        return palabra.adivinado();
    }

    public String getPalabraAdivinar() {
        return palabra.getPalabra();
    }

    public int getIntentosRestantes() {
        return juego.getIntentosRestantes();
    }

    public String getPalabraAdivinada() {
        return palabra.getPalabraAdivinada();
    }
}
