import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPosicion } from 'app/shared/model/posicion.model';

@Component({
  selector: 'jhi-posicion-detail',
  templateUrl: './posicion-detail.component.html',
})
export class PosicionDetailComponent implements OnInit {
  posicion: IPosicion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ posicion }) => (this.posicion = posicion));
  }

  previousState(): void {
    window.history.back();
  }
}
