import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadoPosicion } from 'app/shared/model/estado-posicion.model';

type EntityResponseType = HttpResponse<IEstadoPosicion>;
type EntityArrayResponseType = HttpResponse<IEstadoPosicion[]>;

@Injectable({ providedIn: 'root' })
export class EstadoPosicionService {
  public resourceUrl = SERVER_API_URL + 'api/estado-posicions';

  constructor(protected http: HttpClient) {}

  create(estadoPosicion: IEstadoPosicion): Observable<EntityResponseType> {
    return this.http.post<IEstadoPosicion>(this.resourceUrl, estadoPosicion, { observe: 'response' });
  }

  update(estadoPosicion: IEstadoPosicion): Observable<EntityResponseType> {
    return this.http.put<IEstadoPosicion>(this.resourceUrl, estadoPosicion, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadoPosicion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadoPosicion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
