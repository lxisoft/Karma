import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  VerificationTeamComponent,
  VerificationTeamDetailComponent,
  VerificationTeamUpdateComponent,
  VerificationTeamDeletePopupComponent,
  VerificationTeamDeleteDialogComponent,
  verificationTeamRoute,
  verificationTeamPopupRoute
} from './';

const ENTITY_STATES = [...verificationTeamRoute, ...verificationTeamPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VerificationTeamComponent,
    VerificationTeamDetailComponent,
    VerificationTeamUpdateComponent,
    VerificationTeamDeleteDialogComponent,
    VerificationTeamDeletePopupComponent
  ],
  entryComponents: [
    VerificationTeamComponent,
    VerificationTeamUpdateComponent,
    VerificationTeamDeleteDialogComponent,
    VerificationTeamDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaVerificationTeamModule {}
