import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICandidatura } from 'app/shared/model/candidatura.model';

@Component({
  selector: 'jhi-candidatura-detail',
  templateUrl: './candidatura-detail.component.html',
})
export class CandidaturaDetailComponent implements OnInit {
  candidatura: ICandidatura | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ candidatura }) => (this.candidatura = candidatura));
  }

  previousState(): void {
    window.history.back();
  }
}
