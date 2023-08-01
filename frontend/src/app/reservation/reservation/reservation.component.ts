import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'reservation-component',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  @Output() getAllBookings: EventEmitter<any> = new EventEmitter();
  @Input() hideBookingsButtonText: boolean = false;
  emittGetBookings(){
    this.getAllBookings.emit()
    
  }
}
