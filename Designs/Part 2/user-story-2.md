# User Story 2

As an admin at a top-level customer org
I want to get the details of a direct supplier that I specify by ID

Spec:
Given a user U is present in a top-level customer org O
And a list of direct suppliers is present for O
When U requests the details for a specific direct supplier
They are provided with {supplier}

Test 1:

- Somehow set things up so that when the domain tries to look up the company for user ZU123,
  it gets back ZC789
- Somehow set things up so that when the domain tries to look up the supply chain for ZC789,
  it gets back the direct suppliers as a list
- Somehow set things up so that the domain checks that list to ensure that the given Id is a direct supplier
- Somehow set things up so that the domain can pull the given direct supplier as a map
- When we query the domain for ZS456, a given direct supplier of ZC789
- Then assert that the reply is [ZS456]

Test 2:
- Somehow set things up so that when the domain tried to look up the company for user ZU123,
  it gets back ZC789
- Somehow set things up so that when the domain tries to look up the supply chain for ZC789,
  it gets back the direct suppliers as a list
- Somehow set things up so that when the domain validates the presence of ZS654 in the list,
  it is not found.
- When we query the domain for ZS654 as a direct supplier of ZC789
- Then assert that the reply is a not found response

Test 3:

- Somehow set things up so that no userId is provided to the domain
- When we query the API with no given userId
- Then assert that the response is that of a bad request

Test 4:

- Somehow set things up so that an invalid userId is provided to the domain
- When we query the API with an invalid userId
- Then assert that the response is that of not found