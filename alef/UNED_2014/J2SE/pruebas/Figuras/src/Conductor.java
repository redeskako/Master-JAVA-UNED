
public class Conductor {
/* En esta clase lo importante es que nos fijemos que estamos referenciando a los diferentes objetos construidos,
 * esto es, poligono1,poligono2, y poligono3 mediante una variable de tipo Super clase, Poligonos en este caso.
 * Es decir creamos objetos de tipo subclase en este caso Triangulo,Circulo,Rectangulo, pero los referenciamos mediante
 * variables de tipo Superclase. Poligonos en este caso. Habiamos dicho que la herencia define una relación de tipo
 * ES UN. Triangulo ES UN Poligono,al igual que Circulo y Rectangulo 
 * 
 * Démonos cuenta como se emplea el concepto de SOBREESCRITURA de metodos. El método area tiene una cabecera igual
 * en las clases Circulo, Triangulo y Rectangulo debido a la ABSTRACCION pero un cuerpo diferente debido a la SOBREESCRITURA
 * de métodos. 
 * 
 *   
 * Por último Java, sin decirle nada, sabe si está manejando un Triangulo, Circulo o Rectangulo y llama al método
 * area correcto según la subclase.   
 */
	
	
	
	
	public static void main(String[] args) {
		double areaDelPoligono;
		
		Poligonos poligono1 = new Triangulo (50,2,"Rojo");
		
		areaDelPoligono=poligono1.area();
		
		System.out.println("El poligono construido ha sido un Triangulo de color "+ poligono1.getColor() + " y de area : "
				+ areaDelPoligono);

		Poligonos poligono2 = new Circulo (15,"Azul");
		
		areaDelPoligono=poligono2.area();
	
		System.out.println("El poligono construido ha sido un Circulo de color "+ poligono2.getColor() + " y de area : "
				+ areaDelPoligono);
		
		Poligonos poligono3 = new Rectangulo("Azul",15,10);
		
		areaDelPoligono=poligono3.area();
		
		System.out.println("El poligono construido ha sido un Rectangulo de color "+ poligono3.getColor() + " y de area : "
				+ areaDelPoligono);
	}

}
