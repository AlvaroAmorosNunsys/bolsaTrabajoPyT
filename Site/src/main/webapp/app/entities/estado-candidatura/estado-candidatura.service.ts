import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadoCandidatura } from 'app/shared/model/estado-candidatura.model';

type EntityResponseType = HttpResponse<IEstadoCandidatura>;
type EntityArrayResponseType = HttpResponse<IEstadoCandidatura[]>;

@Injectable({ providedIn: 'root' })
export class EstadoCandidaturaService {
  public resourceUrl = SERVER_API_URL + 'api/estado-candidaturas';

  constructor(protected http: HttpClient) {}

  create(estadoCandidatura: IEstadoCandidatura): Observable<EntityResponseType> {
    return this.http.post<IEstadoCandidatura>(this.resourceUrl, estadoCandidatura, { observe: 'response' });
  }

  update(estadoCandidatura: IEstadoCandidatura): Observable<EntityResponseType> {
    return this.http.put<IEstadoCandidatura>(this.resourceUrl, estadoCandidatura, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadoCandidatura>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadoCandidatura[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
