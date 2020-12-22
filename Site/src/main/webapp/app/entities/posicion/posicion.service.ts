import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPosicion } from 'app/shared/model/posicion.model';

type EntityResponseType = HttpResponse<IPosicion>;
type EntityArrayResponseType = HttpResponse<IPosicion[]>;

@Injectable({ providedIn: 'root' })
export class PosicionService {
  public resourceUrl = SERVER_API_URL + 'api/posicions';

  constructor(protected http: HttpClient) {}

  create(posicion: IPosicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(posicion);
    return this.http
      .post<IPosicion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(posicion: IPosicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(posicion);
    return this.http
      .put<IPosicion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPosicion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPosicion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(posicion: IPosicion): IPosicion {
    const copy: IPosicion = Object.assign({}, posicion, {
      fechaAlta: posicion.fechaAlta && posicion.fechaAlta.isValid() ? posicion.fechaAlta.format(DATE_FORMAT) : undefined,
      fechaNecesidad:
        posicion.fechaNecesidad && posicion.fechaNecesidad.isValid() ? posicion.fechaNecesidad.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaAlta = res.body.fechaAlta ? moment(res.body.fechaAlta) : undefined;
      res.body.fechaNecesidad = res.body.fechaNecesidad ? moment(res.body.fechaNecesidad) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((posicion: IPosicion) => {
        posicion.fechaAlta = posicion.fechaAlta ? moment(posicion.fechaAlta) : undefined;
        posicion.fechaNecesidad = posicion.fechaNecesidad ? moment(posicion.fechaNecesidad) : undefined;
      });
    }
    return res;
  }
}
