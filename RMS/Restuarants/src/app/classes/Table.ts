export class Table {
    constructor(
        public restId: number,
        public restName: string,
        public capacity: number,
        public availableTable: number,
        public availabilty: boolean
    ) {}
}
