import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { HistorialCandidaturaComponent } from './historial-candidatura.component';
import { HistorialCandidaturaDetailComponent } from './historial-candidatura-detail.component';
import { HistorialCandidaturaUpdateComponent } from './historial-candidatura-update.component';
import { HistorialCandidaturaDeleteDialogComponent } from './historial-candidatura-delete-dialog.component';
import { historialCandidaturaRoute } from './historial-candidatura.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(historialCandidaturaRoute)],
  declarations: [
    HistorialCandidaturaComponent,
    HistorialCandidaturaDetailComponent,
    HistorialCandidaturaUpdateComponent,
    HistorialCandidaturaDeleteDialogComponent,
  ],
  entryComponents: [HistorialCandidaturaDeleteDialogComponent],
})
export class FrontHistorialCandidaturaModule {}
