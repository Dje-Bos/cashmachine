import {Order} from './Order';

export interface PageableOrders {
  items: Order[];
  totalCount: number;
}
