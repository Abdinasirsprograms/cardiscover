import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { carFormInterface } from 'src/car-form.interface';

@Injectable({
  providedIn: 'root'
})

export class locationHttpService {


  private BASE_API_URL = 'https://abdinasirnoor.com/cardiscover/api/location/'
  constructor(private http: HttpClient) {}

  getLocationNames(locationInput:String): Observable<String[]> {
    return this.http.get<String[]>(this.BASE_API_URL + locationInput);
  }

  
  getCars(doLocation:String, puLocation:String, doDate:Date, puDate:Date): Observable<carFormInterface> {
    const fullPath = this.BASE_API_URL +'get-avaliable-cars';
    return this.http.post<carFormInterface>(this.BASE_API_URL +'get-avaliable-cars',
    {"doLocation":doLocation, "puLocation":puLocation, "doDate":doDate,
      "puDate":puDate});
  }

}