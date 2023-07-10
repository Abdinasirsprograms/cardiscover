import { Component, Injectable, Input, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { carFormInterface } from '../../car-form.interface';
import { carDiscoverHTTPService } from '../../car-discover-http.service';
import { CarDiscoverResultsTableDataSource } from '../../car-discover-results-table.datasource';
import { AppComponent } from '../app.component';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css'],
})
export class AddCarFormComponent implements OnInit {
  
  carForm!: FormGroup;
  @Input() id: number | undefined;
  @Input() brand: String | undefined;
  @Input() dropoffTime: DatePipe | undefined;
  @Input() location: String| undefined;
  @Input() model: String| undefined;
  @Input() pickupTime: DatePipe | undefined;
  @Input() rate: String| undefined;
  @Input() size: String| undefined;
  @Input() supplier: String| undefined;
  editView = false;
  clearSelectedView = false;
  rowsClicked: number[]= [];
  app:any = null;
  constructor(private formBuilder: FormBuilder, 
    private locationDataService: carDiscoverHTTPService,
    private locationDataSource: CarDiscoverResultsTableDataSource,
    private appComponent: AppComponent) {
      this.editView = appComponent.editView;
      this.rowsClicked = appComponent.rowsClicked;
      this.app = appComponent;
     }

  addMultiple(){}


  ngOnInit(): void {
    this.carForm = this.formBuilder.group({
      brand: ['',Validators.required],
      dropoffTime: [null, Validators.required],
      location: ['',Validators.required],
      model: ['', Validators.required],
      pickupTime: [null, Validators.required],
      rate: [null, Validators.required],
      size: ['', Validators.required],
      supplier: ['', Validators.required]
    });
  }

  onSubmit(): void {
    
    let formData: carFormInterface = this.carForm.value;
    // Call the service to submit the form data or perform any other necessary action
    Number(formData.rate).toFixed(2)
    if(this.editView === true && this.appComponent.responseCarId !== -1){
      formData.id = this.appComponent.responseCarId;
      let mydata = this.locationDataService.modifyCar(formData)
      mydata.subscribe(
        response => {
            // Handle the success response
            let objs = this.appComponent.carResultsDataSource.getData();
            let carIDAlreadyChosen = this.rowsClicked.indexOf(response.id);
            objs.splice(carIDAlreadyChosen, 1, response);
            this.appComponent.carResultsDataSource.setData([]);
            this.appComponent.carResultsDataSource.setData([...objs]);
            this.appComponent.displayedColumns = Object.keys(response);
            this.rowsClicked.push(response.id)
            console.log("I've submitted the form and changed the row data")
            console.log("here's the object selected from the index",objs[carIDAlreadyChosen])
          }
        );
      return
    };
    let mydata = this.locationDataService.createCar(formData)
    mydata.subscribe(
    response => {
        // Handle the success response
        this.appComponent.carResultsDataSource.setData([...this.appComponent.carResultsDataSource.getData(), response]);
        this.appComponent.displayedColumns = Object.keys(response);
        this.appComponent.showTable = true;
      }
    );
  }
}
