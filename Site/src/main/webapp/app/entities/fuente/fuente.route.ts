import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFuente, Fuente } from 'app/shared/model/fuente.model';
import { FuenteService } from './fuente.service';
import { FuenteComponent } from './fuente.component';
import { FuenteDetailComponent } from './fuente-detail.component';
import { FuenteUpdateComponent } from './fuente-update.component';

@Injectable({ providedIn: 'root' })
export class FuenteResolve implements Resolve<IFuente> {
  constructor(private service: FuenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFuente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fuente: HttpResponse<Fuente>) => {
          if (fuente.body) {
            return of(fuente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Fuente());
  }
}

export const fuenteRoute: Routes = [
  {
    path: '',
    component: FuenteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Fuentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FuenteDetailComponent,
    resolve: {
      fuente: FuenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fuentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FuenteUpdateComponent,
    resolve: {
      fuente: FuenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fuentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FuenteUpdateComponent,
    resolve: {
      fuente: FuenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fuentes',
    },
    canActivate: [UserRouteAccessService],
  },
];
