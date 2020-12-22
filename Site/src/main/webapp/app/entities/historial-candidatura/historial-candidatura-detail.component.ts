import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

@Component({
  selector: 'jhi-historial-candidatura-detail',
  templateUrl: './historial-candidatura-detail.component.html',
})
export class HistorialCandidaturaDetailComponent implements OnInit {
  historialCandidatura: IHistorialCandidatura | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historialCandidatura }) => (this.historialCandidatura = historialCandidatura));
  }

  previousState(): void {
    window.history.back();
  }
}
