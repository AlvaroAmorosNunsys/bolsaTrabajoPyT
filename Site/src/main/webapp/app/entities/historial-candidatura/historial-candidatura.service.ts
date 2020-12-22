import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

type EntityResponseType = HttpResponse<IHistorialCandidatura>;
type EntityArrayResponseType = HttpResponse<IHistorialCandidatura[]>;

@Injectable({ providedIn: 'root' })
export class HistorialCandidaturaService {
  public resourceUrl = SERVER_API_URL + 'api/historial-candidaturas';

  constructor(protected http: HttpClient) {}

  create(historialCandidatura: IHistorialCandidatura): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historialCandidatura);
    return this.http
      .post<IHistorialCandidatura>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(historialCandidatura: IHistorialCandidatura): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historialCandidatura);
    return this.http
      .put<IHistorialCandidatura>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHistorialCandidatura>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHistorialCandidatura[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(historialCandidatura: IHistorialCandidatura): IHistorialCandidatura {
    const copy: IHistorialCandidatura = Object.assign({}, historialCandidatura, {
      fechaCambio:
        historialCandidatura.fechaCambio && historialCandidatura.fechaCambio.isValid()
          ? historialCandidatura.fechaCambio.format(DATE_FORMAT)
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
      res.body.forEach((historialCandidatura: IHistorialCandidatura) => {
        historialCandidatura.fechaCambio = historialCandidatura.fechaCambio ? moment(historialCandidatura.fechaCambio) : undefined;
      });
    }
    return res;
  }
}
