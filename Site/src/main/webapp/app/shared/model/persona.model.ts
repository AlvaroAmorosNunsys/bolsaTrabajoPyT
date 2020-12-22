import { ICandidatura } from 'app/shared/model/candidatura.model';

export interface IPersona {
  id?: number;
  documentoIdentidad?: string;
  nombre?: string;
  apellidos?: string;
  email?: string;
  telefono?: string;
  curriculumContentType?: string;
  curriculum?: any;
  comentarios?: string;
  candidaturas?: ICandidatura[];
}

export class Persona implements IPersona {
  constructor(
    public id?: number,
    public documentoIdentidad?: string,
    public nombre?: string,
    public apellidos?: string,
    public email?: string,
    public telefono?: string,
    public curriculumContentType?: string,
    public curriculum?: any,
    public comentarios?: string,
    public candidaturas?: ICandidatura[]
  ) {}
}
