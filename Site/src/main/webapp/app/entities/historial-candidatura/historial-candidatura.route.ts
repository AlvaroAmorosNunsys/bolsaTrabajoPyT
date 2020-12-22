import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHistorialCandidatura, HistorialCandidatura } from 'app/shared/model/historial-candidatura.model';
import { HistorialCandidaturaService } from './historial-candidatura.service';
import { HistorialCandidaturaComponent } from './historial-candidatura.component';
import { HistorialCandidaturaDetailComponent } from './historial-candidatura-detail.component';
import { HistorialCandidaturaUpdateComponent } from './historial-candidatura-update.component';

@Injectable({ providedIn: 'root' })
export class HistorialCandidaturaResolve implements Resolve<IHistorialCandidatura> {
  constructor(private service: HistorialCandidaturaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHistorialCandidatura> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((historialCandidatura: HttpResponse<HistorialCandidatura>) => {
          if (historialCandidatura.body) {
            return of(historialCandidatura.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HistorialCandidatura());
  }
}

export const historialCandidaturaRoute: Routes = [
  {
    path: '',
    component: HistorialCandidaturaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'HistorialCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HistorialCandidaturaDetailComponent,
    resolve: {
      historialCandidatura: HistorialCandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HistorialCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HistorialCandidaturaUpdateComponent,
    resolve: {
      historialCandidatura: HistorialCandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HistorialCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HistorialCandidaturaUpdateComponent,
    resolve: {
      historialCandidatura: HistorialCandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HistorialCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
];
