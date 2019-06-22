import {OrderEntry} from './OrderEntry';

export class Order {
  creationTime: string;
  id: string;
  status: string;
  total: number;
  cashierName: string;
  entries: OrderEntry[];
}
