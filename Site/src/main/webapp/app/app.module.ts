import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { FrontSharedModule } from 'app/shared/shared.module';
import { FrontCoreModule } from 'app/core/core.module';
import { FrontAppRoutingModule } from './app-routing.module';
import { FrontHomeModule } from './home/home.module';
import { FrontEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    FrontSharedModule,
    FrontCoreModule,
    FrontHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    FrontEntityModule,
    FrontAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class FrontAppModule {}
