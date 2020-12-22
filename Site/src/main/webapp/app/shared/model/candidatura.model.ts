import { IHistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

export interface ICandidatura {
  id?: number;
  historialCandidaturas?: IHistorialCandidatura[];
  estadoCandidaturaNombre?: string;
  estadoCandidaturaId?: number;
  fuenteNombre?: string;
  fuenteId?: number;
  posicionTitulo?: string;
  posicionId?: number;
  personaDocumentoIdentidad?: string;
  personaId?: number;
}

export class Candidatura implements ICandidatura {
  constructor(
    public id?: number,
    public historialCandidaturas?: IHistorialCandidatura[],
    public estadoCandidaturaNombre?: string,
    public estadoCandidaturaId?: number,
    public fuenteNombre?: string,
    public fuenteId?: number,
    public posicionTitulo?: string,
    public posicionId?: number,
    public personaDocumentoIdentidad?: string,
    public personaId?: number
  ) {}
}
