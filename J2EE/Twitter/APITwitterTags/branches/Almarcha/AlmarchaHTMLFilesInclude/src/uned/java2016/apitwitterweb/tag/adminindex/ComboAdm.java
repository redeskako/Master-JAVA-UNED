package uned.java2016.apitwitterweb.tag.adminindex;

import uned.java2016.apitwitter.dao.*;
import java.util.Iterator;
import java.sql.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.ArrayList;

public class ComboAdm extends TagSupport{
	private static final long serialVersionUID = 1L;
	ArrayList<String> listahashtagsadm = null;
	Iterator<String> it = null;
	
	
	
	public int doStartTag() throws JspException {
		
		StringBuilder str = new StringBuilder();
		
		try {
			cargarHashtags();
            if (this.listahashtagsadm.size()>0){
            	it = this.listahashtagsadm.iterator();
            	
            	while (it.hasNext()){
            		String hashtag =it.next();
            		//str.append("<tr><td>"+p.getHashtag()+"</td><td>"+p.getOrigen()+"</td><td>"+p.getFechaEntrada().toString()+"</td></tr>");
            		str.append("<option value="+hashtag+">"+hashtag+"</option>");
            	}
            	this.pageContext.getOut().print(str);
            }
        } catch (IOException e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return EVAL_PAGE;
    }
	
	private void cargarHashtags () {
		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		HashtagAdmDAOImpl hashtagadmbd =new HashtagAdmDAOImpl(miBd.getConnection());


		//this.miListaHashtags=new ArrayList<String>();

		try {
			//this.miListaHashtags= hashtagadmbd.selectHashtagIds();
            this.listahashtagsadm = hashtagadmbd.selectHashtagIds();
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		miBd.cerrarConexion();	
	}
}
