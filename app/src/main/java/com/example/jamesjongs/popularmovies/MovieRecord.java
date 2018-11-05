package com.example.jamesjongs.popularmovies;


public class MovieRecord {

    // Variables

    public String title;
    public String synopsis;
    public Double userRating;
    public String releaseDate;
    public String posterpath;


    // Methods

    public MovieRecord(String iTitle, String iSynopsis, Double iUserRating, String iReleaseDate, String iPosterPath ) {
        title = iTitle;
        synopsis = iSynopsis;
        userRating = iUserRating;
        releaseDate = iReleaseDate;
        posterpath = iPosterPath;
    }

}
