
/**
 * HealthWorldBankWsCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package es.uned.master.java.healthworldbankws.healthworldbankws;

    /**
     *  HealthWorldBankWsCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class HealthWorldBankWsCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public HealthWorldBankWsCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public HealthWorldBankWsCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerEstadisticas method
            * override this method for handling normal response from obtenerEstadisticas operation
            */
           public void receiveResultobtenerEstadisticas(
                    es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerEstadisticas operation
           */
            public void receiveErrorobtenerEstadisticas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerIndicadores method
            * override this method for handling normal response from obtenerIndicadores operation
            */
           public void receiveResultobtenerIndicadores(
                    es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerIndicadores operation
           */
            public void receiveErrorobtenerIndicadores(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerPaises method
            * override this method for handling normal response from obtenerPaises operation
            */
           public void receiveResultobtenerPaises(
                    es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerPaises operation
           */
            public void receiveErrorobtenerPaises(java.lang.Exception e) {
            }
                


    }
    