// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
// To load other configurations during runtime you can use the following
// command (with -c option)
// 'ng serve -c test' .

export const environment = {
  production: false,
  test: false,
  /************************************
   * environment specific properties  *
   ***********************************/
  name: 'dev',
  authBaseUrl: 'http://localhost:8080/cmed/auth',
  apiBaseUrl: 'http://localhost:8080/cmed/api'
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
