import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AddCarFormComponent } from 'src/app/add-car-form/car-form.component';
import { carDiscoverHTTPService } from 'src/car-discover-http.service';
import { CarDiscoverResultsTableDataSource } from 'src/car-discover-results-table.datasource';

@Component({
  selector: 'results-table',
  templateUrl: './results-table.component.html',
  styleUrls: ['./results-table.component.css']
})
export class ResultsTableComponent implements OnInit {
  @Output() rowClick: EventEmitter<any> = new EventEmitter();

  rowsClicked: any[] = [];
  @Input() displayedColumns:string[] = [];
  @Input() showTable = false;
  @Input() filteredCarResultsView = false;
  @Input() dataSource: CarDiscoverResultsTableDataSource =  new CarDiscoverResultsTableDataSource();

  constructor(
    private carDiscover: carDiscoverHTTPService,
  ){}
  ngOnInit() {};

  
  clickedRow(event:any, row:any){

    let tableRowTarget = event.target.parentNode;
    let tableRowTargetClassList = tableRowTarget.classList
    tableRowTargetClassList.toggle("not-chosen-hover-state")
    tableRowTargetClassList.toggle("added-to-row")

    this.rowClick.emit({event, row});
  }
}
