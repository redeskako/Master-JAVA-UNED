package pro.jbelo.aux;

import pro.jbelo.model.DataBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Servelet encargdo de procesar la imagen con el custom tag ChartTag
 * Se restringe el acceso
 */

@WebServlet(name = "DisplayPhotoServlet", urlPatterns = {"/DisplayPhotoServlet"})
@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(
                value = "GET",
                rolesAllowed = {"USER","ADMIN"},
                transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
})
public class DisplayPhotoServlet extends HttpServlet {

    /*
    Referencia al DataBean
     */
   @Inject
   DataBean dataBean;
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");

        OutputStream out = response.getOutputStream();
        try {
            byte[] bytes = dataBean.getChartImage();
            for (int i = 0; i < bytes.length; i++) {
                out.write( bytes[i]);
            }
        } finally {            
            out.close();
        } 
    }

}
