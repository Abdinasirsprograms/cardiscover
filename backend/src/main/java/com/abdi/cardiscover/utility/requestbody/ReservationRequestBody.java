package com.abdi.cardiscover.utility.requestbody;

import java.util.GregorianCalendar;

import org.springframework.format.annotation.DateTimeFormat;


public class ReservationRequestBody {
    private String doLocation;
    private String puLocation;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private GregorianCalendar doDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private GregorianCalendar puDate;
    private Long id;
    public ReservationRequestBody(String doLocation,
    String puLocation,
    GregorianCalendar doDate,
    GregorianCalendar puDate,
    Long id) {
        this.doLocation = doLocation;
        this.puLocation = puLocation;
        this.doDate = doDate;
        this.puDate = puDate;
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDoLocation() {
        return doLocation;
    }
    public void setDoLocation(String doLocation) {
        this.doLocation = doLocation;
    }
    public String getPuLocation() {
        return puLocation;
    }
    public void setPuLocation(String puLocation) {
        this.puLocation = puLocation;
    }
    public GregorianCalendar getDoDate() {
        return doDate;
    }
    public void setDoDate(GregorianCalendar doDate) {
        this.doDate = doDate;
    }
    public GregorianCalendar getPuDate() {
        return puDate;
    }
    public void setPuDate(GregorianCalendar puDate) {
        this.puDate = puDate;
    }
    @Override
    public String toString() {
        return "RequestAvaliability [doLocation=" + doLocation.toString() + ", puLocation=" + puLocation.toString() + ", doDate=" + doDate.toString()
                + ", puDate=" + puDate.toString() + ", id = " + id + "]";
    }
}
