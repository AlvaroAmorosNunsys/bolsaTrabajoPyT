import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadoPosicion, EstadoPosicion } from 'app/shared/model/estado-posicion.model';
import { EstadoPosicionService } from './estado-posicion.service';
import { EstadoPosicionComponent } from './estado-posicion.component';
import { EstadoPosicionDetailComponent } from './estado-posicion-detail.component';
import { EstadoPosicionUpdateComponent } from './estado-posicion-update.component';

@Injectable({ providedIn: 'root' })
export class EstadoPosicionResolve implements Resolve<IEstadoPosicion> {
  constructor(private service: EstadoPosicionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadoPosicion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadoPosicion: HttpResponse<EstadoPosicion>) => {
          if (estadoPosicion.body) {
            return of(estadoPosicion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadoPosicion());
  }
}

export const estadoPosicionRoute: Routes = [
  {
    path: '',
    component: EstadoPosicionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'EstadoPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EstadoPosicionDetailComponent,
    resolve: {
      estadoPosicion: EstadoPosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EstadoPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EstadoPosicionUpdateComponent,
    resolve: {
      estadoPosicion: EstadoPosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EstadoPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EstadoPosicionUpdateComponent,
    resolve: {
      estadoPosicion: EstadoPosicionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EstadoPosicions',
    },
    canActivate: [UserRouteAccessService],
  },
];
