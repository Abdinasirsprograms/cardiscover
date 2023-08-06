import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { carFormInterface } from 'src/car-form.interface';

@Injectable({
  providedIn: 'root'
})
export class ReservationHTTPService {
  private BASE_API_URL = 'http://192.168.1.196:8080/reservation/'

  constructor(private http: HttpClient) { }

  bookCar(doLocation:String, puLocation:String, doDate:Date, puDate:Date, carID:Number): Observable<carFormInterface> {    
    console.log("this is at the http client service level");
    console.table({
      "doLocation":doLocation,  "puLocation":puLocation, 
      "doDate":doDate, "puDate":puDate , "id":carID
    })
    return this.http.post<carFormInterface>(this.BASE_API_URL + 'book-car', {
      "doLocation":doLocation,  "puLocation":puLocation, 
      "doDate":doDate, "puDate":puDate , "id":carID
    });
  }
  getAllBookings(): Observable<carFormInterface> {    
    return this.http.get<carFormInterface>(this.BASE_API_URL + 'get-all-bookings');
  }
  getBooking(reservationID:Number): Observable<carFormInterface> {    
    return this.http.get<carFormInterface>(this.BASE_API_URL + 'get-booking/' + reservationID);
  }
}
