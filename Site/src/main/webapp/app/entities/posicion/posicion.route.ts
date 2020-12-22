import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPosicion, Posicion } from 'app/shared/model/posicion.model';
import { PosicionService } from './posicion.service';
import { PosicionComponent } from './posicion.component';
import { PosicionDetailComponent } from './posicion-detail.component';
import { PosicionUpdateComponent } from './posicion-update.component';

@Injectable({ providedIn: 'root' })
export class PosicionResolve implements Resolve<IPosicion> {
  constructor(private service: PosicionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPosicion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((posicion: HttpResponse<Posicion>) => {
          if (posicion.body) {
            return of(posicion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Posicion());
  }
}

export const posicionRoute: Routes = [
  {
    path: '',
    component: PosicionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Posicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PosicionDetailComponent,
    resolve: {
      posicion: PosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Posicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PosicionUpdateComponent,
    resolve: {
      posicion: PosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Posicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PosicionUpdateComponent,
    resolve: {
      posicion: PosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Posicions',
    },
    canActivate: [UserRouteAccessService],
  },
];
