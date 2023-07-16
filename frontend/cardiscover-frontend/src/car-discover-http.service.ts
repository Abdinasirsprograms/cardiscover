import { Injectable }   from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable }   from 'rxjs';
import { map } from 'rxjs/operators';
import { carFormInterface } from './car-form.interface';
@Injectable({
  providedIn: 'root'
})
export class carDiscoverHTTPService {

  constructor(private http: HttpClient) {
  }
  getLocationCars(doLocation:String, puLocation:String): Observable<carFormInterface> {
    return this.http.post<carFormInterface>('http://192.168.1.196:8080/location-cars', {"doLocation":doLocation, "puLocation":puLocation});
  }
  getCars(doLocation:String, puLocation:String, doDate:Date, puDate:Date): Observable<carFormInterface> {
    return this.http.post<carFormInterface>('http://192.168.1.196:8080/get-avaliable-cars',
    {"doLocation":doLocation, "puLocation":puLocation, "doDate":doDate,
      "puDate":puDate});
  }
  getAllCars(): Observable<carFormInterface[]> {
    return this.http.get<carFormInterface[]>('http://192.168.1.196:8080/get-all-cars');
  }
  deleteAllCars(): Observable<carFormInterface[]> {
    return this.http.get<carFormInterface[]>('http://192.168.1.196:8080/delete-all-cars');
  }
  getCar(id:number): Observable<carFormInterface[]> {
    const options = id ?
   { params: new HttpParams().set('id', id) } : {};
    return this.http.get<carFormInterface[]>('http://192.168.1.196:8080/get-car', options);
  }
  createCar(carFormData: carFormInterface): Observable<carFormInterface> {    
    // this'll create a car - not optimized from the backend since it's just 1 class that creates everything
    carFormData.id = 0; 
    return this.http.post<carFormInterface>('http://192.168.1.196:8080/create-car', carFormData);
  }
  modifyCar(locationData: carFormInterface): Observable<carFormInterface> {    
    return this.http.post<carFormInterface>('http://192.168.1.196:8080/modify-car', locationData);
  }
  bookCar(doLocation:String, puLocation:String, doDate:Date, puDate:Date, carID:number): Observable<carFormInterface> {    
    return this.http.post<carFormInterface>('http://192.168.1.196:8080/book-car', {
      "doLocation":doLocation,  "puLocation":puLocation, 
      "doDate":doDate, "puDate":puDate , "carID":carID
    });
  }
}