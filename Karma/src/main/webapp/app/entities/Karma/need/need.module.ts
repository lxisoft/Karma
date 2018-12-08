import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  NeedComponent,
  NeedDetailComponent,
  NeedUpdateComponent,
  NeedDeletePopupComponent,
  NeedDeleteDialogComponent,
  needRoute,
  needPopupRoute
} from './';

const ENTITY_STATES = [...needRoute, ...needPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [NeedComponent, NeedDetailComponent, NeedUpdateComponent, NeedDeleteDialogComponent, NeedDeletePopupComponent],
  entryComponents: [NeedComponent, NeedUpdateComponent, NeedDeleteDialogComponent, NeedDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaNeedModule {}
