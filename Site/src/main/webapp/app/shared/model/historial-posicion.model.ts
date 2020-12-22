import { Moment } from 'moment';

export interface IHistorialPosicion {
  id?: number;
  fechaCambio?: Moment;
  estadoPosicionNombre?: string;
  estadoPosicionId?: number;
  posicionTitulo?: string;
  posicionId?: number;
}

export class HistorialPosicion implements IHistorialPosicion {
  constructor(
    public id?: number,
    public fechaCambio?: Moment,
    public estadoPosicionNombre?: string,
    public estadoPosicionId?: number,
    public posicionTitulo?: string,
    public posicionId?: number
  ) {}
}
