
/**
 * HealthWorldBankWsStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package es.uned.master.java.healthworldbankws.healthworldbankws;

        

        /*
        *  HealthWorldBankWsStub java implementation
        */

        
        public class HealthWorldBankWsStub extends org.apache.axis2.client.Stub
        implements HealthWorldBankWs{
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized java.lang.String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("HealthWorldBankWs" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[3];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/", "obtenerEstadisticas"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/", "obtenerIndicadores"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[1]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/", "obtenerPaises"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[2]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public HealthWorldBankWsStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public HealthWorldBankWsStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public HealthWorldBankWsStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://localhost:8080/HealthWorldBankWsServidor/services/HealthWorldBankWs/" );
                
    }

    /**
     * Default Constructor
     */
    public HealthWorldBankWsStub() throws org.apache.axis2.AxisFault {
        
                    this("http://localhost:8080/HealthWorldBankWsServidor/services/HealthWorldBankWs/" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public HealthWorldBankWsStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * 
                     * @see es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWs#obtenerEstadisticas
                     * @param obtenerEstadisticas6
                    
                     */

                    

                            public  es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse obtenerEstadisticas(

                            es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas obtenerEstadisticas6)
                        

                    throws java.rmi.RemoteException
                    
                    {
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/ObtenerEstadisticas");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenerEstadisticas6,
                                                    optimizeContent(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerEstadisticas")), new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerEstadisticas"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerEstadisticas"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerEstadisticas"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerEstadisticas"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWs#startobtenerEstadisticas
                    * @param obtenerEstadisticas6
                
                */
                public  void startobtenerEstadisticas(

                 es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas obtenerEstadisticas6,

                  final es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/ObtenerEstadisticas");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenerEstadisticas6,
                                                    optimizeContent(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerEstadisticas")), new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerEstadisticas"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultobtenerEstadisticas(
                                        (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorobtenerEstadisticas(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerEstadisticas"))){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerEstadisticas"));
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                                                    java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerEstadisticas"));
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
					
										            callback.receiveErrorobtenerEstadisticas(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerEstadisticas(f);
                                            }
									    } else {
										    callback.receiveErrorobtenerEstadisticas(f);
									    }
									} else {
									    callback.receiveErrorobtenerEstadisticas(f);
									}
								} else {
								    callback.receiveErrorobtenerEstadisticas(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorobtenerEstadisticas(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWs#obtenerIndicadores
                     * @param obtenerIndicadores8
                    
                     */

                    

                            public  es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse obtenerIndicadores(

                            es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores obtenerIndicadores8)
                        

                    throws java.rmi.RemoteException
                    
                    {
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
              _operationClient.getOptions().setAction("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/ObtenerIndicadores");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenerIndicadores8,
                                                    optimizeContent(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerIndicadores")), new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerIndicadores"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerIndicadores"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerIndicadores"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerIndicadores"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWs#startobtenerIndicadores
                    * @param obtenerIndicadores8
                
                */
                public  void startobtenerIndicadores(

                 es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores obtenerIndicadores8,

                  final es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
             _operationClient.getOptions().setAction("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/ObtenerIndicadores");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenerIndicadores8,
                                                    optimizeContent(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerIndicadores")), new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerIndicadores"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultobtenerIndicadores(
                                        (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorobtenerIndicadores(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerIndicadores"))){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerIndicadores"));
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                                                    java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerIndicadores"));
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
					
										            callback.receiveErrorobtenerIndicadores(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerIndicadores(f);
                                            }
									    } else {
										    callback.receiveErrorobtenerIndicadores(f);
									    }
									} else {
									    callback.receiveErrorobtenerIndicadores(f);
									}
								} else {
								    callback.receiveErrorobtenerIndicadores(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorobtenerIndicadores(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[1].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[1].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWs#obtenerPaises
                     * @param obtenerPaises10
                    
                     */

                    

                            public  es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse obtenerPaises(

                            es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises obtenerPaises10)
                        

                    throws java.rmi.RemoteException
                    
                    {
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
              _operationClient.getOptions().setAction("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/ObtenerPaises");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenerPaises10,
                                                    optimizeContent(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerPaises")), new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerPaises"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerPaises"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerPaises"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerPaises"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWs#startobtenerPaises
                    * @param obtenerPaises10
                
                */
                public  void startobtenerPaises(

                 es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises obtenerPaises10,

                  final es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
             _operationClient.getOptions().setAction("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/ObtenerPaises");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenerPaises10,
                                                    optimizeContent(new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerPaises")), new javax.xml.namespace.QName("http://healthworldbankws.java.master.uned.es/HealthWorldBankWs/",
                                                    "obtenerPaises"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultobtenerPaises(
                                        (es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorobtenerPaises(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerPaises"))){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerPaises"));
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                                                    java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"ObtenerPaises"));
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
					
										            callback.receiveErrorobtenerPaises(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorobtenerPaises(f);
                                            }
									    } else {
										    callback.receiveErrorobtenerPaises(f);
									    }
									} else {
									    callback.receiveErrorobtenerPaises(f);
									}
								} else {
								    callback.receiveErrorobtenerPaises(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorobtenerPaises(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[2].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[2].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

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

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://localhost:8080/HealthWorldBankWsServidor/services/HealthWorldBankWs/
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
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


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



    
   }
   