package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.utils.PasswordEncryptor;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        String email =
                req.getParameter("user_login");

        String password =
                req.getParameter("user_pw");

        try {

            UserDAO dao =
                    DAOFactory.createDAO(UserDAO.class);

            User user =
                    dao.findByEmail(email);

            if (user != null &&
                PasswordEncryptor.checkPassword(
                        password,
                        user.getPassword())) {

                HttpSession session =
                        req.getSession();

                session.setAttribute(
                        "usuario_logado",
                        user);

                resp.sendRedirect(
                        req.getContextPath()
                        + "/index.jsp");

            } else {

                resp.sendRedirect(
                        req.getContextPath()
                        + "/login.jsp?erro=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect(
                    req.getContextPath()
                    + "/login.jsp?erro=true");
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session =
                req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.sendRedirect(
                req.getContextPath()
                + "/login.jsp");
    }
}