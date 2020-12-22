import { Moment } from 'moment';

export interface IHistorialCandidatura {
  id?: number;
  fechaCambio?: Moment;
  posicionTitulo?: string;
  posicionId?: number;
  candidaturaId?: number;
  estadoCandidaturaNombre?: string;
  estadoCandidaturaId?: number;
}

export class HistorialCandidatura implements IHistorialCandidatura {
  constructor(
    public id?: number,
    public fechaCambio?: Moment,
    public posicionTitulo?: string,
    public posicionId?: number,
    public candidaturaId?: number,
    public estadoCandidaturaNombre?: string,
    public estadoCandidaturaId?: number
  ) {}
}
