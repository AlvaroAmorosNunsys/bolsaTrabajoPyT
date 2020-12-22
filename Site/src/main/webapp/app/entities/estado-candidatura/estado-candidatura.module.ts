import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { EstadoCandidaturaComponent } from './estado-candidatura.component';
import { EstadoCandidaturaDetailComponent } from './estado-candidatura-detail.component';
import { EstadoCandidaturaUpdateComponent } from './estado-candidatura-update.component';
import { EstadoCandidaturaDeleteDialogComponent } from './estado-candidatura-delete-dialog.component';
import { estadoCandidaturaRoute } from './estado-candidatura.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(estadoCandidaturaRoute)],
  declarations: [
    EstadoCandidaturaComponent,
    EstadoCandidaturaDetailComponent,
    EstadoCandidaturaUpdateComponent,
    EstadoCandidaturaDeleteDialogComponent,
  ],
  entryComponents: [EstadoCandidaturaDeleteDialogComponent],
})
export class FrontEstadoCandidaturaModule {}
