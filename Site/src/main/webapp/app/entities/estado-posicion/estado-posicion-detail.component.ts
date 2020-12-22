import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadoPosicion } from 'app/shared/model/estado-posicion.model';

@Component({
  selector: 'jhi-estado-posicion-detail',
  templateUrl: './estado-posicion-detail.component.html',
})
export class EstadoPosicionDetailComponent implements OnInit {
  estadoPosicion: IEstadoPosicion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadoPosicion }) => (this.estadoPosicion = estadoPosicion));
  }

  previousState(): void {
    window.history.back();
  }
}
