/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Level;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.TeamDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author raghu
 */
@WebServlet(name = "TeamDetailServlet", urlPatterns = {"/teamDetail"})
public class TeamDetailServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(TeamDetailServlet.class.getName());
    
    @Resource
    Validator validator;
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        LOG.info("TeamDetailServlet inside doGet method");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/teamDetail.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("TeamDetailServlet inside doPost method");
        
        String tName = request.getParameter("teamName");
        String cName = request.getParameter("captainName");
        String contact = request.getParameter("contact");
        String createdDate = request.getParameter("date");

        LOG.info("Parameter caught name:" + tName);
        LOG.info("Parameter caught captain:" + cName);
        LOG.info("Parameter caught contact:" + contact);
        LOG.info("Parameter caught date:" + createdDate);
        
        LocalDateTime teamCreatedDate = null;
        
        if(createdDate != null && !(createdDate.isEmpty())){
//            date example
//             gets user provided date and puts current time. So not recommended
            teamCreatedDate = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.now());
        }
        
//        here level is hardcoded as it will always be BEG in the beginning. It will change with respect to
//        another table, later in the process.
        TeamDetail td = new TeamDetail(tName, cName, contact, Level.BEG, teamCreatedDate);
        
        LOG.info("Constructed instance: " + td.toString());
        
        Set<ConstraintViolation<TeamDetail>> violations = validator.validate(td);
        if(violations.size() > 0){
            LOG.info("There is an issue with validation");
            for(ConstraintViolation<TeamDetail> violation : violations){
                LOG.info(violation.getPropertyPath() + " " + violation.getMessage());
            }
            
            request.setAttribute("createdTime", td.getCreatedTime().toLocalDate().toString());
            
            request.setAttribute("td", td);
            request.setAttribute("errors", violations);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/teamDetail.jsp");
            dispatcher.forward(request, response);
        }
        else{
            LOG.info("Passed validations!!!");
            request.setAttribute("td", td);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirmation.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
