import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class A{
	 float   f = 1;
	 int n4 = '1';
	 double n3 = 1.0;
	 short n1 = 1;
	 
	 public static void main(String... args){
		 List<Country> countries = new ArrayList<>();
		 countries.add(new Country("Russia"));
		 countries.add(new Country("Belarus"));
		 countries.add(new Country("Poland"));
		 countries.add(new Country("US"));
		 
		 List<String> strings = countries
				 .stream()
				 .filter(c -> c.getName().length() > 0)
				 .map(c -> ""+c.getName().charAt(0))
				 .distinct()
				 .sorted()
				 .collect(Collectors.toList());
		 
		 strings.forEach(System.out::println);
	 }
	 
	 public static class Country{
		 private String name;
		 
		 public Country(String name){
			 this.name = name;
		 }
		 
		 public String getName(){
			 return name;
		 }
	 }
}
/*
//div vs span?
//strong vs bold ?
var a={
var b = 12;
function f(a){
}
}

//Develop Agnualr directive <my-countries/> that gets counties from https://mycountries.com and displays them as a drop-down box ordered alphatbetocally with the first country displayed by default. 

//---------------------------------

//Develop REST service (using any technology) https://mycountries.com/api/countries/list

@RestController
@RequestMapping(value="mycountries.com/api")
class CoutriesListController{
@Autowired
private CountryRepository countryRepo;

@RequestMapping(method= RequestMethod.GET, value = "countries/list", prodeuces = "application/json")
public ResponseEntity<List<String>> getCountries(
 List<Country> countries = countryRepo.findAll();
 List<String> stringList = countries.stream().convert(c -> c.getName()).collect(Collections.toList);
 return new ResponseEntity<List<String>>(stringList, HttpStatus.OK);
}

@Entity
class Country{
Long id;
String name;
}

public interface CountryRepository extends CrudRepository<Country, Long>{
}

//SOAP
//TDD
//Unit Testing

@Configuration
public class TestRepositoryConfiguration{

@Bean
public CountryRepository countryRepository(){
return Mockito.mock(CountryRepository.class);
}

@ContextConfiguration(classes ="TestRepositoryConfiguration.class")
public class CountryListControllerTest{
@Autowired
private CountryRepository;

@Test
public void testRest(){
}

//------------------------
//List <- ArrayList, LinkedList
O(n)

*/

