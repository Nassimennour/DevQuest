import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './shared/components/login-page/login-page.component';
import { RegisterComponent } from './shared/components/register/register.component';
import { AdminDashboardComponent } from './features/admin/components/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './features/user/components/user-dashboard/user-dashboard.component';
import { LandingComponent } from './shared/components/landing/landing.component';
import { ContactComponent } from './shared/components/contact/contact.component';
import { AboutComponent } from './shared/components/about/about.component';
import { TermsComponent } from './shared/components/terms/terms.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'landing',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginPageComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'landing',
    component: LandingComponent,
  },
  {
    path: 'contact',
    component: ContactComponent,
  },
  {
    path: 'about',
    component: AboutComponent,
  },
  {
    path: 'terms',
    component: TermsComponent,
  },
  {
    path: 'admin',
    loadChildren: () => {
      return import('./features/admin/admin.module').then((m) => m.AdminModule);
    },
  },
  {
    path: 'user',
    loadChildren: () => {
      return import('./features/user/user.module').then((m) => m.UserModule);
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
