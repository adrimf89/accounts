package com.crud.sample.restful.service;

import com.crud.sample.restful.data.AccountFilterParam;
import com.crud.sample.restful.model.Account;
import com.crud.sample.restful.repository.AccountRepository;
import com.crud.sample.restful.vo.AccountVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void createAccount(){
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setName("test");
        createdAccount.setAge(20);
        createdAccount.setEmail("test@test.com");

        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(createdAccount);

        AccountVO accountVO = new AccountVO(0L, "test", "test@test.com", 20, null, null, null);
        AccountVO result = accountService.create(accountVO);

        assertThat(result, hasProperty("id", is(1L)));
        assertThat(result, hasProperty("name", is("test")));
        assertThat(result, hasProperty("age", is(20)));
        assertThat(result, hasProperty("email", is("test@test.com")));

    }

    @Test
    public void updateAccount(){
        Account createdAccount = new Account();
        createdAccount.setId(2L);
        createdAccount.setName("updated account");
        createdAccount.setAge(20);
        createdAccount.setEmail("test@test.com");

        Mockito.when(accountRepository.existsById(2L)).thenReturn(true);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(createdAccount);

        AccountVO accountVO = new AccountVO(2L, "updated account", "test@test.com", 20, null, null, null);
        AccountVO result = accountService.update(accountVO);

        assertThat(result, hasProperty("id", is(2L)));
        assertThat(result, hasProperty("name", is("updated account")));
        assertThat(result, hasProperty("age", is(20)));
        assertThat(result, hasProperty("email", is("test@test.com")));

    }

    @Test
    public void updateNotExistAccount(){
        Account createdAccount = new Account();
        createdAccount.setId(2L);
        createdAccount.setName("updated account");
        createdAccount.setAge(20);
        createdAccount.setEmail("test@test.com");

        Mockito.when(accountRepository.existsById(2L)).thenReturn(false);

        AccountVO accountVO = new AccountVO(2L, "updated account", "test@test.com", 20, null, null, null);
        AccountVO result = accountService.update(accountVO);

        assertThat(result, nullValue());
    }

    @Test
    public void findByIdFound(){
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setAge(20);
        account.setEmail("test@test.com");

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<AccountVO> result = accountService.findById(1L);

        assertTrue(result.isPresent());
        AccountVO accountResult = result.get();
        assertThat(accountResult, hasProperty("id", is(1L)));
        assertThat(accountResult, hasProperty("name", is("test")));
        assertThat(accountResult, hasProperty("age", is(20)));
        assertThat(accountResult, hasProperty("email", is("test@test.com")));
    }

    @Test
    public void findByIdNotFound(){
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AccountVO> result = accountService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void findAllWithoutFilter(){
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setId(1L);
        account.setName("account1");
        account.setAge(20);
        account.setEmail("test@test.com");
        accounts.add(account);

        Account account2 = new Account();
        account2.setId(1L);
        account2.setName("account2");
        account2.setAge(20);
        account2.setEmail("test@test.com");
        accounts.add(account2);

        Mockito.when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountVO> result = accountService.findAll(null);

        assertThat(result, containsInAnyOrder(
                hasProperty("name", is("account1")),
                hasProperty("name", is("account2"))
        ));
    }

    @Test
    public void findAllWithFilter(){
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setId(1L);
        account.setName("account1");
        account.setAge(20);
        account.setEmail("filter@test.com");
        accounts.add(account);

        Account account2 = new Account();
        account2.setId(1L);
        account2.setName("account2");
        account2.setAge(20);
        account2.setEmail("filter@test.com");
        accounts.add(account2);

        AccountFilterParam params = new AccountFilterParam();
        params.setEmail("filter@test.com");

        Mockito.when(accountRepository.findAll(Mockito.any(Example.class))).thenReturn(accounts);

        List<AccountVO> result = accountService.findAll(params);

        assertThat(result, containsInAnyOrder(
                hasProperty("email", is("filter@test.com")),
                hasProperty("email", is("filter@test.com"))
        ));
    }

}
