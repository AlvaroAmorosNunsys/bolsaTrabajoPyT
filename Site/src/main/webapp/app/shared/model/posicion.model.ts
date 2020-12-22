import { Moment } from 'moment';
import { ICandidatura } from 'app/shared/model/candidatura.model';
import { IHistorialCandidatura } from 'app/shared/model/historial-candidatura.model';
import { IHistorialPosicion } from 'app/shared/model/historial-posicion.model';

export interface IPosicion {
  id?: number;
  titulo?: string;
  descripcion?: string;
  numeroPuestos?: number;
  salarioMinimo?: number;
  salarioMaximo?: number;
  fechaAlta?: Moment;
  fechaNecesidad?: Moment;
  candidaturas?: ICandidatura[];
  historialCandidaturas?: IHistorialCandidatura[];
  historialPosicions?: IHistorialPosicion[];
  estadoPosicionNombre?: string;
  estadoPosicionId?: number;
  tipoJornadaNombre?: string;
  tipoJornadaId?: number;
  unidadDeNegocioNombre?: string;
  unidadDeNegocioId?: number;
}

export class Posicion implements IPosicion {
  constructor(
    public id?: number,
    public titulo?: string,
    public descripcion?: string,
    public numeroPuestos?: number,
    public salarioMinimo?: number,
    public salarioMaximo?: number,
    public fechaAlta?: Moment,
    public fechaNecesidad?: Moment,
    public candidaturas?: ICandidatura[],
    public historialCandidaturas?: IHistorialCandidatura[],
    public historialPosicions?: IHistorialPosicion[],
    public estadoPosicionNombre?: string,
    public estadoPosicionId?: number,
    public tipoJornadaNombre?: string,
    public tipoJornadaId?: number,
    public unidadDeNegocioNombre?: string,
    public unidadDeNegocioId?: number
  ) {}
}
