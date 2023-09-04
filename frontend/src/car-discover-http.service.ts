import { Injectable }   from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable }   from 'rxjs';
import { map } from 'rxjs/operators';
import { carFormInterface } from './car-form.interface';
@Injectable({
  providedIn: 'root'
})
export class carDiscoverHTTPService {
  private BASE_API_URL = 'http://192.168.1.196:8080/car/'
  constructor(private http: HttpClient) {
  }
  getAllCars(): Observable<carFormInterface[]> {
    return this.http.get<carFormInterface[]>(this.BASE_API_URL + 'get-all-cars');
  }
  deleteAllCars(): Observable<carFormInterface[]> {
    return this.http.get<carFormInterface[]>(this.BASE_API_URL + 'delete-all-cars');
  }
  deleteCar(id:number): Observable<carFormInterface[]> {
    return this.http.get<carFormInterface[]>(this.BASE_API_URL + 'delete-car/' + id);
  }
  getCar(id:number): Observable<carFormInterface[]> {
    const options = id ?
   { params: new HttpParams().set('id', id) } : {};
    return this.http.get<carFormInterface[]>(this.BASE_API_URL + 'get-car', options);
  }
  createCar(carFormData: carFormInterface): Observable<carFormInterface> {    
    // this'll create a car - not optimized from the backend since it's just 1 class that creates everything
    carFormData.id = 0; 
    return this.http.post<carFormInterface>(this.BASE_API_URL + 'create-car', carFormData);
  }
  modifyCar(locationData: carFormInterface): Observable<carFormInterface> {    
    return this.http.post<carFormInterface>(this.BASE_API_URL + 'modify-car', locationData);
  }

}