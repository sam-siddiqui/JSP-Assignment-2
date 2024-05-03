package com.samsidd.jspassignment2.controllers;

import com.samsidd.jspassignment2.Utils;
import com.samsidd.jspassignment2.dao.UserDAO;
import com.samsidd.jspassignment2.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "login", value = {"/login", "/register", "/logout", "/update"})
public class AuthController extends HttpServlet {

    HttpSession session;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals("/register"))
            request.getRequestDispatcher("register.jsp").forward(request, response);

        if(servletPath.equals("/login"))
            request.getRequestDispatcher("login.jsp").forward(request, response);

        if(servletPath.equals("/logout")) {
            HttpSession session = request.getSession(false);
            if(session == null) response.sendError(HttpServletResponse.SC_FORBIDDEN, "No Session Created!");

            assert session != null;
            boolean isLoggedIn = (boolean) session.getAttribute("userLoggedIn");
            if(!isLoggedIn) response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not Logged In");

            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath());
            return;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals("/register")) {
            registerUser(request, response);
        }

        if(servletPath.equals("/login")) {
            loginUser(request, response);
        }

        if(servletPath.equals("/update")) {
            HttpSession session = request.getSession(false);
            User currentUserDetails = (User) session.getAttribute("userDetails");

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String username = request.getParameter("username");
            User currentUser = new User(currentUserDetails.getUser_id(), fname, lname, username, currentUserDetails.getPassword());

            if(!Utils.validateUserData(currentUser)) request.getRequestDispatcher("register.jsp").forward(request, response);

            UserDAO userDAO = new UserDAO();

            if(userDAO.updateUser(currentUser)) {
                Utils.updateUserSessionVariables(request, currentUser);

                response.sendRedirect(request.getContextPath());
            }
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();

        User userToCheckAgainst = userDAO.getUserByUserName(username);

        if(userToCheckAgainst == null) response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No User Found");

        assert userToCheckAgainst != null;
        try {
            if(Objects.equals(userToCheckAgainst.getPassword(), password)) {
                Utils.initializeUserSessionVariables(request, userToCheckAgainst);
                response.sendRedirect(request.getContextPath());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Password!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if(!Utils.isValidEmailAddress(email)) request.getRequestDispatcher("register.jsp").forward(request, response);

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(!confirmPassword.equals(password)) request.getRequestDispatcher("register.jsp").forward(request, response);


        User newUser = new User(fname, lname, username, password);

        if(!Utils.validateUserData(newUser)) request.getRequestDispatcher("register.jsp").forward(request, response);

        UserDAO userDAO = new UserDAO();

        if(userDAO.createUser(newUser)) {
            newUser.setUser_id(userDAO.getLastCreatedUserKey());
            Utils.initializeNewUserSessionVariables(request, newUser);

            response.sendRedirect(request.getContextPath());
        }
    }
}
