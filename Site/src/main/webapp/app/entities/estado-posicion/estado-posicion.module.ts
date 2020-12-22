import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { EstadoPosicionComponent } from './estado-posicion.component';
import { EstadoPosicionDetailComponent } from './estado-posicion-detail.component';
import { EstadoPosicionUpdateComponent } from './estado-posicion-update.component';
import { EstadoPosicionDeleteDialogComponent } from './estado-posicion-delete-dialog.component';
import { estadoPosicionRoute } from './estado-posicion.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(estadoPosicionRoute)],
  declarations: [
    EstadoPosicionComponent,
    EstadoPosicionDetailComponent,
    EstadoPosicionUpdateComponent,
    EstadoPosicionDeleteDialogComponent,
  ],
  entryComponents: [EstadoPosicionDeleteDialogComponent],
})
export class FrontEstadoPosicionModule {}
