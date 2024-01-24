# User story 1

As an admin at a top-level customer org
I want to view a list of my direct suppliers

Spec:
Given a user U is present in a top-level customer org O
And a list of direct suppliers [Suppliers] is present for O
When U requests a list of their direct suppliers
Then they are provided with [Suppliers]

Test 1:

- Somehow set things up so that when the domain tries to look up the company for user ZU123,
it gets back ZC789
- Somehow set things up so that when the domain tries to look up the supply chain for ZC789,
it gets back a supply chain with direct suppliers ZS456
- When we query the domain for direct suppliers of ZU123
- Then assert that the reply is [ZS456]

Test 2:

- Somehow set things up do that no userId is provided to the domain
- When we query the API with no given userId
- Then assert that the response is that of a bad request

Test 3:

- Somehow set things up do that an invalid userId is provided to the domain
- When we query the APO with an invalid userId
- Then assert that the response is that of not found