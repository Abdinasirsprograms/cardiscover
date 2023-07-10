import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AddCarFormComponent } from 'src/app/add-car-form/car-form.component';
import { carDiscoverHTTPService } from 'src/car-discover-http.service';

@Component({
  selector: 'results-table-actions',
  templateUrl: './actions.component.html',
  styleUrls: ['./actions.component.css']
})
export class ActionsComponent {
  carDiscoverForm!: FormGroup;
  locationComponentChild!: AddCarFormComponent;

  noResult: Boolean = false;
  editCarTextHeader: Boolean = false;
  insertNewCarTextHeader: Boolean = true;
  @Input() addCarView = false;
  @Input() editView = false;
  noInputView = false;
  showStartCarReservationView = false;
  InitialCarDiscoverView  = false;
  chosenRowModifyPointer = 0;
  previousChosenRowModifyPointer = 0;
  @Input() rowsClicked: number[] = [];
  responseCarId: number = 0;
  @Input() showControls: boolean = false;

  constructor(
    private carDiscover: carDiscoverHTTPService,
    private formBuilder: FormBuilder
    ) {}
  clearSelected(){
    // Get all table rows
    let rows = document.querySelectorAll(".added-to-row");

    // Remove the class from all table rows
    rows.forEach(row => {      
      row.classList.add("not-chosen-hover-state")
      row.classList.remove("added-to-row")
    })
    this.rowsClicked = [];
  }

  
  deleteAllCars(){
    this.carDiscover.deleteAllCars().subscribe()
  }

  bookCar(){
    const formData = this.carDiscoverForm.value;
    this.carDiscover.bookCar(formData.doLocation, formData.puLocation, 
      formData.doDate, formData.puDate, this.rowsClicked[0]).subscribe((response: any) => {
      console.log(formData.doLocation, formData.puLocation, 
        formData.doDate, formData.puDate, this.rowsClicked[0])})
  }

  modify(){
    if(this.editView){
      if(this.locationComponentChild){
        if(this.rowsClicked.length > 0){
          // Will allow using Next & Previous button
          this.previousChosenRowModifyPointer = this.chosenRowModifyPointer
          let editCar = this.rowsClicked[this.chosenRowModifyPointer];
          this.locationComponentChild.editView = true;
          this.locationComponentChild.clearSelectedView = true;
          this.carDiscover.getCar(editCar).subscribe(
            (responseData:any) => {
              if( Object.keys(responseData).length === 0){
                console.error("Couldn't get car ID", editCar)
                return;
              }
              if(this.locationComponentChild){
                this.locationComponentChild.carForm.patchValue({
                  id: responseData.id,
                  brand: responseData.brand,
                  dropoffTime: responseData.dropoffTime,
                  location: responseData.location,
                  model: responseData.model,
                  pickupTime: responseData.pickupTime,
                  rate: responseData.rate,
                  size: responseData.size,
                  supplier: responseData.supplier
                });
              }else {
                console.error("ITS NOT RENDERED YET??!!!")

              }
              console.log(this.locationComponentChild.carForm.value.rate)
              this.responseCarId = parseInt(responseData.id)
            }
          );

          this.addCarView = true;
          this.editCarTextHeader = true;
          this.insertNewCarTextHeader = false;
        }
      }
      // carForm.location = 
    }
  }

  deleteRows(){}


}
