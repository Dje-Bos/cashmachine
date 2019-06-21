export class OrderEntry {
  public productCode: string;
  public productName: string;
  public no: number;
  public quantity: number;
  public price: number;


  constructor(productName: string, productCode: string, no: number, quantity: number, price: number) {
    this.productCode = productCode;
    this.productName = productName;
    this.no = no;
    this.quantity = quantity;
    this.price = price;
  }
}
