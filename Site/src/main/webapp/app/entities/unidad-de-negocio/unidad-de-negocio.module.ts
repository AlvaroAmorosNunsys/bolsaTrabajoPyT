import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { UnidadDeNegocioComponent } from './unidad-de-negocio.component';
import { UnidadDeNegocioDetailComponent } from './unidad-de-negocio-detail.component';
import { UnidadDeNegocioUpdateComponent } from './unidad-de-negocio-update.component';
import { UnidadDeNegocioDeleteDialogComponent } from './unidad-de-negocio-delete-dialog.component';
import { unidadDeNegocioRoute } from './unidad-de-negocio.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(unidadDeNegocioRoute)],
  declarations: [
    UnidadDeNegocioComponent,
    UnidadDeNegocioDetailComponent,
    UnidadDeNegocioUpdateComponent,
    UnidadDeNegocioDeleteDialogComponent,
  ],
  entryComponents: [UnidadDeNegocioDeleteDialogComponent],
})
export class FrontUnidadDeNegocioModule {}
