import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICandidatura, Candidatura } from 'app/shared/model/candidatura.model';
import { CandidaturaService } from './candidatura.service';
import { CandidaturaComponent } from './candidatura.component';
import { CandidaturaDetailComponent } from './candidatura-detail.component';
import { CandidaturaUpdateComponent } from './candidatura-update.component';

@Injectable({ providedIn: 'root' })
export class CandidaturaResolve implements Resolve<ICandidatura> {
  constructor(private service: CandidaturaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICandidatura> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((candidatura: HttpResponse<Candidatura>) => {
          if (candidatura.body) {
            return of(candidatura.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Candidatura());
  }
}

export const candidaturaRoute: Routes = [
  {
    path: '',
    component: CandidaturaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Candidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CandidaturaDetailComponent,
    resolve: {
      candidatura: CandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Candidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CandidaturaUpdateComponent,
    resolve: {
      candidatura: CandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Candidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CandidaturaUpdateComponent,
    resolve: {
      candidatura: CandidaturaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Candidaturas',
    },
    canActivate: [UserRouteAccessService],
  },
];
