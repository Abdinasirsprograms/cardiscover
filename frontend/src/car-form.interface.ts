import { DatePipe } from "@angular/common"

export interface carFormInterface {
    id: number
    brand: String
    dropoffTime: DatePipe
    location: String
    model: String
    pickupTime: DatePipe
    rate: Number
    size: String
    supplier: String
  }