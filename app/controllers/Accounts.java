package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class Accounts extends Controller {
    public static void index() {

        Member member = Accounts.getLoggedInMember();
        member = Accounts.getLoggedInMember();

        render("member.html", member);
    }

    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void register(String firstname, String lastname, String email, String password) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static void user(String firstname, String lastname, String email, String password) {

        Member member = Accounts.getLoggedInMember();
        if (!firstname.isEmpty()) member.firstname = firstname;
        if (!lastname.isEmpty()) member.lastname = lastname;
        if (!email.isEmpty()) member.email = email;
        member.password = password;
        member.save();
        redirect("/member");
    }
}
