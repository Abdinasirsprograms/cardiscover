import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { carFormInterface } from 'src/car-form.interface';

@Injectable({
  providedIn: 'root'
})

export class locationHttpService {


  private BASE_API_URL = 'http://192.168.1.196:8080/location/'
  constructor(private http: HttpClient) {
  }
  // getLocationCars(doLocation:String, puLocation:String): Observable<carFormInterface> {
  //   return this.http.post<carFormInterface>(this.BASE_API_URL + 'location-cars', {"doLocation":doLocation, "puLocation":puLocation});
  // }

  
  // getCars(doLocation:String, puLocation:String, doDate:Date, puDate:Date): Observable<carFormInterface> {
  //   return this.http.post<carFormInterface>(this.BASE_API_URL + 'location/get-avaliable-cars',
  //   {"doLocation":doLocation, "puLocation":puLocation, "doDate":doDate,
  //     "puDate":puDate});
  // }
  
  getCars(doLocation:String, puLocation:String, doDate:Date, puDate:Date): Observable<carFormInterface> {
    const fullPath = this.BASE_API_URL +'get-avaliable-cars';
    return this.http.post<carFormInterface>(this.BASE_API_URL +'get-avaliable-cars',
    {"doLocation":doLocation, "puLocation":puLocation, "doDate":doDate,
      "puDate":puDate});
    // return this.http.post<carFormInterface>(this.BASE_API_URL + 'location/get-avaliable-cars',
    // {"doLocation":doLocation, "puLocation":puLocation, "doDate":doDate,
    //   "puDate":puDate});
  }

}