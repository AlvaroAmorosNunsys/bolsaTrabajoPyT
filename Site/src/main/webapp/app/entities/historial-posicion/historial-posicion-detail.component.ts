import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistorialPosicion } from 'app/shared/model/historial-posicion.model';

@Component({
  selector: 'jhi-historial-posicion-detail',
  templateUrl: './historial-posicion-detail.component.html',
})
export class HistorialPosicionDetailComponent implements OnInit {
  historialPosicion: IHistorialPosicion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historialPosicion }) => (this.historialPosicion = historialPosicion));
  }

  previousState(): void {
    window.history.back();
  }
}
