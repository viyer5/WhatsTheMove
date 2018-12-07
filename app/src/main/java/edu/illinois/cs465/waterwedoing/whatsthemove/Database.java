package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.content.Context;
import android.content.res.Resources;

public class Database {
    public static final SearchResult DATABASE[] = {
            new SearchResult("Krannert Art Museum", "The Krannert Art Museum is an art museum located at the University of Illinois at Urbana-Champaign in Champaign, Illinois, United States. It has 48,000 square feet of space devoted to all periods of art, dating from ancient Egypt to contemporary photography.", 4, 2, 0),
            new SearchResult("UI Ice Arena", "University of Illinois Ice Arena, also known as the Big Pond, is an ice arena and recreational sport facility in Champaign, Illinois, and owned and operated by the University of Illinois at Urbana-Champaign.", 4, 3, 1),
            new SearchResult("University of Illinois Observatory", "The University of Illinois Observatory is a great place to look at the night sky and to hear about new astronomical results. The University of Illinois Astronomical Society (UIAS) has run open houses at the observatory for decades. It is free to attend on the second Friday of every month (times depend on the season).", 5, 2, 0),
            new SearchResult("Cravings", "Pared-down venue offering an extensive menu of hearty Chinese staples, plus other Asian dishes.", 4, 1, 2),
            new SearchResult("Taco Bell", "Fast-food chain serving Mexican-inspired fare such as tacos, quesadillas & nachos.", 3, 1, 1)
    };

    public static int getImageId(String title) {
        switch(title) {
            case "Krannert Art Museum":
                return R.drawable.krannert;
            case "UI Ice Arena":
                return R.drawable.ice_arena;
            case "University of Illinois Observatory":
                return R.drawable.uofiobservatory;
            case "Cravings":
                return R.drawable.cravings;
            case "Taco Bell":
                return R.drawable.tacobell;
            default:
                return R.drawable.krannert;
        }
    }

    public static int getRatingImageId(int rating) {
        switch(rating) {
            case 1:
                return R.drawable.stars_1;
            case 2:
                return R.drawable.stars_2;
            case 3:
                return R.drawable.stars_3;
            case 4:
                return R.drawable.stars_4;
            case 5:
                return R.drawable.stars_5;
            default:
                return R.drawable.stars_1;
        }
    }
}
