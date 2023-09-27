import { DatePipe } from "@angular/common"

export interface carFormInterface {
    id: number
    brand: String
    dropoffTime: Date
    location: String
    model: String
    pickupTime: Date
    rate: Number
    size: String
    supplier: String
  }