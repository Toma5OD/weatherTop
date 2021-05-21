package controllers;

import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class StationRow extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Admin");

        List<Station> stationList = Station.findAll();
        render ("stationrow.html", stationList);
    }
}

