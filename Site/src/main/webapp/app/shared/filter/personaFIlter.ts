
export class PersonaFilter {
    constructor(   
      public id?: number,
      public documentoIdentidad?: string,
      public nombre?: string,
      public apellidos?: string,
      public email?: string,
      public telefono?: string,
      public comentarios?: string,
  ){
        
    }

    toMap(): any {
        
        const map = new Map();
        if (this.id !=null) {
            map.set('id.equals',this.id);
        }
        if(this.documentoIdentidad!=null) {
            map.set('documentoIdentidad.equals', this.documentoIdentidad)
        }
        if(this.nombre!=null) {
            map.set('nombre.contains', this.nombre);
        }
        if(this.apellidos!=null) {
            map.set('apellidos.contains', this.apellidos)
        }
        if(this.email!=null ){
            map.set('apellidos.contains', this.email);
        }
        if(this.telefono!=null){
            map.set('telefono.contains', this.telefono);
        }
        if(this.comentarios!=null){
            map.set('comentarios.contains', this.comentarios);
        }


        return map;
    }
}