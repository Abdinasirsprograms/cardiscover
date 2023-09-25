import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'reservation-component',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  @Output() getAllReservations: EventEmitter<any> = new EventEmitter();
  @Input() hideReservationsButtonText: boolean = false;
  emittGetReservations(){
    this.getAllReservations.emit()
    
  }
}
