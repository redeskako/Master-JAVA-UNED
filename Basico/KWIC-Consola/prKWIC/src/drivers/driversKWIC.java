package drivers;

import kwic.KWIC;



public class driversKWIC {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String palNoSig = "del,el,la,los,las,al,un,una,unos,unas,y,o,a,ante,bajo,cabe,de,desde,en,entre,hacia,hasta,para,por,sin,si,no";
        String [] frases = {
            "El color del dinero",
            "Color púrpura",
            "Misión imposible",
            "La misión",
            "La rosa púrpura del Cairo",
            "El dinero llama al dinero",
            "La rosa del azafrán",
            "El nombre de la rosa",
            "Toma el dinero y corre",
            "El bueno, el feo y el malo",
            "El Bueno, EL FEO Y EL MALO"
        };

        KWIC glosario = new KWIC();
        glosario.palabrasNoSignificativas(palNoSig);
        //System.out.println("palabras no significativas: \n"+palNoSig.toString());

        for (int i=0 ; i<frases.length ; i++){
            glosario.generarFrases(frases[i]);
        }

        //System.out.println("glosario: \n"+glosario.toString());
        System.out.println(glosario.toString());

    }

}
