import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { CandidaturaComponent } from './candidatura.component';
import { CandidaturaDetailComponent } from './candidatura-detail.component';
import { CandidaturaUpdateComponent } from './candidatura-update.component';
import { CandidaturaDeleteDialogComponent } from './candidatura-delete-dialog.component';
import { candidaturaRoute } from './candidatura.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(candidaturaRoute)],
  declarations: [CandidaturaComponent, CandidaturaDetailComponent, CandidaturaUpdateComponent, CandidaturaDeleteDialogComponent],
  entryComponents: [CandidaturaDeleteDialogComponent],
})
export class FrontCandidaturaModule {}
