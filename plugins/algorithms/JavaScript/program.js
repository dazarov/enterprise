
var empty_object = {};

var stooge = {
"first-name": "Jerome",
"last-name": "Howard"
};

var flight = {
	airline: "Oceanic",
	number: 815,
	departure: {
		IATA: "SYD",
		time: "2004-09-22 14:55",
		city: "Sydney"
	},
	arrival: {
		IATA: "LAX",
		time: "2004-09-23 10:42",
		city: "Los Angeles"
	}
};

var middle = stooge["midle-name"] || "(none)";
var status = flight.status || "Unknown";

document.title = "<-JavaScript->"

document.writeln('Hello, world!\n\n');
document.writeln('First Name: '+stooge["first-name"]);
document.writeln('Last Name: '+stooge["last-name"]);

document.writeln('Flight Status: '+status);

stooge['middle-name'] = 'Lester';
stooge.nickname = 'Curly';

flight.equipment = {
	model: 'Boing 777'
};
flight.status = "overdue";

document.writeln('\nFlight Status: '+flight.status);
document.write('\n');
if(typeof Object.create !== 'function'){
	Object.create = function (o){
		var F = function (){};
		F.prototype = o;
		return new F();
	};
};

var another_stooge = Object.create(stooge);

another_stooge['first-name'] = 'Harry';
another_stooge['middle-name'] = 'Moses';
another_stooge.nickname = 'Moe';

stooge.profession = 'actor';

document.writeln('Another Stooge Profession - '+another_stooge.profession);
document.write('\n');

var name;

for(name in another_stooge){
	if(typeof another_stooge[name] !== 'function'){
		document.writeln(name + ': '+another_stooge[name]);
	}
}
document.write('\n');

var i;
var properties = [
	'first-name',
	'middle-name',
	'last-name',
	'profession'
];
for(i = 0; i < properties.length; i+=1){
	document.writeln(properties[i] + ': '+ another_stooge[properties[i]]);
}
document.write('\n');

delete another_stooge.nickname;

document.writeln('Another Stooge Nickname - '+another_stooge.nickname);
document.write('\n');

document.writeln('01------------------------------------------------');

var MYAPP = {};

MYAPP.stooge = {
	"first-name": "Joe",
	"last-name": "Howard"	
};

MYAPP.flight = {
	airline: "Oceanic",
	number: 815,
	departure: {
		IATA: "SYD",
		time: "2004-09-22 14:55",
		city: "Sydney"	
	},
	arrival: {
		IATA: "LAX",
		time: "2004-09-23 10:42",
		city: "Los Angeles"
	}
};

document.writeln('02------------------------------------------------');

var hanoi = function hanoi(disc, src, aux, dst){
	if(disc > 0){
		hanoi(disc-1,src,dst,aux);
		document.writeln('Move disc '+disc+' from '+src+' to '+dst);
		hanoi(disc-1,aux,src,dst);
	}
};

hanoi(3, 'Src', 'Aux', 'Dst');

document.writeln('03------------------------------------------------');

var walk_the_DOM = function walk(node, func){
	func(node);
	node = node.getfirstChild;
	while(node){
		walk(node, func);
		node = node.nextSibling;
	}
};

var getElementByAttribute = function (att, value){
	var results = [];

	walk_the_DOM(document.body, function (node){
		
		var actual = node.nodeType === 1 && node.getAttribute(att);
		if(typeof actual === 'string' &&
		(actual === value || typeof value !== 'string')){
			results.push(node);
		}
	});
	return results;
}

document.writeln('04------------------------------------------------');

var factorial = function factorial(i, a){
	
	a = a || 1;
	if(i < 2){
		return a;
	}
	return factorial(i - 1, a * i);
};

document.writeln(factorial(4));

document.writeln('05------------------------------------------------');

var v0 = 100;

document.writeln('V0 - '+v0);

var v1 = v0 === 100 && 500;

document.writeln('var v1 = v0 === 100 && 500; V1 - '+v1);

var v2 = v0 === 200 && 500;

document.writeln('var v2 = v0 === 200 && 500; V2 - '+v2);

var v3 = v3 && 500;

document.writeln('var v3 =     v3     && 500; V3 - '+v3);

var v4 = v0 && 500;

document.writeln('var v4 =     v0     && 500; V4 - '+v4);

var v5 = v0 === 100 || 500;

document.writeln('var v5 = v0 === 100 || 500; V5 - '+v5);

var v6 = v0 === 200 || 500;

document.writeln('var v6 = v0 === 200 || 500; V6 - '+v6);

var v7 = v7 || 500;

document.writeln('var v7 =     v7     || 500; V7 - '+v7);

var v8 = v0 || 500;

document.writeln('var v8 =     v0     || 500; V8 - '+v8);

document.writeln('06------------------------------------------------');

var fade = function (node){
	var level = 1;
	var step = function(){
		var hex = level.toString(16);
		node.style.backgroundColor = '#FFFF' + hex + hex;
		if(level < 15){
			level += 1;
			setTimeout(step, 100);
		}
	};
	setTimeout(step, 100);
};

fade(document.body);

document.writeln('07------------------------------------------------');

Function.prototype.method = function (name, func){
	this.prototype[name] = func;
	return this;
}

String.method('trim', function (){
	return this.replace(/^\s+$/g, '');
});

document.writeln('"' + " neat ".trim()+'"');

document.writeln('08------------------------------------------------');

String.method('deentityify', function (){
	var entity = {
		quot: '"',
		lt: '<',
		gt: '>'	
	};
	return function(){
		
		return this.replace(/&([^&;]+);/g,
			function (a, b) {
				var r = entity[b];
				return typeof r === 'string' ? r : a;
			})
	};
}());

document.writeln('&lt;&quot;&gt;'.deentityify());

document.writeln('09------------------------------------------------');

var mammal = function (spec){
	var that = {};

	that.get_name = function(){
		return spec.name;
	};
	that.says = function(){
		return spec.saying || '';
	};

	return that;
};

var myMammal = mammal({name: 'Herb'});

document.writeln('Mammal name - '+myMammal.get_name());
document.writeln('Mammal says - '+myMammal.says());
//document.writeln('Mammal purr - '+myMammal.purr(1));

var cat = function(spec){
	spec.saying = spec.saying || 'meow';
	var that = mammal(spec);
	that.purr = function(n)	{
		var i, s = '';
		for(i = 0; i < n; i += 1){
			if(s){
				s += '-';
			}
			s += 'r';
		}
		return s;
	};
	that.get_name = function(){
		return that.says() + ' ' + spec.name + ' ' + that.says();	
	};
	return that;
};

var myCat = cat({name: 'Henrietta'});

document.writeln('My Cat name - '+myCat.get_name());
document.writeln('My Cat says - '+myCat.says());
document.writeln('My Cat purr - '+myCat.purr(5));

Object.method('superior', function (name){
	var that = this,
		method = that[name];
	return function () {
		return method.apply(that, arguments);	
	};
});

var coolcat = function(spec){
	var that = cat(spec),
		super_get_name = that.superior('get_name');
	that.get_name = function(n){
		return 'like '+super_get_name() + ' baby';
	};
	return that;
};

var myCoolCat = coolcat({name: 'Bix'});

document.writeln('Coolcat name - '+myCoolCat.get_name());
document.writeln('Coolcat says - '+myCoolCat.says());
document.writeln('Coolcat purr - '+myCoolCat.purr(6));

document.writeln('10------------------------------------------------');

var eventuality = function (that){
	var registry = {};

	that.fire = function (event) {
		var array, func, handler, i,
		type = typeof event === 'string' ? event : event.type;

		if(regestry.hasOwnProperty(type)){
			array = registry[type];
			for(i == 0; i < array.length; i += 1){
				handler = array[i];

				func = handler.method;
				if(typeof func === 'string'){
					func = this[func];
				}

				func.apply(this, handler.parameters || [event]);
			}	
		}
		return this;
	};

	that.on = function(type, method, parameters){
		var handler = {
			method: method,
			parameters: parameters	
		};
		if(registry.hasOwnProperty(type)){
			registry[type].push(handler);
		}else{
			registry[type] = [handler];
		}
		return this;
	};
	return that;
};


//eventuality(that);

document.writeln('11------------------------------------------------');

// Arrays

document.writeln('12------------------------------------------------');

// Regular Expressions

var parse_url = /^(?:([A-Za-z]+):)?(\/{0,3})([0-9.\-A-Za-z]+)(?::(\d+))?(?:\/([^?#]*))?(?:\?([^#]*))?(?:#(.*))?$/;

var url = "http://www.ora.com:80/goodparts?q#fragment";

var result = parse_url.exec(url);

var names = ['url', 'scheme', 'slash', 'host', 'port', 'path', 'query', 'hash'];

var blanks = '       ';

var i;

for(i = 0; i < names.length; i += 1){
	document.writeln(names[i]+':'+blanks.substring(names[i].length), result[i]);
}

document.writeln('13------------------------------------------------');

var parse_number = /^-?\d+(?:\.\d*)?(?:e[+\-]?\d+)?$/i;

var test = function(num){
	document.writeln(parse_number.test(num));
}

test('1');
test('number');
test('98.6');
test('132.21.86.100');
test('123.45E-67');
test('123.45D-67');

document.writeln('14------------------------------------------------');

var decimalFraction = 0.1 + 0.2;

document.writeln("decimal fraction - "+decimalFraction);

console.log('15------------------------------------------------');

var myData = {
	name: 'Adam',
	weather: 'sunny'
};

var hasName = 'name' in myData;
var hasDate = 'date' in myData;

console.log('HasName: '+hasName);
console.log('HasDate: '+hasDate);

console.log('16------------------------------------------------');


// Encapsulation:::


var person = (function () {
	// private members
  var fullName = "Jason Shapiro";
  var reg = new RegExp(/\d+/);
 
  return {
	  // public members
    setFullName : function (newValue) {
      if( reg.test(newValue) ) {
        alert("Invalid Name");
      }
      else {
        fullName = newValue;
      }
    },
    getFullName : function () {
     return fullName; 
    }
  }; // end of the return
}());