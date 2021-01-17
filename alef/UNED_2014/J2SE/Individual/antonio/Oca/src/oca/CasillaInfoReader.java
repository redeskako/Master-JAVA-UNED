/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/


package oca;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class CasillaInfoReader {
	static final String CASILLA = "Casilla";
	static final String INDICE = "indice";
	static final String TIPO = "Tipo";
	static final String INDICE_DESTINO = "IndiceDestino";
	static final String RONDAS_SIN_JUGAR = "RondasSinJugar";
	static final String REPETIR_TURNO = "RepetirTurno";
	static final String COORDENADAS_GUI = "CoordenadasGUI";
	static final String X = "X";
	static final String Y = "Y";

	@SuppressWarnings( { "unchecked" } )
  
	public List< CasillaInfo > leerCasillas( String ficheroTablero ) {
		List< CasillaInfo > tableroOcaInfo = new ArrayList< CasillaInfo >();
		try {
			// creamos un nuevo XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// configuramos un nuevo eventReader
			InputStream is = new FileInputStream( ficheroTablero );
			XMLEventReader eventReader = inputFactory.createXMLEventReader( is );
			// leemos el fichero XML con la descripción de tablero
			CasillaInfo casillaInfo = null;

			while ( eventReader.hasNext() ) {
				XMLEvent event = eventReader.nextEvent();
				if ( event.isStartElement() ) {
					StartElement startElement = event.asStartElement();
					// si es un elemento <Casilla> creamos un nuevo CasillaInfo
					if ( startElement.getName().getLocalPart() == ( CASILLA ) ) {
						casillaInfo = new CasillaInfo();
						// Leemos el índice correspondiente a la casilla como atributo
						Iterator< Attribute > atributos = startElement.getAttributes();
						while ( atributos.hasNext() ) {
							Attribute atributo = atributos.next();
							if ( atributo.getName().toString().equals( INDICE ) ) {
								casillaInfo.setIndice( Integer.parseInt( atributo.getValue() ) );
							}

						}
					}

					if ( event.isStartElement() ) {
						if ( event.asStartElement().getName().getLocalPart().equals( TIPO ) ) {
							event = eventReader.nextEvent();
							casillaInfo.setTipo( Integer.parseInt( event.asCharacters().getData() ) );
							continue;
						}
					}
					if ( event.asStartElement().getName().getLocalPart().equals( INDICE_DESTINO ) ) {
						event = eventReader.nextEvent();
						casillaInfo.setIndiceDestino( Integer.parseInt( event.asCharacters().getData() ) );
						continue;
					}
					if ( event.asStartElement().getName().getLocalPart().equals( RONDAS_SIN_JUGAR )) {
						event = eventReader.nextEvent();
						casillaInfo.setRondasSinJugar( Integer.parseInt( event.asCharacters().getData() ) );
						continue;
					}
					if ( event.asStartElement().getName().getLocalPart().equals( REPETIR_TURNO )) {
						event = eventReader.nextEvent();
						casillaInfo.setRepetirTurno( Boolean.parseBoolean( event.asCharacters().getData() ) );
						continue;
					}
				}
				// añadimos casilla a la lista
				if ( event.isEndElement() ) {
					EndElement endElement = event.asEndElement();
					if ( endElement.getName().getLocalPart() == ( CASILLA ) ) {
						tableroOcaInfo.add( casillaInfo );
					}
				}

			}
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		} catch ( XMLStreamException e ) {
			e.printStackTrace();
		}
		return tableroOcaInfo;
	}
} 