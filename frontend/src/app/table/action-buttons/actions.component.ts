import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'results-table-actions',
  templateUrl: './actions.component.html',
  styleUrls: ['./actions.component.css']
})
export class ActionsComponent {
  @Output() clearSelectedClick: EventEmitter<any> = new EventEmitter();
  @Output() deleteAllCarsClick: EventEmitter<any> = new EventEmitter();
  @Output() bookCarClick: EventEmitter<any> = new EventEmitter();
  @Output() modifyClick: EventEmitter<any> = new EventEmitter();
  @Output() deleteRowsClick: EventEmitter<any> = new EventEmitter();

  @Input() addCarView = false;
  @Input() editView = false;
  @Input() lengthOfRows: number = 0;
  @Input() showStartCarReservationView: boolean = true;
  @Input() showControls: boolean = true;

  clearSelected(){
    this.clearSelectedClick.emit()

  }

  
  deleteAllCars(){
  this.deleteAllCarsClick.emit()
  }

  bookCar(){
    this.bookCarClick.emit()
    
  }

  modify(){
    this.modifyClick.emit()

  }

  deleteRows(){
    this.deleteRowsClick.emit()

  }


}
