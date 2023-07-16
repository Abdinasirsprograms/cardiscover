import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { carDiscoverHTTPService } from 'src/car-discover-http.service';

@Component({
  selector: 'cardiscover-form',
  templateUrl: './cardiscover-form.component.html',
  styleUrls: ['./cardiscover-form.component.css']
})

export class CardiscoverFormComponent implements OnInit {
  @Output() formSubmitted: EventEmitter<any> = new EventEmitter();

  carDiscoverForm!: FormGroup;

  puDate?: Date;
  doDate?: Date;
  doLocation: String = '';
  puLocation: String = '';
  noInputView = false;
  value: any;

  constructor(
    private formBuilder: FormBuilder
  ){};

  getAvaliableCars(){
    // this.LocationDataService.getLocationCars(this.doLocation, this.puLocation).subscribe(data => {
    //   this.dataSource.setData(data)
    //   this.showTable = true;
    // })
    const formData = this.carDiscoverForm.value;
    if(formData.doLocation === "" && formData.puLocation === ""){
      this.noInputView = true;
      return
    }
    this.formSubmitted.emit(formData);
  }
  
  ngOnInit(): void {
    this.carDiscoverForm = this.formBuilder.group({
        doDate: [DatePipe],
        doLocation: ['',Validators.required],
        puLocation: ['',Validators.required],
        puDate: [DatePipe],
      });
  }
}
