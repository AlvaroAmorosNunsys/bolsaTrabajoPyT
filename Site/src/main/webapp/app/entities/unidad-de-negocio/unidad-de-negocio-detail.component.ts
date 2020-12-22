import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';

@Component({
  selector: 'jhi-unidad-de-negocio-detail',
  templateUrl: './unidad-de-negocio-detail.component.html',
})
export class UnidadDeNegocioDetailComponent implements OnInit {
  unidadDeNegocio: IUnidadDeNegocio | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ unidadDeNegocio }) => (this.unidadDeNegocio = unidadDeNegocio));
  }

  previousState(): void {
    window.history.back();
  }
}
