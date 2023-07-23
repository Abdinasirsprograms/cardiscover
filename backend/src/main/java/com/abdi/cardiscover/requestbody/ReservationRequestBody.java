package com.abdi.cardiscover.requestbody;

import java.util.GregorianCalendar;

import org.springframework.format.annotation.DateTimeFormat;


public class ReservationRequestBody {
    private String doLocation;
    private String puLocation;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private GregorianCalendar doDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private GregorianCalendar puDate;
    private Long carId;
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
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
        return "RequestAvaliability [doLocation=" + doLocation + ", puLocation=" + puLocation + ", doDate=" + doDate
                + ", puDate=" + puDate + "]";
    }
}
