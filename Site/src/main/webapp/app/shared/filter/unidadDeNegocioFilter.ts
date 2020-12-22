
export class UnidadDeNegocioFilter {
    constructor(   
       public id?: number,
       public nombre?: string
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


        return map;
    }
}