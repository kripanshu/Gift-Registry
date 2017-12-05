# Gift-Registry
Gift registry application - A responsive Web application for gift registry with properly working database and smart front end.




Front-end: Angular 4.0.0, bootstrap:4.0.0 beta, font-awsome, popper.js, HTML5,CSS3
Micro Services: Spring and Hibernate with Ehchache
Web Services: Spring and Hibernate with TSL/Https secure certification.
DataBase: MySql





Features:
Admin : Log into account, access the inventory, add/delete items from the inventory.
User : Register, Login
       Create your own registry, make it public(visible to all other user) or private(contained to yourself).
       Share your registry with other people.
       add/delete items from your registry.
       assign items to the other people from their registry(registries).
Extra features: User profile updatation, forget password, single-sign-on.
Security: SSL certificate with https secured connection, basic OAuth.
Compression: EhCache



To run: 
1. Clone/Download folder and unzip it.
2. Further unzip the innermost folders
3. Import both projects folder in Archive folder into Eclipse and gift_registry_application
into any web development IDE
4. Build the libraries into the eclipse folder and update Maven, also assign servers to both
microservice and wpl project(web service)
5. Create schema in MySQL names “wplproject” and import all the sql files.
6. Start front end app into IDE and right “ng serve” in terminal(cd into directory)
7. Explore our application

Angular 4.0
# GiftRegistary

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.5.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).



