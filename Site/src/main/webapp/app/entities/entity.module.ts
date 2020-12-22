import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'posicion',
        loadChildren: () => import('./posicion/posicion.module').then(m => m.FrontPosicionModule),
      },
      {
        path: 'unidad-de-negocio',
        loadChildren: () => import('./unidad-de-negocio/unidad-de-negocio.module').then(m => m.FrontUnidadDeNegocioModule),
      },
      {
        path: 'candidatura',
        loadChildren: () => import('./candidatura/candidatura.module').then(m => m.FrontCandidaturaModule),
      },
      {
        path: 'persona',
        loadChildren: () => import('./persona/persona.module').then(m => m.FrontPersonaModule),
      },
      {
        path: 'estado-posicion',
        loadChildren: () => import('./estado-posicion/estado-posicion.module').then(m => m.FrontEstadoPosicionModule),
      },
      {
        path: 'estado-candidatura',
        loadChildren: () => import('./estado-candidatura/estado-candidatura.module').then(m => m.FrontEstadoCandidaturaModule),
      },
      {
        path: 'fuente',
        loadChildren: () => import('./fuente/fuente.module').then(m => m.FrontFuenteModule),
      },
      {
        path: 'tipo-jornada',
        loadChildren: () => import('./tipo-jornada/tipo-jornada.module').then(m => m.FrontTipoJornadaModule),
      },
      {
        path: 'historial-posicion',
        loadChildren: () => import('./historial-posicion/historial-posicion.module').then(m => m.FrontHistorialPosicionModule),
      },
      {
        path: 'historial-candidatura',
        loadChildren: () => import('./historial-candidatura/historial-candidatura.module').then(m => m.FrontHistorialCandidaturaModule),
      },
      {
        path: 'usuario',
        loadChildren: () => import('./usuario/usuario.module').then(m => m.FrontUsuarioModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FrontEntityModule {}
