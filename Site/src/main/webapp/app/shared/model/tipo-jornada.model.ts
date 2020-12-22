export interface ITipoJornada {
  id?: number;
  nombre?: string;
}

export class TipoJornada implements ITipoJornada {
  constructor(public id?: number, public nombre?: string) {}
}
