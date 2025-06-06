# Stubs

Stubs provide canned answers to calls made during the test, usually not responding at all to
anything outside what's programmed in for the test. Stubs may also record information about calls,
such as an email gateway stub that remembers the messages it 'sent', or maybe only how many messages it 'sent'.

## When to use it

### Use a stub when you want to:

* verify indirect outputs from the system under test
* avoid real database access
* avoid network access
* avoid non-deterministic behavior
* make testing easier
* test your code in isolation
* verify indirect outputs from the system under test

## How to use it

###Use a stub when you want to:

* todo

## Example

```kotlin
class StubEmailService : EmailService {
     private val emails = mutableListOf<Email>()
 
     override fun sendEmail(email: Email) {
         emails.add(email)
     }
 
     fun sentEmails(): List<Email> {
         return emails
     }
 }
 ```
