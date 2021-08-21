import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { HeroComponent } from './hero/hero.component';
import { LogInComponent } from './log-in/log-in.component';

import { PaymentComponent } from './payment/payment.component';
import { TransactionListComponent } from './transaction-list/transaction-list.component';

const routes: Routes = [
  {
    path:'',
    component:HeroComponent
  },
  {
    path:'login',
    component:LogInComponent
  },
  {
    path:'transactions',
    component: TransactionListComponent
  },
  {
    path:'createaccount',
    component: CreateAccountComponent
  },
  {
    path:'sendmoney',
    component: PaymentComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
}