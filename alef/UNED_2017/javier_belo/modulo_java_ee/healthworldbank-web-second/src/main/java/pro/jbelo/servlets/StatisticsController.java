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
 * Controlador de statistics.jsp
 * @author jbelo
 * @date 2017 04.
 */
@WebServlet("/main/users/statistics")
public class StatisticsController extends HttpServlet {

    /**
     * Referencia a DataBean
     */
    @Inject
    DataBean dataBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String operation = req.getParameter("operation");
        if (operation != null && operation.equals("pagination")) {
            String selector = req.getParameter("selector");
            if (selector.equals("select-country")) {




                String action = req.getParameter("action");
                try {
                    dataBean.retrieveCountries(action);
                    resp.setContentType("text/plain;charset=UTF-8");
                    PrintWriter out = resp.getWriter();
                    String jsonCountries = dataBean.objectToJson(dataBean.getCountryContainer());
                    out.println(jsonCountries);
                    out.close();
                } catch (ServerRunningException e) {
                    e.printStackTrace();
                }

            } else if (selector.equals("select-indicator")) {
                String action = req.getParameter("action");
                try {
                    dataBean.retrieveIndicators(action);
                    resp.setContentType("text/plain;charset=UTF-8");
                    PrintWriter out = resp.getWriter();
                    String jsonIndicators = dataBean.objectToJson(dataBean.getIndicatorsContainer());
                    out.println(jsonIndicators);
                    out.close();
                } catch (ServerRunningException e) {
                    e.printStackTrace();
                }
            }

        } else if(operation != null && operation.equals("health-statistic")){
            String country = req.getParameter("country");
            String indicator = req.getParameter("indicator");
            try {
                dataBean.retrieveStatistics("neutral",country,indicator);
            } catch (ServerRunningException e) {
                e.printStackTrace();
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main/users/statistics.jsp");
            requestDispatcher.forward(req,resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main/users/statistics.jsp");
            requestDispatcher.forward(req,resp);
        }
    }


}
