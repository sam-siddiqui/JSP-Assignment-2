package com.samsidd.jspassignment2.controllers;

import com.samsidd.jspassignment2.dao.BookDAO;
import com.samsidd.jspassignment2.dao.ReservationDAO;
import com.samsidd.jspassignment2.models.Book;
import com.samsidd.jspassignment2.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "reserve", value = {"/reserve", "/return", "/returnAll", "/request"})
public class ReservationController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        if(servletPath.equals("/request")) {response.sendRedirect(request.getContextPath()); return;};

        HttpSession session = request.getSession(false);
        if(session == null) response.sendError(HttpServletResponse.SC_FORBIDDEN, "No Session Created!");

        assert session != null;
        boolean isLoggedIn = (boolean) session.getAttribute("userLoggedIn");
        if(!isLoggedIn) response.sendRedirect(request.getContextPath() + "/login");



        ReservationDAO reservationDAO = new ReservationDAO();
        BookDAO bookDAO = new BookDAO();
        ArrayList<Integer> reservedBooksID = (ArrayList<Integer>) session.getAttribute("reservedBooks");

        if(servletPath.equals("/reserve")) {
            boolean isReserved = false;
            User currentUser = (User) session.getAttribute("userDetails");
            int bookIDToReserve = Integer.parseInt(request.getParameter("book_id"));
            Book bookToReserve = bookDAO.getBookByID(bookIDToReserve);

            if(bookToReserve == null) response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book Not Found!");
            assert bookToReserve != null;

            isReserved = reservationDAO.reserveBookForUser(bookToReserve.getBook_id(), currentUser.getUser_id());

            if(isReserved) {
                bookDAO.makeBookUnavailable(bookToReserve);
                reservedBooksID.add(bookIDToReserve);
                session.setAttribute("reservedBooks", reservedBooksID);
            }
        }

        if(servletPath.equals("/return")) {
            boolean isReturned = false;
            User currentUser = (User) session.getAttribute("userDetails");
            int bookIDToReturn = Integer.parseInt(request.getParameter("book_id"));
            Book bookToReturn = bookDAO.getBookByID(bookIDToReturn);

            if(bookToReturn == null) response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book Not Found!");

            assert bookToReturn != null;
            isReturned = reservationDAO.returnReservedBookFromUser(bookToReturn.getBook_id(), currentUser.getUser_id());


            if(isReturned) {
                bookDAO.makeBookAvailable(bookToReturn);
                reservedBooksID.remove(Integer.valueOf(bookIDToReturn));
                session.setAttribute("reservedBooks", reservedBooksID);
            }
        }

        if(servletPath.equals("/returnAll")) {
            boolean isReturned = false;
            User currentUser = (User) session.getAttribute("userDetails");

            isReturned = reservationDAO.returnAllReservedBooksFromUser(currentUser.getUser_id());

            if(isReturned) {
                for (int book_id : reservedBooksID) {
                    bookDAO.makeBookAvailable(book_id);
                }

                reservedBooksID = new ArrayList<Integer>();
                session.setAttribute("reservedBooks", reservedBooksID);
            }
        }



        response.sendRedirect(request.getContextPath());
    }
}
