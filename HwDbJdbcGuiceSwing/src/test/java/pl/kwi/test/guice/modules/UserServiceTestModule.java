package pl.kwi.test.guice.modules;

import pl.kwi.services.UserService;
import pl.kwi.test.guice.providers.UserServiceTestProvider;
import com.google.inject.Binder;
import com.google.inject.Module;

public class UserServiceTestModule implements Module {

	public void configure(Binder binder) {
        binder.bind(UserService.class).toProvider(UserServiceTestProvider.class);
    }

}
