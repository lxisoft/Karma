import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { KarmaWebClientSharedLibsModule, KarmaWebClientSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [KarmaWebClientSharedLibsModule, KarmaWebClientSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [KarmaWebClientSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaWebClientSharedModule {}
