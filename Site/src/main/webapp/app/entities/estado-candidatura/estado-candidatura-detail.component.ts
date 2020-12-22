import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadoCandidatura } from 'app/shared/model/estado-candidatura.model';

@Component({
  selector: 'jhi-estado-candidatura-detail',
  templateUrl: './estado-candidatura-detail.component.html',
})
export class EstadoCandidaturaDetailComponent implements OnInit {
  estadoCandidatura: IEstadoCandidatura | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadoCandidatura }) => (this.estadoCandidatura = estadoCandidatura));
  }

  previousState(): void {
    window.history.back();
  }
}
