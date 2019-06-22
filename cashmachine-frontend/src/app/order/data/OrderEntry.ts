export class OrderEntry {
  public productCode: string;
  public productName: string;
  public id: number;
  public quantity: number;
  public total: number;


  constructor(productName: string, productCode: string, id: number, quantity: number, total: number) {
    this.productCode = productCode;
    this.productName = productName;
    this.id = id;
    this.quantity = quantity;
    this.total = total;
  }
}
