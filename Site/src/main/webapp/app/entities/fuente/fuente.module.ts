import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { FuenteComponent } from './fuente.component';
import { FuenteDetailComponent } from './fuente-detail.component';
import { FuenteUpdateComponent } from './fuente-update.component';
import { FuenteDeleteDialogComponent } from './fuente-delete-dialog.component';
import { fuenteRoute } from './fuente.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(fuenteRoute)],
  declarations: [FuenteComponent, FuenteDetailComponent, FuenteUpdateComponent, FuenteDeleteDialogComponent],
  entryComponents: [FuenteDeleteDialogComponent],
})
export class FrontFuenteModule {}
