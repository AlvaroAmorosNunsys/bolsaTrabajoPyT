import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHistorialPosicion } from 'app/shared/model/historial-posicion.model';

type EntityResponseType = HttpResponse<IHistorialPosicion>;
type EntityArrayResponseType = HttpResponse<IHistorialPosicion[]>;

@Injectable({ providedIn: 'root' })
export class HistorialPosicionService {
  public resourceUrl = SERVER_API_URL + 'api/historial-posicions';

  constructor(protected http: HttpClient) {}

  create(historialPosicion: IHistorialPosicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historialPosicion);
    return this.http
      .post<IHistorialPosicion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(historialPosicion: IHistorialPosicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historialPosicion);
    return this.http
      .put<IHistorialPosicion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHistorialPosicion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHistorialPosicion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(historialPosicion: IHistorialPosicion): IHistorialPosicion {
    const copy: IHistorialPosicion = Object.assign({}, historialPosicion, {
      fechaCambio:
        historialPosicion.fechaCambio && historialPosicion.fechaCambio.isValid()
          ? historialPosicion.fechaCambio.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaCambio = res.body.fechaCambio ? moment(res.body.fechaCambio) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((historialPosicion: IHistorialPosicion) => {
        historialPosicion.fechaCambio = historialPosicion.fechaCambio ? moment(historialPosicion.fechaCambio) : undefined;
      });
    }
    return res;
  }
}
