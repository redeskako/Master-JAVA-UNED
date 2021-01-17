/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.jms.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.jms.ObjectMessage;

/**
 *
 * @author carlosl.sanchez
 */
@MessageDriven(mappedName = "jms/NoticiaMensaje", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class NoticiaMensajeBean implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    
    @PersistenceContext
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);
    }
    public void save(Object object){
        em.persist(object);
    }
    
    public NoticiaMensajeBean() {
    }

    public void onMessage(Message message) {

        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                NoticiaEntidad e =(NoticiaEntidad) msg.getObject();
                save(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
