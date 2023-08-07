import { AfterContentInit, AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { AddCarFormComponent } from './add-car-form/car-form.component';
import { CarDiscoverResultsTableDataSource } from '../car-discover-results-table.datasource';
import { carDiscoverHTTPService } from '../car-discover-http.service';
import { locationHttpService } from 'src/services/location/location.service';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ResultsTableComponent } from './table/results-table/results-table.component';
import { CardiscoverFormComponent } from './cardiscover-form/cardiscover-form.component';
import { transition } from '@angular/animations';
import { ReservationHTTPService } from 'src/services/reservation/reservation.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})


export class AppComponent  {


  rowsClicked: any[] = [];
  carResultsUnfiltered: number[] = [];
  doLocations: number[] = [];
  displayedColumns: string[] = [];
  brand: String = '';

  showCarDiscoverForm = true;
  // view flags
  noResult: Boolean = false;
  editCarTextHeader: Boolean = false;
  insertNewCarTextHeader: Boolean = true;
  showTable = false;
  addCarView = false;
  editView = false;
  // additional DO location results table 
  showDoLocationResults = false;
  showBookingResults = false;
  hideBookingsButtonText = false;
  noInputView = false;
  filteredCarResultsView = false;
  responseCarId = 0;
  InitialCarDiscoverView  = false;
  showTableChooseCarText = false;
  chosenRowModifyPointer = 0;
  previousChosenRowModifyPointer = 0;
  
  carResultsDataSource = new CarDiscoverResultsTableDataSource();
  doLocationResultsDataSource = new CarDiscoverResultsTableDataSource();
  bookingResultsDataSource = new CarDiscoverResultsTableDataSource();
  @ViewChild('bookingsResults') bookingResultsTableComponent!: ResultsTableComponent;
  @ViewChild('carResults') carResultsTableComponent!: ResultsTableComponent;
  @ViewChild('doLocationResults') doLocationResults!: ResultsTableComponent;
  @ViewChild('cardiscoverForm') cardiscoverForm!: CardiscoverFormComponent;
  @ViewChild('carForm') addCarFormComponentChild!: AddCarFormComponent;

  temporaryCarForm!:FormGroup;
  constructor(
    private formBuilder: FormBuilder, 
    private carDiscover: carDiscoverHTTPService,
    private locationService: locationHttpService,
    private reservationService: ReservationHTTPService
    ) {}

  /* 
  *
  * Functions for toggling views
  *
  */


  filterForSelected(){
    if(this.showTable){
      if(this.carResultsTableComponent.showTable && this.filteredCarResultsView == false){
        this.carResultsUnfiltered = this.carResultsTableComponent.dataSource.getData();
        this.carResultsTableComponent.dataSource.setData([...this.rowsClicked]);
        this.carResultsTableComponent.displayedColumns = Object.keys(this.rowsClicked[0]);
        this.filteredCarResultsView = true;
        this.clearSelected();

      } 
      else {
        this.rowsClicked = [];
        this.carResultsTableComponent.dataSource.setData(this.carResultsUnfiltered);
        this.filteredCarResultsView = false;
        this.clearSelected();
      }
    }
  }


  clearSelected(){
    // reset the clicked rows to not selected state and clear the array
    let rows = document.querySelectorAll(".added-to-row");
    rows.forEach(row => {      
      row.classList.add("not-chosen-hover-state")
      row.classList.remove("added-to-row")
    })
    this.rowsClicked = [];
  }

  // Shows all cars results table
  toggleEditView(){
    if(this.editView) {
      // Done editing, reset view
      this.editView = false;
      if(this.noResult){
        this.noResult = false;
      }
      // Reset the forms "clear selected" button
      if(this.addCarFormComponentChild){
        this.addCarFormComponentChild.clearSelectedView = false;
      }
      
      this.showTable = false;
      this.carResultsDataSource.setData([]);
      this.displayedColumns = [];
      this.noInputView = false;
      this.insertNewCarTextHeader = true;
      this.rowsClicked = [];
      this.addCarView = false;
      this.showCarDiscoverForm = true;
    } 
    else {
      this.noInputView = false;
      this.editView = true;
      this.carDiscover.getAllCars().subscribe((response: any) => {
        if( Object.keys(response).length === 0){
          if(this.noResult === true){
            document.body.querySelector(".shake")?.getAnimations()[0].play();
          }
          this.noResult = true;  
          if(this.showTable){
            this.showTable = false;
          }
          return;
        }
        // Show table results with all cars response
        this.carResultsTableComponent.dataSource.setData([...response]);

        this.carResultsTableComponent.displayedColumns = Object.keys(response[0]);
        // Ensure the car's ID is always at the front if present
        let idPos = this.carResultsTableComponent.displayedColumns.indexOf("id")
        if(idPos !== -1){
          this.carResultsTableComponent.displayedColumns.splice(idPos, 1);
          this.carResultsTableComponent.displayedColumns.unshift("id");
        }
        // This will show only the car results table with controls,  
        // showDoLocationResults needs to be set to true to also show doLocation results table
        this.showTable = true;
        this.noResult = false;  
        this.showCarDiscoverForm = false;
      })
    }      
  }


  // Shows add car form 
  toggleAddCarView(){
    if(this.addCarView){
      this.addCarView = false;
      if(this.editView === false){
        this.showCarDiscoverForm = true;
      }
      if(this.addCarFormComponentChild){
        this.addCarFormComponentChild.editView = true;
        this.addCarFormComponentChild.clearSelectedView = true;
      }
    } else {
      this.addCarView = true;
      this.showCarDiscoverForm = false;
      if(this.addCarFormComponentChild){
        this.addCarFormComponentChild.editView = false;
        this.addCarFormComponentChild.clearSelectedView = false;
      }
    }
  }
  

  getAvaliableCars(emittedData: any){
    const formData = emittedData
    this.locationService.getCars(formData.doLocation, formData.puLocation, formData.doDate, formData.puDate).subscribe((response: any) => {
      // if nothing gets returned
      if( Object.keys(response).length === 0){
        if(this.noInputView) this.noInputView = false
        if(this.noResult === true){
          document.body.querySelector(".shake")?.getAnimations()[0].play();
        }
        this.noResult = true;  
        if(this.showTable){
          this.showTable = false;    
        }
        return;
      }
      // Show table results with cars response based on carDiscover form
      this.carResultsDataSource.setData([...response]);
      this.displayedColumns = Object.keys(response[0]);
      // Ensure the car's ID is always at the front if present
      let idPos = this.displayedColumns.indexOf("id")
      if(idPos !== -1){
        this.displayedColumns.splice(idPos, 1);
        this.displayedColumns.unshift("id");
      }
      this.showTable = true;
      this.noResult = false;  
      this.noInputView = false;
      this.showTableChooseCarText = true;
    })
    // reset the carform because the form data somehow gets reset after the getCars
    // method returns
    this.cardiscoverForm = emittedData
  }

  clickedRow(emittedData: any){
    let event = emittedData.event;
    let row = emittedData.row;
    this.rowsClicked.push(row)

    // let carIDAlreadyChosen = this.rowsClicked.indexOf(row.id);
    if(this.showTableChooseCarText){
      this.showTableChooseCarText = false;
      this.InitialCarDiscoverView = true;
    }
    // if(carIDAlreadyChosen !== -1){
    //   this.rowsClicked.splice(carIDAlreadyChosen, 1)

    // } else {
    //   this.rowsClicked.push(row.id)
    
    // }
  }

  getAllBookings(){
    if(this.showBookingResults){
      this.bookingResultsDataSource.setData([]);
      this.bookingResultsTableComponent.showTable = false;
      this.noResult = false;  
      this.showCarDiscoverForm = true;
      this.hideBookingsButtonText = false;
      this.showBookingResults = false
      this.showTable = false
      return
    }
    this.noInputView = false;
    this.showBookingResults = true;
    // this.editView = true;
    this.reservationService.getAllBookings().subscribe((response: any) => {
      if( Object.keys(response).length === 0){
        if(this.noResult === true){
          document.body.querySelector(".shake")?.getAnimations()[0].play();
        }
        this.noResult = true;  
        if(this.showBookingResults){
          this.showBookingResults = false;
        }
        return;
      }
      // Show table results with all cars response
      this.bookingResultsDataSource.setData([...response]);
      // bookingResultsDataSource = new CarDiscoverResultsTableDataSource();
      // @ViewChild('bookingsResults') bookingResultsTableComponent!: ResultsTableComponent;
      this.bookingResultsTableComponent.displayedColumns = Object.keys(response[0]);
      // Ensure the car's ID is always at the front if present
      let idPos = this.bookingResultsTableComponent.displayedColumns.indexOf("id")
      if(idPos !== -1){
        this.bookingResultsTableComponent.displayedColumns.splice(idPos, 1);
        this.bookingResultsTableComponent.displayedColumns.unshift("id");
      }
      // This will show only the car results table with controls,  
      // showDoLocationResults needs to be set to true to also show doLocation results table
      this.bookingResultsTableComponent.showTable = true;
      this.noResult = false;  
      this.showCarDiscoverForm = false;
      this.hideBookingsButtonText = true;
      this.showTable = true;
    })

  }

  
  deleteAllCars(){
    this.carDiscover.deleteAllCars().subscribe()
  }

  bookCar(){
    let formData = this.cardiscoverForm.carDiscoverForm.value;
    console.log("this is the payload", formData.doLocation, formData.puLocation, 
      formData.doDate, formData.puDate, this.rowsClicked[0].id)
    this.reservationService.bookCar(formData.doLocation, formData.puLocation, 
      formData.doDate, formData.puDate, this.rowsClicked[0].id).subscribe((response: any) => {
      console.log("CAR BOOKED!!")})
  }

  modify(){
    if(this.editView){
      if(this.addCarFormComponentChild){
        if(this.rowsClicked.length > 0){
          // Will allow using Next & Previous button
          this.previousChosenRowModifyPointer = this.chosenRowModifyPointer
          let editCar = this.rowsClicked[this.chosenRowModifyPointer];
          this.addCarFormComponentChild.editView = true;
          this.addCarFormComponentChild.clearSelectedView = true;
          this.carDiscover.getCar(editCar.id).subscribe(
            (responseData:any) => {
              if( Object.keys(responseData).length === 0){
                console.error("Couldn't get car ID", editCar)
                return;
              }
              if(this.addCarFormComponentChild){
                if(this.temporaryCarForm){
                  this.addCarFormComponentChild.carForm = this.temporaryCarForm;
                }
                this.addCarFormComponentChild.carForm.patchValue({
                  id: Number.parseInt(responseData.id),
                  brand: responseData.brand,
                  dropoffTime: new Date(Date.parse(responseData.dropoffTime)),
                  location: responseData.location,
                  model: responseData.model,
                  pickupTime: new Date(Date.parse(responseData.pickupTime)),
                  rate: responseData.rate,
                  size: responseData.size,
                  supplier: responseData.supplier
                });
                console.table(responseData);
                if('reservation_id' in responseData){
                  this.addCarFormComponentChild.carForm.patchValue({
                    reservation_id: Number.parseInt(responseData.reservation_id),
                  });
                  this.addCarFormComponentChild.reservation_id = responseData.reservation_id;
                } else {
                  this.addCarFormComponentChild.carForm.removeControl('reservation_id');
                  this.addCarFormComponentChild.reservation_id = -1;
                }
                this.addCarFormComponentChild.location = responseData.location;
              }else {
                console.error("NOT RENDERED YET??!!!")

              }
              console.log(this.addCarFormComponentChild.carForm.value)
              this.responseCarId = parseInt(responseData.id)
            }
          );
          this.addCarView = true;
          this.editCarTextHeader = true;
          this.insertNewCarTextHeader = false;
          window.scroll({top: 0, behavior: "smooth"})
        }
      }
      else {
        console.error("Component hasn't rendered or is null!")
        console.error(this.addCarFormComponentChild)
      }
    }
    if(this.showBookingResults){
      if(this.addCarFormComponentChild){
        if(this.rowsClicked.length > 0){
          // Will allow using Next & Previous button
          this.previousChosenRowModifyPointer = this.chosenRowModifyPointer
          let editBooking = this.rowsClicked[this.chosenRowModifyPointer];
          this.addCarFormComponentChild.editView = true;
          // this.addCarFormComponentChild.clearSelectedView = true;
          this.reservationService.getBooking(editBooking.reservation_id).subscribe(
            (responseData:any) => {
              if( Object.keys(responseData).length === 0){
                console.error("Couldn't get booking ID", editBooking)
                return;
              }
              if(this.addCarFormComponentChild){
              console.log("this is the response data", responseData)
                this.addCarFormComponentChild.carForm.patchValue({
                  location: null,
                  id: Number.parseInt(responseData.id),
                  reservation_id: Number.parseInt(responseData.reservation_id),
                  dropoffTime: new Date(Date.parse(responseData.dropoffTime)),
                  pickupTime: new Date(Date.parse(responseData.pickupTime)),
                });
              }else {
                console.error("NOT RENDERED YET??!!!")

              }
              this.temporaryCarForm = this.addCarFormComponentChild.carForm;
              console.log(this.addCarFormComponentChild.carForm.value)
              
              this.responseCarId = parseInt(responseData.id)
            }
          );
          // this.addCarFormComponentChild.carForm.removeControl('location');
          this.addCarFormComponentChild.location = '';

          this.addCarView = true;
          // this.editCarTextHeader = true;
          this.insertNewCarTextHeader = false;
          window.scroll({top: 0, behavior: "smooth"})
        }
      }
      else {
        console.error("Component hasn't rendered or is null!")
        console.error(this.addCarFormComponentChild)
      }
    }
  }

  deleteRows(){}



}
