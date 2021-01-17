
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package es.uned.master.java.healthworldbankws.healthworldbankws;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/".equals(namespaceURI) &&
                  "EstadisticaType".equals(typeName)){
                   
                            return  es.uned.master.java.healthworldbankws.healthworldbankws.EstadisticaType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/".equals(namespaceURI) &&
                  "IndicadorType".equals(typeName)){
                   
                            return  es.uned.master.java.healthworldbankws.healthworldbankws.IndicadorType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/".equals(namespaceURI) &&
                  "PaisType".equals(typeName)){
                   
                            return  es.uned.master.java.healthworldbankws.healthworldbankws.PaisType.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    