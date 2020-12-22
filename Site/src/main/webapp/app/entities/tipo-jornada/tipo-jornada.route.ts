import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITipoJornada, TipoJornada } from 'app/shared/model/tipo-jornada.model';
import { TipoJornadaService } from './tipo-jornada.service';
import { TipoJornadaComponent } from './tipo-jornada.component';
import { TipoJornadaDetailComponent } from './tipo-jornada-detail.component';
import { TipoJornadaUpdateComponent } from './tipo-jornada-update.component';

@Injectable({ providedIn: 'root' })
export class TipoJornadaResolve implements Resolve<ITipoJornada> {
  constructor(private service: TipoJornadaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITipoJornada> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tipoJornada: HttpResponse<TipoJornada>) => {
          if (tipoJornada.body) {
            return of(tipoJornada.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TipoJornada());
  }
}

export const tipoJornadaRoute: Routes = [
  {
    path: '',
    component: TipoJornadaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'TipoJornadas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TipoJornadaDetailComponent,
    resolve: {
      tipoJornada: TipoJornadaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TipoJornadas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TipoJornadaUpdateComponent,
    resolve: {
      tipoJornada: TipoJornadaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TipoJornadas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TipoJornadaUpdateComponent,
    resolve: {
      tipoJornada: TipoJornadaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TipoJornadas',
    },
    canActivate: [UserRouteAccessService],
  },
];
