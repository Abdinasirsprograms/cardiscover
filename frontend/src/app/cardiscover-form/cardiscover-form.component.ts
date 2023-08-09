import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { map, Observable } from 'rxjs';
import { carDiscoverHTTPService } from 'src/car-discover-http.service';

@Component({
  selector: 'cardiscover-form',
  templateUrl: './cardiscover-form.component.html',
  styleUrls: ['./cardiscover-form.component.css']
})

export class CardiscoverFormComponent implements OnInit {
  @Output() formSubmitted: EventEmitter<any> = new EventEmitter();
  @Output() filterForPickUpLocationName: EventEmitter<any> = new EventEmitter();
  @Output() filterForDropoffLocationName: EventEmitter<any> = new EventEmitter();
  @Input() filteredPickupOptions!: Observable<String[]>;
  @Input() filteredDropoffOptions!: Observable<String[]>;
  carDiscoverForm!: FormGroup;

  puDate!: Date;
  doDate!: Date;
  doLocation: String = '';
  puLocation: String = '';
  noInputView = false;
  value: any;

  constructor(
    private formBuilder: FormBuilder
  ){};


  getAvaliableCars(){

    const formData = this.carDiscoverForm.value;
    if(formData.doLocation === "" && formData.puLocation === ""){
      this.noInputView = true;
      return
    }
    this.formSubmitted.emit(formData);
  }

  puLocationInput(locationInput: Event){
    this.filterForPickUpLocationName.emit(locationInput)
  }

  doLocationInput(locationInput: Event){
    this.filterForDropoffLocationName.emit(locationInput)
  }
  ngOnInit(): void {
    this.carDiscoverForm = this.formBuilder.group({
        doDate: [DatePipe],
        doLocation: ['',Validators.required],
        puLocation: ['',Validators.required],
        puDate: [DatePipe],
      });
    };

}
