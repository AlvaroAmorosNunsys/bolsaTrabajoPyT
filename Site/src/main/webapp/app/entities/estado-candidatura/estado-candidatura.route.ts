import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadoCandidatura, EstadoCandidatura } from 'app/shared/model/estado-candidatura.model';
import { EstadoCandidaturaService } from './estado-candidatura.service';
import { EstadoCandidaturaComponent } from './estado-candidatura.component';
import { EstadoCandidaturaDetailComponent } from './estado-candidatura-detail.component';
import { EstadoCandidaturaUpdateComponent } from './estado-candidatura-update.component';

@Injectable({ providedIn: 'root' })
export class EstadoCandidaturaResolve implements Resolve<IEstadoCandidatura> {
  constructor(private service: EstadoCandidaturaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadoCandidatura> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadoCandidatura: HttpResponse<EstadoCandidatura>) => {
          if (estadoCandidatura.body) {
            return of(estadoCandidatura.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadoCandidatura());
  }
}

export const estadoCandidaturaRoute: Routes = [
  {
    path: '',
    component: EstadoCandidaturaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'EstadoCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EstadoCandidaturaDetailComponent,
    resolve: {
      estadoCandidatura: EstadoCandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EstadoCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EstadoCandidaturaUpdateComponent,
    resolve: {
      estadoCandidatura: EstadoCandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EstadoCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EstadoCandidaturaUpdateComponent,
    resolve: {
      estadoCandidatura: EstadoCandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EstadoCandidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
];
