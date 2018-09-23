# presentation_store
1. Dodaj dependency do spring-context
2. Utwórz plik applicationContext.xml w src/main/resources
3. Dodaj do applicationContext.xml następujące beany:<br>
  - Wszystkie nieabstrakcyjne klasy z pakietu pl.sdacademy.store.repository<br>
  - Wszystkie klasy z pakietu pl.sdacademy.store.service, oraz wstrzyknij do nich potrzebne beany<br>
  - Wszystkie klasy z pakietu pl.sdacademy.store.controller, oraz wstrzyknij do nich potrzebne beany<br>
  - Klasę pl.sdacademy.store.Application oraz wstrzyknij do niej wymagane klasy controller<br>
4. Z klasy Application usuń tworzenie instancji zależnych klas oraz ich ustawianie w klasach
5. W klasie Main pobierz bean Application, uruchom projekt i sprawdź, czy wykonuje się poprawnie

Dodatkowe:

6. W klasach *Repository dodaj metody initialize() oraz cleanUp(), w których wypiszesz na ekran informacje o „Initializing Repository” oraz „Cleaning up repository”. 
7. W applicationContext.xml dodaj dla beanów *Resource atrybuty init-method oraz destroy-method tak, aby wywoływały odpowiednie metody
8. Aby umożliwić wykonywanie metod z destroy-method, w klasie Main zmień typ obiektu applicationContext na AbstractApplicationContext oraz wywołaj na nim metodę close().
```
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
Application application = applicationContext.getBean("application", Application.class);    
application.start();
```

### cześć 2 - autowire
Następnie: Stwórz taką konfigurację, aby:

9. Beany będące Serwisami używały autowire za pomocą konstruktora. Pamiętaj o usunięciu setterów w klasie beana oraz interface!
10. Beany będące Controllerami powinny używać autowire byType
11. Bean Application powinien używać autowire byName

### cześć 3 - value w konstruktorze
11. Skonfiguruj repositoryLocation dla beana hardDriveCustomerRepository tak, aby było ustawiane za pomocą konstruktora
12. Skonfiguruj repositoryLocation dla beana hardDrivePurchaseRepository tak, aby było ustawiane za pomocą konstruktora
13. Skonfiguruj repositoryLocation dla beana hardDriveVehicleRepository tak, aby było ustawiane za pomocą konstruktora

### cześć 4 - wartości w pliku konfiguracyjnym
14. W katalogu src/main/resources utwórz plik application.properties
15. Do pliku application.properties dodaj następujące klucze oraz odpowiednie dla nich wartości:

```
repository.customer.hardDriveLocation
repository.purchase.hardDriveLocation
repository.vehicle.hardDriveLocation
```
16. W pliku applicationContext.xml zdefiniuj bean dla klasy: org.springframework.beans.factory.config.PropertyPlaceholderConfigurerPamiętaj, o ustawieniu w nim pola location i wskazaniu na plik application.properties
17. Zmień konfigurację beanów repozytoriów tak, aby używały wartości zapisanych w pliku konfiguracyjnym
18. Uruchom i zweryfikuj, czy aplikacja działa

### część 5 - konfiguracja przez adnotacje
19. Zmień konfigurację applicationContext.xml tak, aby definiowała użycie adnotacji
20. Usuń stare konfiguracje beanów.
21. W głównym pakiecie aplikacji stwórz klasę ApplicationConfiguration
22. Dodaj do niej adnotację @Configuration
23. Zdefiniuj wszystkie beany oraz zależności między nimi w tej klasie
24. Pamiętaj o adnotacji @Bean
25. Pamiętaj o ustawieniu wartości repositoryLocation
26. Uruchom aplikację i zweryfikuj, czy działa.

### część 6
27. Zmień definicję beana Application tak, aby korzystała z autowire BY_TYPE
28. Zmień definicję beanów CarDataController oraz SellingController tak, aby korzystała z autowire BY_NAME
29. Ciało metod definiujących beany powinny mieć po 1 linijce
30. Uruchom i zweryfikuj działanie aplikacji

### część 7
31. Dodaj adnotację @PropertySource do ApplicationConfiguration
- Adnotacja powinna znajdować się nad klasą
- Powinna wskazywać na plik znajdujący się w classpath (np.classpath:nazwaPliku.properties)
32. Dodaj definicję beana PropertySourcesPlaceholderConfigurer
- Metoda definiująca go musi być statyczna
- Wystarczy zwrócić nowy obiekt tej klasy
33. W klasie konfiguracji dodaj trzy pola typu String, które będą przechowywać lokalizację plików z danymi repozytorium
- Każde z tych pól adnotuj za pomocą @Value, za pomocą którego wstrzykniesz odpowiednią wartość
- Pamiętaj o ${}
34. Uruchom aplikację i sprawdź, czy działa

### część 8
35. Wszystkie klasy Repozytoriów oznacz adnotacją @Repository
36. Wszystkie klasy Serwisów oznacz adnotacją @Service
37. Wszystkie klasy Controllerów oznacz adnotacją @Controller
38. Klasę Application oznacz adnotacją @Component
39. Z klasy konfiguracji usuń pola oznaczone adnotacją @Value oraz wszystkie metody definiujące beany oprócz PropertySourcesPlaceholderConfigurer
40. Użyj adnotacji @Autowire oraz @Value w Beanach tak, aby aplikacja nadal działała

### część 9
41. Przerób wszystkie klasy beanów tak, aby używały konstruktora do wstrzykiwania
42. Zwróć uwagę, aby wstrzykiwać interface, a nie implementację (czyli np. wstrzykuj VehicleRepository a nie HardDriveVehicleRepository). Zwracaj także uwagę na nazwę parametru (np. claimRepository a nie hardDrivePurchaseRepository)
43. Usuń wszystkie użycia @Autowire
44. Pamiętaj, że @Value może być użyte także w konstruktorze
45. Uruchom aplikację i sprawdź czy działa (ważne aby sprawdzić działanie przed następnym punktem)
46. Dodaj nową implementację CustomerRepository:
47. Nazwij ją FakeCustomerRepository i oznacz odpowiednią adnotacją
48. Metoda byId(Long) niech zwraca nulla
49. Metoda add(Customer) niech zwraca wartość otrzymaną w parametrze

### część 10
50. Dodaj javax.inject do projektu
51. Stwórz własne Qualifiery:
- HardDriveStorage
- FakeStorage
54. Użyj Qualifierów do oznaczenia klas repozytoriów
55. Użyj włanego qualifiera do wskazania odpowiedniej implementacji w DefaultSellingService
56. Uruchom i sprawdź działanie aplikacji
