export interface IUsuario {
  id?: number;
  userLogin?: string;
  userId?: number;
  unidadDeNegocioNombre?: string;
  unidadDeNegocioId?: number;
}

export class Usuario implements IUsuario {
  constructor(
    public id?: number,
    public userLogin?: string,
    public userId?: number,
    public unidadDeNegocioNombre?: string,
    public unidadDeNegocioId?: number
  ) {}
}
