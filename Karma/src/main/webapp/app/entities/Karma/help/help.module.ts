import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  HelpComponent,
  HelpDetailComponent,
  HelpUpdateComponent,
  HelpDeletePopupComponent,
  HelpDeleteDialogComponent,
  helpRoute,
  helpPopupRoute
} from './';

const ENTITY_STATES = [...helpRoute, ...helpPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [HelpComponent, HelpDetailComponent, HelpUpdateComponent, HelpDeleteDialogComponent, HelpDeletePopupComponent],
  entryComponents: [HelpComponent, HelpUpdateComponent, HelpDeleteDialogComponent, HelpDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaHelpModule {}
