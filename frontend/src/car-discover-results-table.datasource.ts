import { DataSource } from '@angular/cdk/collections';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { carFormInterface } from './car-form.interface';
import { MatTableDataSource } from '@angular/material/table';

@Injectable({
  providedIn: 'root'
})
export class CarDiscoverResultsTableDataSource extends DataSource<any> {
    private dataSource: MatTableDataSource<any>;

    constructor() {
        super();
        this.dataSource = new MatTableDataSource<any>();
    }
    setData(data: any) {
        this.dataSource.data = data;
    }
    getData() {
        return this.dataSource.data;
    }
    connect(): Observable<carFormInterface[]> {
        return this.dataSource.connect()
    }
    disconnect() {
        return this.dataSource.disconnect()
    }
}