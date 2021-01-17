package pro.jbelo.servlets;

import pro.jbelo.beans.DataBean;
import pro.jbelo.utils.ServerRunningException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controlador de map
 * @author jbelo
 * @date 2017 04.
 */
@WebServlet("/main/users/map")
public class MapController extends HttpServlet {

    /*
     * Referencia al DatBean
     */
    @Inject
    DataBean dataBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String operation = req.getParameter("operation");
        if (operation != null && operation.equals("pagination")){
            String action = req.getParameter("action");
            try {
                dataBean.retrieveIndicators(action);
                resp.setContentType("text/plain;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                String jsonIndicators = dataBean.objectToJson(dataBean.getIndicatorsContainer());
                System.out.println(jsonIndicators);
                out.println(jsonIndicators);
                out.close();
            } catch (ServerRunningException e) {
                e.printStackTrace();
            }
        } else if((operation != null && operation.equals("geo-statistic"))){
            if(!req.authenticate(resp)){
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            String indicator = req.getParameter("indicator");
            String year = req.getParameter("year");
            try {
                dataBean.retrieveGeoStatistic(indicator, year);
                resp.setContentType("text/plain;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                String geoStatistic = dataBean.objectToJson(dataBean.getDataMapEntities());
                out.println(geoStatistic);
                out.close();
            } catch (ServerRunningException e) {
                e.printStackTrace();
            }

        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main/users/map.jsp");
            requestDispatcher.forward(req,resp);
        }




    }
}
