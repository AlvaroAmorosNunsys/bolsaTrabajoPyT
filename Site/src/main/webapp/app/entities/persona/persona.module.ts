import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontSharedModule } from 'app/shared/shared.module';
import { PersonaComponent } from './persona.component';
import { PersonaDetailComponent } from './persona-detail.component';
import { PersonaUpdateComponent } from './persona-update.component';
import { PersonaDeleteDialogComponent } from './persona-delete-dialog.component';
import { personaRoute } from './persona.route';

@NgModule({
  imports: [FrontSharedModule, RouterModule.forChild(personaRoute)],
  declarations: [PersonaComponent, PersonaDetailComponent, PersonaUpdateComponent, PersonaDeleteDialogComponent],
  entryComponents: [PersonaDeleteDialogComponent],
})
export class FrontPersonaModule {}
