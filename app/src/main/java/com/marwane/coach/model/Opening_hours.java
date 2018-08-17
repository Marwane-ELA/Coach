package com.marwane.coach.model;

/**
 * http://pojo.sodhanalibrary.com/Convert
 * convert json response of the API to a class
 */
public class Opening_hours {
    private String open_now;

    public String getOpen_now ()
    {
        return open_now;
    }

    public void setOpen_now (String open_now)
    {
        this.open_now = open_now;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [open_now = "+open_now+"]";
    }
}