import { IHistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

export interface IEstadoCandidatura {
  id?: number;
  nombre?: string;
  porDefecto?: boolean;
  historialCandidaturas?: IHistorialCandidatura[];
}

export class EstadoCandidatura implements IEstadoCandidatura {
  constructor(
    public id?: number,
    public nombre?: string,
    public porDefecto?: boolean,
    public historialCandidaturas?: IHistorialCandidatura[]
  ) {
    this.porDefecto = this.porDefecto || false;
  }
}
