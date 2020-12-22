import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHistorialPosicion, HistorialPosicion } from 'app/shared/model/historial-posicion.model';
import { HistorialPosicionService } from './historial-posicion.service';
import { HistorialPosicionComponent } from './historial-posicion.component';
import { HistorialPosicionDetailComponent } from './historial-posicion-detail.component';
import { HistorialPosicionUpdateComponent } from './historial-posicion-update.component';

@Injectable({ providedIn: 'root' })
export class HistorialPosicionResolve implements Resolve<IHistorialPosicion> {
  constructor(private service: HistorialPosicionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHistorialPosicion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((historialPosicion: HttpResponse<HistorialPosicion>) => {
          if (historialPosicion.body) {
            return of(historialPosicion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HistorialPosicion());
  }
}

export const historialPosicionRoute: Routes = [
  {
    path: '',
    component: HistorialPosicionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'HistorialPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HistorialPosicionDetailComponent,
    resolve: {
      historialPosicion: HistorialPosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HistorialPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HistorialPosicionUpdateComponent,
    resolve: {
      historialPosicion: HistorialPosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HistorialPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HistorialPosicionUpdateComponent,
    resolve: {
      historialPosicion: HistorialPosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HistorialPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
];
