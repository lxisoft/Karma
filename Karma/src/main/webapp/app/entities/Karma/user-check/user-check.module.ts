import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  UserCheckComponent,
  UserCheckDetailComponent,
  UserCheckUpdateComponent,
  UserCheckDeletePopupComponent,
  UserCheckDeleteDialogComponent,
  userCheckRoute,
  userCheckPopupRoute
} from './';

const ENTITY_STATES = [...userCheckRoute, ...userCheckPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    UserCheckComponent,
    UserCheckDetailComponent,
    UserCheckUpdateComponent,
    UserCheckDeleteDialogComponent,
    UserCheckDeletePopupComponent
  ],
  entryComponents: [UserCheckComponent, UserCheckUpdateComponent, UserCheckDeleteDialogComponent, UserCheckDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaUserCheckModule {}
