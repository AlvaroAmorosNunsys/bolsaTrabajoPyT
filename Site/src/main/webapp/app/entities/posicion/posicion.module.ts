import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { PosicionComponent } from './posicion.component';
import { PosicionDetailComponent } from './posicion-detail.component';
import { PosicionUpdateComponent } from './posicion-update.component';
import { PosicionDeleteDialogComponent } from './posicion-delete-dialog.component';
import { posicionRoute } from './posicion.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(posicionRoute)],
  declarations: [PosicionComponent, PosicionDetailComponent, PosicionUpdateComponent, PosicionDeleteDialogComponent],
  entryComponents: [PosicionDeleteDialogComponent],
})
export class FrontPosicionModule {}
