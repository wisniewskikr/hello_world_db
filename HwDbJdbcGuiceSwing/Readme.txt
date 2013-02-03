GUICE


Guice is a dependency injection container created by Google. 
In other words it is the library provides dependency injection.
You can use this DI everywhere:
- in Web Applications;
- in Clients;
- in Tests.



I. GUICE WITHOUT ANNOTATIONS

To use Guice without annotations you have to do following steps:
1. Create interface
For instance: IMathService.

2. Create implementation
For instance MathService.

3. Create Module
This Module class has to implement Guice Module interface. We have to
bind/connect here an interface with the implementation.

	public class MathServiceModule implements Module {
		public void configure(Binder binder) {
	        binder.bind(IMathService.class).to(MathService.class);
	    }
	}

4. Run application in container
We run DI container by using Guice.createInjector().

	Injector injector = Guice.createInjector(new MathServiceModule());
    Main main = injector.getInstance(Main.class);



 II. GUICE WITH ANNOTATIONS

To use Guice with annotations you have to do following steps:
1. Create interface
You have to use here annotation @ImplementedBy which indicates on implementation.

	@ImplementedBy(MathService.class)
	public interface IMathService {}

2. Create implementation
For instance MathService.

3. Run application in container
We run DI container by using Guice.createInjector(). 

	Injector injector = Guice.createInjector();
    Main main = injector.getInstance(Main.class);
 
 
 
 III. GUICE AND TESTS
 
 To use Guice in tests you have to do following steps:
 1. Create Provider
 This Provider has to implement Guice Provider interface. It has to return
 mock of interface.
 
	 public class MathServiceProvider implements Provider<IMathService>{
		@Override
		public IMathService get() {
			IMathService mock = Mockito.mock(IMathService.class);
			Mockito.when(mock.add(Mockito.anyInt(), Mockito.anyInt())).thenReturn(5);
			return mock;
		}
	}

2. Create Module
This Module class has to implement Guice Module interface. We have to
bind/connect here an interface with the Provider and mock.

	public class MainTestModule implements Module {
		public void configure(Binder binder) {
	        binder.bind(IMathService.class).toProvider(MathServiceProvider.class);
	    }
	}
	
3. Run tests in container
We have to run tests with mock interface so with provider instead of implementation.
	
	public class MainTest {
		private Main main;
		@Before
		public void setUp(){
			Injector injector = Guice.createInjector(new MainTestModule());
	        main = injector.getInstance(Main.class);
		}
		...
	}	