
/**
 * HealthWorldBankWsMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package es.uned.master.java.healthworldbankws.healthworldbankws;

        /**
        *  HealthWorldBankWsMessageReceiverInOut message receiver
        */

        public class HealthWorldBankWsMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        HealthWorldBankWsSkeletonInterface skel = (HealthWorldBankWsSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("obtenerEstadisticas".equals(methodName)){
                
                es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse obtenerEstadisticasResponse7 = null;
	                        es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas wrappedParam =
                                                             (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               obtenerEstadisticasResponse7 =
                                                   
                                                   
                                                         skel.obtenerEstadisticas(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), obtenerEstadisticasResponse7, false, new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerEstadisticas"));
                                    } else 

            if("obtenerIndicadores".equals(methodName)){
                
                es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse obtenerIndicadoresResponse9 = null;
	                        es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores wrappedParam =
                                                             (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               obtenerIndicadoresResponse9 =
                                                   
                                                   
                                                         skel.obtenerIndicadores(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), obtenerIndicadoresResponse9, false, new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerIndicadores"));
                                    } else 

            if("obtenerPaises".equals(methodName)){
                
                es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse obtenerPaisesResponse11 = null;
	                        es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises wrappedParam =
                                                             (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               obtenerPaisesResponse11 =
                                                   
                                                   
                                                         skel.obtenerPaises(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), obtenerPaisesResponse11, false, new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerPaises"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse wrapObtenerEstadisticas(){
                                es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse wrappedElement = new es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse wrapObtenerIndicadores(){
                                es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse wrappedElement = new es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse wrapObtenerPaises(){
                                es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse wrappedElement = new es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.class.equals(type)){
                
                           return es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse.class.equals(type)){
                
                           return es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.class.equals(type)){
                
                           return es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse.class.equals(type)){
                
                           return es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.class.equals(type)){
                
                           return es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse.class.equals(type)){
                
                           return es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    