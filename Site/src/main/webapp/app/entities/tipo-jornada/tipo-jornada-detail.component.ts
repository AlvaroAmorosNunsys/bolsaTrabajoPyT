import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoJornada } from 'app/shared/model/tipo-jornada.model';

@Component({
  selector: 'jhi-tipo-jornada-detail',
  templateUrl: './tipo-jornada-detail.component.html',
})
export class TipoJornadaDetailComponent implements OnInit {
  tipoJornada: ITipoJornada | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoJornada }) => (this.tipoJornada = tipoJornada));
  }

  previousState(): void {
    window.history.back();
  }
}
