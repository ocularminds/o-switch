package com.ocularminds.oswitch.core;

public interface CoreBankingProvider {

  Fault balance(Entry entry);

  Fault transfer(Entry entry);

  Fault bill(Entry entry);

}
