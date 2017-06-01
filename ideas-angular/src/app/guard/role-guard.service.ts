import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class RoleGuard implements CanActivate {

    constructor(private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot) {
		// Protects routes depending on the authentified role

		// Protects user list access from basic user
        let type: string = route.params['type'];
        if(type === 'user') {
            if ((sessionStorage.getItem('role') === 'Manager') || (sessionStorage.getItem('role') === 'SysAdmin')) {
                return true;
            }
            this.router.navigate(['/overview']);
            return false;
        }
        return true;
    }
}
