export class Order {
    constructor(
      public billNo: number,
      public restName: string,
      public restId: number,
      public totalProducts: number,
      public totalAmount: number,
      public paidStatus: string
    ) {}
}
