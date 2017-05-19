"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var RoleGuard = (function () {
    function RoleGuard(router, activatedroute) {
        this.router = router;
        this.activatedroute = activatedroute;
    }
    RoleGuard.prototype.canActivate = function (route) {
        var userId;
        this.sub = this.activatedroute.params.subscribe(function (params) {
            userId = params['userId'];
        });
        var flag = this.check(userId);
        if (!flag) {
            this.router.navigate(['/overview']);
        }
        return flag;
    };
    RoleGuard.prototype.check = function (userId) {
        for (var i = 0; i < (JSON.parse(sessionStorage.getItem('Users')).UsersA.length); i++) {
            if ((JSON.parse(sessionStorage.getItem('Users')).UsersA[i].id) == userId) {
                return true;
            }
        }
        return false;
    };
    return RoleGuard;
}());
RoleGuard = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [router_1.Router, router_1.ActivatedRoute])
], RoleGuard);
exports.RoleGuard = RoleGuard;
//# sourceMappingURL=role-guard.service.js.map