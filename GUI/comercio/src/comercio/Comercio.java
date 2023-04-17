/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comercio;

/**
 *
 * @author kako
 */
public class Comercio implements Comparable<Comercio>{
    private int co_id;
    private String co_name;
    private String co_pais;

    public Comercio(int id, String co_name, String co_pais){
        this.co_id= id;
        this.co_name= co_name;
        this.co_pais= co_pais;
    }

    public int get_co_id(){
        return this.co_id;
    }
    public void set_co_id(int co_id){
        this.co_id= co_id;
    }
    public String get_co_name(){
        return this.co_name;
    }
    public void set_co_name(String co_name){
        this.co_name = co_name;
    }
    public String get_co_pais(){
        return this.co_pais;
    }
    public void set_co_pais(String co_pais){
        this.co_pais = co_pais;
    }
    @Override
    public String toString(){
        return this.co_id + " - " + this.co_name + " - " + this.co_pais;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof Comercio){
            return (this.co_id == (((Comercio) o).get_co_id()));
        }else{
            throw new ClassCastException("NO es un Comercio");
        }
    }
    @Override
    public int hashCode(){
        return (new Integer(this.co_id)).hashCode();
    }
    
    public int compareTo(Comercio com){
        if (this.co_pais.compareToIgnoreCase(com.get_co_pais())==0){
            return this.co_name.compareToIgnoreCase(com.get_co_name());
        }else{
            return this.co_pais.compareToIgnoreCase(com.get_co_pais());
        }
    }
}
