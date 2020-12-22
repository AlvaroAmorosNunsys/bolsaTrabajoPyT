import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { HistorialPosicionComponent } from './historial-posicion.component';
import { HistorialPosicionDetailComponent } from './historial-posicion-detail.component';
import { HistorialPosicionUpdateComponent } from './historial-posicion-update.component';
import { HistorialPosicionDeleteDialogComponent } from './historial-posicion-delete-dialog.component';
import { historialPosicionRoute } from './historial-posicion.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(historialPosicionRoute)],
  declarations: [
    HistorialPosicionComponent,
    HistorialPosicionDetailComponent,
    HistorialPosicionUpdateComponent,
    HistorialPosicionDeleteDialogComponent,
  ],
  entryComponents: [HistorialPosicionDeleteDialogComponent],
})
export class FrontHistorialPosicionModule {}
