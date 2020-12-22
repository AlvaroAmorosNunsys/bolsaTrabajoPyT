import { IHistorialPosicion } from 'app/shared/model/historial-posicion.model';

export interface IEstadoPosicion {
  id?: number;
  nombre?: string;
  porDefecto?: boolean;
  historialPosicions?: IHistorialPosicion[];
}

export class EstadoPosicion implements IEstadoPosicion {
  constructor(public id?: number, public nombre?: string, public porDefecto?: boolean, public historialPosicions?: IHistorialPosicion[]) {
    this.porDefecto = this.porDefecto || false;
  }
}
