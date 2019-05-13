package com.crud.sample.restful.service;

import com.crud.sample.restful.data.AccountFilterParam;
import com.crud.sample.restful.model.Account;
import com.crud.sample.restful.model.Address;
import com.crud.sample.restful.repository.AccountRepository;
import com.crud.sample.restful.vo.AccountVO;
import com.crud.sample.restful.vo.AddressVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    /**
     * Find all accounts
     * It can be filtered given account filter
     * @param params
     * @return
     */
    public List<AccountVO> findAll(AccountFilterParam params){

        Example<Account> filter = buildFilter(params);
        List <Account> accounts;
        if(filter == null){
            accounts = accountRepository.findAll();
        } else {
            accounts = accountRepository.findAll(filter);
        }

        return accounts
                .stream()
                .map(a -> getAccountVO(a))
                .collect(Collectors.toList());
    }

    /**
     * Find account by id
     * @param id
     * @return
     */
    public Optional<AccountVO> findById(Long id){
        return getResult(accountRepository.findById(id));
    }

    /**
     * Create new account
     * @param accountVO
     * @return
     */
    public AccountVO create(AccountVO accountVO){
        return getAccountVO(accountRepository.save(getAccount(accountVO)));
    }

    /**
     * Update existing account
     * @param accountVO
     * @return
     */
    public AccountVO update(AccountVO accountVO){
        if (accountRepository.existsById(accountVO.getId())){
            return getAccountVO(accountRepository.save(getAccount(accountVO)));
        }

        return null;
    }

    /**
     * Delete existing account
     * @param id
     */
    public void delete(Long id){
        accountRepository.deleteById(id);
    }

    /**
     * Get valid result given account
     * @param accountResult
     * @return
     */
    private Optional<AccountVO> getResult(Optional<Account> accountResult){
        return accountResult.isPresent() ?
                Optional.of(getAccountVO(accountResult.get())) : Optional.empty();
    }

    /**
     * Convert to AccountVO
     * @param account
     * @return
     */
    private AccountVO getAccountVO(Account account){
        return AccountVO.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .age(account.getAge())
                .created(account.getCreated())
                .updated(account.getUpdated())
                .addresses(getAddressesVO(account.getAddresses()))
                .build();
    }

    /**
     * Convert to AddressVO
     * @param addresses
     * @return
     */
    private List<AddressVO> getAddressesVO(Collection<Address> addresses){
        if (CollectionUtils.isEmpty(addresses)){
            return Collections.emptyList();
        }

        return addresses
                .stream()
                .map(address -> AddressVO.builder()
                        .id(address.getId())
                        .address(address.getAddress())
                        .created(address.getCreated())
                        .updated(address.getUpdated())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Convert to Account
     * @param accountVO
     * @return
     */
    private Account getAccount(AccountVO accountVO){
        return Account.builder()
                .id(accountVO.getId())
                .name(accountVO.getName())
                .email(accountVO.getEmail())
                .age(accountVO.getAge())
                .addresses(getAddresses(accountVO.getAddresses()))
                .build();
    }

    /**
     * Convert to Address
     * @param addressesVO
     * @return
     */
    private List<Address> getAddresses(Collection<AddressVO> addressesVO){
        if (CollectionUtils.isEmpty(addressesVO)){
            return Collections.emptyList();
        }

        return addressesVO
                .stream()
                .map(addressVO -> Address.builder()
                        .id(addressVO.getId())
                        .address(addressVO.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Build account filter
     * @param params
     * @return
     */
    private Example<Account> buildFilter(AccountFilterParam params){
        if (params != null){
            if (!StringUtils.isEmpty(params.getEmail())){
                Account accountFilter = new Account();
                accountFilter.setEmail(params.getEmail());
                ExampleMatcher emailMatcher = ExampleMatcher.matchingAny()
                        .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
                return Example.of(accountFilter, emailMatcher);
            }
            //Here we can add more account filters
        }

        return null;
    }
}
