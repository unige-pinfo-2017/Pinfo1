import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot) {
        if (sessionStorage.getItem('id')) {
            return true;
        }

        this.router.navigate(['/login']);
        return false;
    }
}
