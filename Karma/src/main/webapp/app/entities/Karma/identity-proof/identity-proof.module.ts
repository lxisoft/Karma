import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  IdentityProofComponent,
  IdentityProofDetailComponent,
  IdentityProofUpdateComponent,
  IdentityProofDeletePopupComponent,
  IdentityProofDeleteDialogComponent,
  identityProofRoute,
  identityProofPopupRoute
} from './';

const ENTITY_STATES = [...identityProofRoute, ...identityProofPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    IdentityProofComponent,
    IdentityProofDetailComponent,
    IdentityProofUpdateComponent,
    IdentityProofDeleteDialogComponent,
    IdentityProofDeletePopupComponent
  ],
  entryComponents: [
    IdentityProofComponent,
    IdentityProofUpdateComponent,
    IdentityProofDeleteDialogComponent,
    IdentityProofDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaIdentityProofModule {}
