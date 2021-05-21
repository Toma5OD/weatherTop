package controllers;

import models.Reading;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
  public static void index()
  {
    List<Reading> readings = Reading.findAll();
    render("admin.html", readings);
  }
}

