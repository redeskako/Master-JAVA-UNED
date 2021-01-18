package driver;



import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.EnumSet;



import tikajes.Categoria;
import tikajes.Catjor;
import tikajes.Empleado;
import tikajes.Jornada;
import tikajes.Tikaje;



public class Driver {

    /**
     * @param args
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        Date f = Date.valueOf("2001-03-25");
        Date f2 = Date.valueOf("1998-09-30");
        Empleado e = new Empleado("Isabel", "peña fernandez" , "C/ cuevas de agua, nº5",f, 2500);
        Empleado e2 = new Empleado("Pepito","Perez González","C/no recuerdo, nº44",f2,1000);
        Tikaje t = new Tikaje();
        t.añadirEmpleado(e);
        //t.añadirEmpleado(e2);
        //System.out.println(t.toString());
        //t.eliminarEmpleado(e2);
        //System.out.println(t.toString());
        Catjor cj1 = new Catjor(Categoria.DIRECTOR,Jornada.INTENSIVA);
        Catjor cj2 = new Catjor(Categoria.GERENTE,Jornada.INTENSIVA);
        Catjor cj3 = new Catjor(Categoria.JEFE_DE_SERVICIO,Jornada.INTENSIVA);
        Catjor cj4 = new Catjor(Categoria.JEFE_DE_DEPARTAMENTO,Jornada.NOCTURNA);
        Catjor cj5 = new Catjor(Categoria.JEFE_DE_DEPARTAMENTO,Jornada.NORMAL);
        Catjor cj6 = new Catjor(Categoria.JEFE_DE_NEGOCIADO,Jornada.NOCTURNA);
        Catjor cj7 = new Catjor(Categoria.JEFE_DE_NEGOCIADO,Jornada.NORMAL);
        Catjor cj8 = new Catjor(Categoria.ADMINISTRATIVO,Jornada.NORMAL);
        Catjor cj9 = new Catjor(Categoria.PEON,Jornada.NORMAL);
        Catjor cj10 = new Catjor(Categoria.OFICIAL1,Jornada.NORMAL);
        Catjor cj11 = new Catjor(Categoria.SEGURATA,Jornada.NOCTURNA);

        t.asignarCategoriaJornada(e, cj1);
        t.asignarCategoriaJornada(e2, cj2);
        System.out.println(t.toString());
        t.asignarCategoriaJornada(e, cj3);
        System.out.println(t.toString());


    }

}

