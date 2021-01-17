package pro.jbelo.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Clase para ustom tag
 * @author jbelo
 * @date 2017 05.
 */
public class ChartTag extends SimpleTagSupport {

    /**
     * Ancho de image
     */
    private int width;
    /**
     * Alto de image
     */
    private int height;

    /**
     * Setter width
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setter height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Introduce la imagen del gráfico en la página referenciando a DisplayPhotoServlet
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringBuilder image = new StringBuilder("<img src='/healthworldbank-web/DisplayPhotoServlet' alt='photo' height='");
        image.append(height);
        image.append("' width='");
        image.append(width);
        image.append("'/>");
        out.println(image);
    }
}
