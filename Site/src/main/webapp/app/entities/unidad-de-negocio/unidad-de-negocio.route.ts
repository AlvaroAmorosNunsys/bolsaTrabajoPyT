import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUnidadDeNegocio, UnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';
import { UnidadDeNegocioService } from './unidad-de-negocio.service';
import { UnidadDeNegocioComponent } from './unidad-de-negocio.component';
import { UnidadDeNegocioDetailComponent } from './unidad-de-negocio-detail.component';
import { UnidadDeNegocioUpdateComponent } from './unidad-de-negocio-update.component';

@Injectable({ providedIn: 'root' })
export class UnidadDeNegocioResolve implements Resolve<IUnidadDeNegocio> {
  constructor(private service: UnidadDeNegocioService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUnidadDeNegocio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((unidadDeNegocio: HttpResponse<UnidadDeNegocio>) => {
          if (unidadDeNegocio.body) {
            return of(unidadDeNegocio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UnidadDeNegocio());
  }
}

export const unidadDeNegocioRoute: Routes = [
  {
    path: '',
    component: UnidadDeNegocioComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'UnidadDeNegocios',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UnidadDeNegocioDetailComponent,
    resolve: {
      unidadDeNegocio: UnidadDeNegocioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UnidadDeNegocios',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UnidadDeNegocioUpdateComponent,
    resolve: {
      unidadDeNegocio: UnidadDeNegocioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UnidadDeNegocios',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UnidadDeNegocioUpdateComponent,
    resolve: {
      unidadDeNegocio: UnidadDeNegocioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UnidadDeNegocios',
    },
    canActivate: [UserRouteAccessService],
  },
];
