package antonio.j2ee.practica1Thinspo.video.modelo;

import java.util.Comparator;


/**
 * Clase que implementa java.util.Comparator
 * Utilizada para ordenar la Collection Tags 
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see java.util.Comparator
 */

public class ComparadorTags implements Comparator<Tag> {
       
			public int compare(Tag tag1, Tag tag2) {
				    return tag1.getValor().compareTo(tag2.getValor());
			}
}
