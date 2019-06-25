import {Product} from '../../order/data/Product';

export interface PageableProducts {
  items: Product[];
  totalCount: number;
}
