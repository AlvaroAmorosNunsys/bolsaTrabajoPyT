import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPosicion } from 'app/shared/model/posicion.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PosicionService } from './posicion.service';
import { PosicionDeleteDialogComponent } from './posicion-delete-dialog.component';

import { PosicionFilter } from './../../shared/filter/PosicionFilter';
import { EstadoPosicionService } from '../estado-posicion/estado-posicion.service';
import { TipoJornadaService } from '../tipo-jornada/tipo-jornada.service';
import { IEstadoPosicion } from 'app/shared/model/estado-posicion.model';
import { ITipoJornada } from 'app/shared/model/tipo-jornada.model';
import { UnidadDeNegocioService } from '../unidad-de-negocio/unidad-de-negocio.service';
import { AccountService } from 'app/core/auth/account.service';
import { UsuarioService } from '../usuario/usuario.service';
import { UserService } from 'app/core/user/user.service';
import { IUsuario } from 'app/shared/model/usuario.model';

@Component({
  selector: 'jhi-posicion',
  templateUrl: './posicion.component.html',
})
export class PosicionComponent implements OnInit, OnDestroy {
  posicions?: IPosicion[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  public isCollapsed = false;

  filter!: PosicionFilter;
  estadoPosicionList: IEstadoPosicion[] = []
  tipoJornadaList: ITipoJornada[] = [];

  account!: Account;


  constructor(
    protected posicionService: PosicionService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected estadoPosicionService: EstadoPosicionService,
    protected tipoJornadaService: TipoJornadaService,
    protected unidadDeNegocioService: UnidadDeNegocioService,
    protected accountService: AccountService,
    protected usuarioService: UsuarioService,
    protected userService: UserService
  ) { }

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.accountService.identity().subscribe(account => {
      if (account) {

        this.userService.find(account.login).subscribe(resUser => {
          if (resUser) {
            this.usuarioService.find(resUser.id).subscribe((usuario: HttpResponse<IUsuario>) => {

              if (usuario.body?.unidadDeNegocioId) {
                this.filter.unidadDeNegocioId=usuario.body?.unidadDeNegocioId;
              }
              this.posicionService
                .query({
                  filter: this.filter != null ? this.filter.toMap() : null,
                  page: pageToLoad - 1,
                  size: this.itemsPerPage,
                  sort: this.sort(),
                })
                .subscribe(
                  (res: HttpResponse<IPosicion[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
                  () => this.onError()
                );
            });
          }
        })
      }
    })


    //
    this.estadoPosicionService.query().subscribe(
      (res: HttpResponse<IEstadoPosicion[]>) => {
        this.estadoPosicionList = res.body || [];
      }),
      () => this.onError();

    this.tipoJornadaService.query().subscribe(
      (res: HttpResponse<ITipoJornada[]>) => {
        this.tipoJornadaList = res.body || [];
      }),
      () => this.onError();


  }

  ngOnInit(): void {
    this.filter = new PosicionFilter();
    this.handleNavigation();
    this.registerChangeInPosicions();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPosicion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPosicions(): void {
    this.eventSubscriber = this.eventManager.subscribe('posicionListModification', () => this.loadPage());
  }

  delete(posicion: IPosicion): void {
    const modalRef = this.modalService.open(PosicionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.posicion = posicion;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IPosicion[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/posicion'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.posicions = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
  // --


  onChangeEvent(event: any): void {
    // eslint-disable-next-line no-console
    console.log(event);


  }

  buscar(): void {
    this.loadPage(1);

    // eslint-disable-next-line no-console
    console.log(this.filter);
  }

  limpiarFiltro(): void {
    this.filter = new PosicionFilter();
    this.loadPage(1);

    // eslint-disable-next-line no-console
    console.log(this.filter);
  }
}
  