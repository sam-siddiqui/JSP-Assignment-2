package com.samsidd.jspassignment2.controllers;

import com.samsidd.jspassignment2.dao.BookExtendedDAO;
import com.samsidd.jspassignment2.dao.ReservationDAO;
import com.samsidd.jspassignment2.models.BookExtended;
import com.samsidd.jspassignment2.models.Reservation;
import com.samsidd.jspassignment2.models.Topic;
import com.samsidd.jspassignment2.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(name = "index", value = "/")
public class RouteController extends HttpServlet {
    HttpSession session;

    public void init() {
        HttpSession session = null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession(false) == null) initializeSessionVariables(request);
        HttpSession session = request.getSession();
        String selectedTopicID = session.getAttribute("selectedTopicID") == null ? "0" : (String) session.getAttribute("selectedTopicID");

        if(session.getAttribute("userLoggedIn").equals(false)) {
            sendToFrontPage(request, response, selectedTopicID);
        } else {
            sendToAccountPage(request, response, selectedTopicID);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedTopicID = request.getParameter("selectedTopicID") == null ? "0" : request.getParameter("selectedTopicID");
        HttpSession session = request.getSession(false);

        if(session.getAttribute("userLoggedIn").equals(false)) {
            sendToFrontPage(request, response, selectedTopicID);
        } else {
            sendToAccountPage(request, response, selectedTopicID);
        }
    }

    private void sendToFrontPage(HttpServletRequest request, HttpServletResponse response, String filterTopicID) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        BookExtendedDAO bookDAO = new BookExtendedDAO();
        ArrayList<BookExtended> availableBooks = null;
        if(filterTopicID.equals("0")) availableBooks = bookDAO.getAllAvailableBooks();
        else availableBooks = bookDAO.getAllAvailableBooksByTopicID(Integer.parseInt(filterTopicID));

        ArrayList<Topic> allTopics = bookDAO.getAllBookTopics();

        request.setAttribute("totalTopics", allTopics);
        request.setAttribute("totalBooksAvailable", availableBooks);
        request.setAttribute("selectedTopicID", filterTopicID);
        session.setAttribute("selectedTopicID", filterTopicID);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
    private void sendToAccountPage(HttpServletRequest request, HttpServletResponse response, String filterTopicID) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("userDetails");
        ArrayList<Integer> reservedBooksID;
        Object reservedBooksInSession = session.getAttribute("reservedBooks");

        if(reservedBooksInSession != null)
            reservedBooksID = (ArrayList<Integer>) reservedBooksInSession;
        else {

            ReservationDAO reservationDAO = new ReservationDAO();

            ArrayList<Reservation> reservedBooks = reservationDAO.getReservedBooksByUserID(currentUser.getUser_id());
            reservedBooksID = reservedBooks
                    .stream()
                    .map(Reservation::getBook_id)
                    .collect(Collectors.toCollection(ArrayList::new));
            session.setAttribute("reservedBooks", reservedBooksID);
        }

        BookExtendedDAO bookDAO = new BookExtendedDAO();
        ArrayList<BookExtended> availableBooks = null;
        if(filterTopicID.equals("0")) availableBooks = bookDAO.getAllAvailableBooks();
        else availableBooks = bookDAO.getAllAvailableBooksByTopicID(Integer.parseInt(filterTopicID));

        ArrayList<BookExtended> reservedBooksByUser = bookDAO.getBooksByID(reservedBooksID);
        ArrayList<Topic> allTopics = bookDAO.getAllBookTopics();

        request.setAttribute("totalTopics", allTopics);
        request.setAttribute("totalBooksAvailable", availableBooks);
        request.setAttribute("reservedBooksByUser", reservedBooksByUser);
        request.setAttribute("selectedTopicID", filterTopicID);
        session.setAttribute("selectedTopicID", filterTopicID);
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    private void initializeSessionVariables(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userLoggedIn", false);
        session.setAttribute("username", null);
        session.setAttribute("userDetails", null);
        session.setAttribute("reservedBooks", null);
        session.setAttribute("filterBooksByTopicID", "0");

    }

}
