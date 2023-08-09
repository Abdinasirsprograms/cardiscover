import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCardModule } from '@angular/material/card';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTableModule} from '@angular/material/table';
import { carDiscoverHTTPService } from 'src/car-discover-http.service';
import { CarDiscoverResultsTableDataSource } from 'src/car-discover-results-table.datasource';
import { HttpClientModule } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { AddCarFormComponent } from './add-car-form/car-form.component';
import {MatListModule} from '@angular/material/list';
import {MatNativeDatetimeModule, MatDatetimepickerModule} from  '@mat-datetimepicker/core';
import { ResultsTableComponent } from './table/results-table/results-table.component';
import { ActionsComponent } from './table/action-buttons/actions.component';
import { CardiscoverFormComponent } from './cardiscover-form/cardiscover-form.component';
import { ReservationComponent } from './reservation/reservation/reservation.component'
import {MatAutocompleteModule} from '@angular/material/autocomplete';

@NgModule({
  declarations: [
    AppComponent,
    AddCarFormComponent,
    ResultsTableComponent,
    ActionsComponent,
    CardiscoverFormComponent,
    ReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    ReactiveFormsModule,
    MatGridListModule,
    MatTableModule,
    HttpClientModule,
    FormsModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
      // use this if you want to use native javascript dates and INTL API if available
  MatNativeDatetimeModule,
  // MatMomentDatetimeModule,
  MatDatetimepickerModule,
  MatAutocompleteModule

  ],
  providers: [CarDiscoverResultsTableDataSource, carDiscoverHTTPService],
  bootstrap: [AppComponent]
})
export class AppModule { }
