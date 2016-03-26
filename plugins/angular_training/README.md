v2
==

## How to install?
1. download and install https://nodejs.org/
2. in command line tool type `npm install`

## How to run?
in command line tool type `npm start`

## When you want to compile code without restart server
in command line tool type `grunt` for dev environment

## How to add bower javascript libs to the application?
1. install bower component using command line
2. in Grungfile.js find section `// bower js libs starts here`
3. add string to array in this section

`  bowerJsComponents = [
    'angular/angular.js', <path to lib>(string), ...];`

## How to add javascript files to the application?
1. create javascript files in `source/js/` folder
2. in Grungfile.js find section `// application js files start here`
3. add string to array in this section:

`  appJS = [
    'app.js',
    <path to file>(string), ... ];`

## How to add module .less file and how to compile it into application.css ?
1. create .less file in `source\modules\<module-name>\<module-name>.less`
2. in Grungfile.js find section `// less files for all application.css`
3. add string to array in this section:

` lessFls[pkg.directories.build + "/css/application.css"] = [
    pkg.directories.build + "/less/application.less", <path to file>(string), ...];`