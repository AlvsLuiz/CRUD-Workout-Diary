package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.utils.PasswordEncryptor;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            User user = new User();

            user.setName(
                    req.getParameter("name"));

            user.setEmail(
                    req.getParameter("email"));

            String senhaCriptografada =
                    PasswordEncryptor.hashPassword(
                            req.getParameter("password"));

            user.setPassword(
                    senhaCriptografada);

            user.setGender("M");

            UserDAO dao =
                    DAOFactory.createDAO(UserDAO.class);

            dao.save(user);

            resp.sendRedirect(
                    req.getContextPath()
                            + "/login.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect(
                    req.getContextPath()
                            + "/register.jsp?erro=true");
        }
    }
}