
export class HistorialPosicionFilter {
    constructor(   
       public id?: number,
       public nombre?: string,
       public porDefecto?: boolean
  ){
        
    }

    toMap(): any {
        
        const map = new Map();
        if (this.id !=null) {
            map.set('id.equals',this.id);
        }
        if(this.nombre != null) {
            map.set('nombre.contains',this.nombre);
        }
        if(this.porDefecto =! null ){
            map.set('porDefecto.equals', this.porDefecto)
        }


        return map;
    }
}