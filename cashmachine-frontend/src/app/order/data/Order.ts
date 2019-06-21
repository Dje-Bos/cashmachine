import {OrderEntry} from './OrderEntry';

export interface Order {
  creationTime: string;
  id: string;
  status: string;
  total: string;
  cashierName: string;
  entries: OrderEntry[];
}
