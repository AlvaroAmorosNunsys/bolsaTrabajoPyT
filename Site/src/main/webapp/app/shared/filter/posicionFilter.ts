import { Moment } from "moment";

export class PosicionFilter {
    constructor(   
    public id?: number,
    public titulo?: string,
    public descripcion?: string,
    public numeroPuestos?: number,
    public salarioMinimo?: number,
    public salarioMaximo?: number,
    public minFechaAlta?: Moment,
    public maxFechaAlta?: Moment,
    public minFechaNecesidad?: Moment,
    public maxFechaNecesidad?: Moment,
    public estadoPosicionId?: number,
    public tipoJornadaId?: number,
    public unidadDeNegocioNombre?: string,
    public unidadDeNegocioId?: number
    ){
        
    }

    toMap(): any {
        const map = new Map();
        if (this.titulo !=null) {
            map.set('titulo.contains',this.titulo);
        }
        if(this.descripcion != null) {
            map.set('descripcion.contains',this.descripcion);
        }
        if(this.numeroPuestos!=null) {
            map.set('numeroPuestos.equals',this.numeroPuestos);
        }

        if(this.salarioMinimo!=null){
            map.set('salarioMinimo.greaterOrEqualThan', this.salarioMinimo);
        }

        if (this.salarioMaximo!=null) {
            map.set('salarioMaximo.lessOrEqualThan',this.salarioMaximo);
        }

        if(this.minFechaAlta!=null) {
            map.set('fechaAlta.greaterOrEqualThan',this.minFechaAlta);
        }
        if(this.maxFechaAlta!=null){
            map.set('fechaAlta.lessOrEqualThan', this.maxFechaAlta);
        }
        if(this.minFechaNecesidad!=null){
            map.set('fechaNecesidad.greaterOrEqualThan', this.minFechaNecesidad);
        }
        if(this.maxFechaNecesidad!=null){
            map.set('fechaNecesidad.lessOrEqualThan', this.maxFechaNecesidad);
        }
        if(this.estadoPosicionId!=null){
            map.set('estadoPosicionId.equals', this.estadoPosicionId);
        }
        if(this.tipoJornadaId!=null){
            map.set('tipoJornadaId.equals', this.tipoJornadaId);
        }
        if (this.unidadDeNegocioNombre!=null) {
            map.set('unidadDeNegocioNombre.equals',this.unidadDeNegocioNombre);
        }
        if (this.unidadDeNegocioId!=null) {
            map.set('unidadDeNegocioId.equals', this.unidadDeNegocioId)
        }


        return map;
    }
}