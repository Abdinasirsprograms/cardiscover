import { Component, Injectable, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { carFormInterface } from '../../car-form.interface';
import { carDiscoverHTTPService } from '../../car-discover-http.service';
import { CarDiscoverResultsTableDataSource } from '../../car-discover-results-table.datasource';
import { AppComponent } from '../app.component';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css'],
})
export class AddCarFormComponent implements OnInit,OnChanges {
  
  carForm: FormGroup = this.formBuilder.group({
    id: [null],
    brand: ['',Validators.required],
    dropoffTime: [null, Validators.required],
    location: ['',Validators.required],
    model: ['', Validators.required],
    pickupTime: [null, Validators.required],
    rate: ['', Validators.required],
    size: ['', Validators.required],
    supplier: ['', Validators.required]
  });
  @Input() id!: Number;
  @Input() reservation_id!: Number;
  @Input() brand!: String;
  @Input() dropoffTime!: Date;
  @Input() location!: String;
  @Input() model!: String;
  @Input() pickupTime!: Date;
  @Input() rate!: String;
  @Input() size!: String;
  @Input() supplier!: String;
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
  
  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
  }

  ngOnInit(): void {
    this.carForm.controls['id'].disable()
    if(this.reservation_id !== -1){
      this.carForm.addControl('reservation_id',  this.formBuilder.control(this.reservation_id, [Validators.required]))
      this.carForm.controls['reservation_id'].disable()
    }
  };
  //   this.carForm = this.formBuilder.group({
  //     brand: ['',Validators.required],
  //     dropoffTime: [null, Validators.required],
  //     location: ['',Validators.required],
  //     model: ['', Validators.required],
  //     pickupTime: [null, Validators.required],
  //     rate: [null, Validators.required],
  //     size: ['', Validators.required],
  //     supplier: ['', Validators.required]
  //   });
  // }

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
