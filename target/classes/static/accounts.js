var accounts = [];

function findAccount (accountId) {
  return accounts[findAccountKey(accountId)];
}

function findAccountKey (accountId) {
  for (var key = 0; key < accounts.length; key++) {
    if (accounts[key].id == accountId) {
      return key;
    }
  }
}

var accountService = {
  findAll(fn) {
    axios
      .get('/api/v1/accounts')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/v1/accounts/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(account, fn) {
    axios
      .post('/api/v1/accounts', account)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, account, fn) {
    axios
      .put('/api/v1/accounts', account)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteAccount(id, fn) {
    axios
      .delete('/api/v1/accounts/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#account-list',
  data: function () {
    return {accounts: [], searchKey: ''};
  },
  computed: {
    filteredAccounts() {
      return this.accounts.filter((account) => {
      	return account.name.indexOf(this.searchKey) > -1
      	  || account.email.indexOf(this.searchKey) > -1
      	  || account.age.toString().indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
    accountService.findAll(r => {this.accounts = r.data; accounts = r.data})
  }
});

var Account = Vue.extend({
  template: '#account',
  data: function () {
    return {account: findAccount(this.$route.params.account_id)};
  }
});

var AccountEdit = Vue.extend({
  template: '#account-edit',
  data: function () {
    return {
        account: findAccount(this.$route.params.account_id),
        addressInput: ''
    };
  },
  methods: {
    updateAccount: function () {
      accountService.update(this.account.id, this.account, r => router.push('/'))
    },
    addAddress(){
      var newAddress = this.addressInput.trim();
      if (!newAddress) {return;}
      this.account.addresses.push({address: newAddress});
      this.addressInput = '';
    },
    deleteAddress(index) {
      this.account.addresses.splice(index, 1);
    }
  }
});

var AccountDelete = Vue.extend({
  template: '#account-delete',
  data: function () {
    return {account: findAccount(this.$route.params.account_id)};
  },
  methods: {
    deleteAccount: function () {
      accountService.deleteAccount(this.account.id, r => router.push('/'))
    }
  }
});

var AddAccount = Vue.extend({
  template: '#add-account',
  data() {
    return {
      account: {name: '', email: '', age: 0, addresses: []},
      addressInput: ''
    }
  },
  methods: {
    createAccount() {
      accountService.create(this.account, r => router.push('/'))
    },
    addAddress(){
        var newAddress = this.addressInput.trim();
        if (!newAddress) {return;}
        this.account.addresses.push({address: newAddress});
        this.addressInput = '';
    },
    deleteAddress(index) {
      this.account.addresses.splice(index, 1);
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/account/:account_id', component: Account, name: 'account'},
		{path: '/add-account', component: AddAccount},
		{path: '/account/:account_id/edit', component: AccountEdit, name: 'account-edit'},
		{path: '/account/:account_id/delete', component: AccountDelete, name: 'account-delete'}
	]
});

new Vue({
  router
}).$mount('#accountApp')
