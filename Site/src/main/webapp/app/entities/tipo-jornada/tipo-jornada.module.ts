import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { TipoJornadaComponent } from './tipo-jornada.component';
import { TipoJornadaDetailComponent } from './tipo-jornada-detail.component';
import { TipoJornadaUpdateComponent } from './tipo-jornada-update.component';
import { TipoJornadaDeleteDialogComponent } from './tipo-jornada-delete-dialog.component';
import { tipoJornadaRoute } from './tipo-jornada.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(tipoJornadaRoute)],
  declarations: [TipoJornadaComponent, TipoJornadaDetailComponent, TipoJornadaUpdateComponent, TipoJornadaDeleteDialogComponent],
  entryComponents: [TipoJornadaDeleteDialogComponent],
})
export class FrontTipoJornadaModule {}
