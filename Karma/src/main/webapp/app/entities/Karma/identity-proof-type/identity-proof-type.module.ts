import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  IdentityProofTypeComponent,
  IdentityProofTypeDetailComponent,
  IdentityProofTypeUpdateComponent,
  IdentityProofTypeDeletePopupComponent,
  IdentityProofTypeDeleteDialogComponent,
  identityProofTypeRoute,
  identityProofTypePopupRoute
} from './';

const ENTITY_STATES = [...identityProofTypeRoute, ...identityProofTypePopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    IdentityProofTypeComponent,
    IdentityProofTypeDetailComponent,
    IdentityProofTypeUpdateComponent,
    IdentityProofTypeDeleteDialogComponent,
    IdentityProofTypeDeletePopupComponent
  ],
  entryComponents: [
    IdentityProofTypeComponent,
    IdentityProofTypeUpdateComponent,
    IdentityProofTypeDeleteDialogComponent,
    IdentityProofTypeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaIdentityProofTypeModule {}
