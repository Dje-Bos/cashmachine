import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HfRoutingModule} from './hf-routing.module';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {HeaderProfileComponent} from './header-profile/header-profile.component';
import {MatButtonModule, MatMenuModule} from '@angular/material';

@NgModule({
  declarations: [
    FooterComponent,
    HeaderComponent,
    HeaderProfileComponent
  ],
  exports: [
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    HfRoutingModule,
    MatMenuModule,
    MatButtonModule
  ]
})
export class HfModule { }
