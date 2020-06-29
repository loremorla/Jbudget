package it.unicam.cs.pa.jbudget105124.Model.Account;

public interface AccountManager {

        static Account createAccount(int ID,String name,String description,double ob,AccountType at){
            return new AccountBasic(ID,name,description,ob,at);
        }
}
