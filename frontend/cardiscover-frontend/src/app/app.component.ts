import { Component, OnInit, ViewChild } from '@angular/core';
import { AddCarFormComponent } from './add-car-form/car-form.component';
import { CarDiscoverResultsTableDataSource } from '../car-discover-results-table.datasource';
import { carDiscoverHTTPService } from '../car-discover-http.service';
import { FormGroup } from '@angular/forms';
import { ResultsTableComponent } from './table/results-table/results-table.component';
import { CardiscoverFormComponent } from './cardiscover-form/cardiscover-form.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})


export class AppComponent {


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
  noInputView = false;
  filteredCarResultsView = false;
  responseCarId = 0;
  InitialCarDiscoverView  = false;
  showTableChooseCarText = false;
  chosenRowModifyPointer = 0;
  previousChosenRowModifyPointer = 0;
  carResultsDataSource = new CarDiscoverResultsTableDataSource();
  doLocationResultsDataSource = new CarDiscoverResultsTableDataSource();
  @ViewChild('carResults') carResultsTableComponent!: ResultsTableComponent;
  @ViewChild('doLocationResults') doLocationResults!: ResultsTableComponent;
  @ViewChild('cardiscoverForm') cardiscoverForm!: CardiscoverFormComponent;

  addCarFormComponentChild!: AddCarFormComponent;

  constructor(
    private carDiscover: carDiscoverHTTPService,
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
      } 
      else if(this.carResultsTableComponent.showTable && this.filteredCarResultsView){
        this.rowsClicked = [];
        this.carResultsTableComponent.dataSource.setData(this.carResultsUnfiltered);
        this.filteredCarResultsView = false;
      }
    }
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
      if(this.addCarView === false){
        this.showCarDiscoverForm = true;
      }
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
  
  deleteRows(){}


  getAvaliableCars(emittedData: any){
    const formData = emittedData
    this.carDiscover.getCars(formData.doLocation, formData.puLocation, formData.doDate, formData.puDate).subscribe((response: any) => {
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

}
