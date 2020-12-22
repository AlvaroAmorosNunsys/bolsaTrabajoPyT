import { IPosicion } from 'app/shared/model/posicion.model';
import { IUsuario } from 'app/shared/model/usuario.model';

export interface IUnidadDeNegocio {
  id?: number;
  nombre?: string;
  posicions?: IPosicion[];
  usuarios?: IUsuario[];
}

export class UnidadDeNegocio implements IUnidadDeNegocio {
  constructor(public id?: number, public nombre?: string, public posicions?: IPosicion[], public usuarios?: IUsuario[]) {}
}
