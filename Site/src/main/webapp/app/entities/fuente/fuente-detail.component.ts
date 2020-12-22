import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFuente } from 'app/shared/model/fuente.model';

@Component({
  selector: 'jhi-fuente-detail',
  templateUrl: './fuente-detail.component.html',
})
export class FuenteDetailComponent implements OnInit {
  fuente: IFuente | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fuente }) => (this.fuente = fuente));
  }

  previousState(): void {
    window.history.back();
  }
}
