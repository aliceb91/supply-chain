# User Story 3

As an admin at a top-level customer org
I want to add a direct supplier to my supply chain

Spec:
Given a user U is present in a top-level customer org O
And a list of direct suppliers is present for O
And a target supplier S currently exists
And S currently doesn't exist in O's supplier list
When U requests to add S to O's supplier list
They are provided with an updated company data entry

Test 1:

- Somehow set things up so that when the domain tries to look up the company for user ZU123, it gets back ZC789
- Somehow set things up so that when the domain tries to look up the supply chain for ZC789, it gets back the direct suppliers as a list
- Somehow set things up so that the domain can check the list for duplicates
- Somehow set things up so that the domain can add the supplier to the suppliers field of ZC789
- When we request that the domain adds ZS654 to the supply chain of ZC789
- Then assert that the reply is [ZS456, ZS654]

Test 2:

- Somehow set things up so that when the domain tried to look up the company for user ZU123, it gets back ZC789
- Somehow set things up so that when the domain tries to look up the supply chain for ZX789, it gets back the direct suppliers as a list
- Somehow set things up so that when the domain validates the presence of ZS654 in the list, it is found.
- When we attempt to add ZS654 to the supply chain of ZX789
- Then assert that there is a conflict

Test 3: 

- Somehow set things up so that when the domain tried to look up the company for user ZU123, it gets back ZC789
- Somehow set things up so that when the domain tries to look up the supply chain for ZC789, it gets back the direct suppliers as a list
- Somehow set things up so that when the domain validates the presence of ZS654 in the list, it is not found.
- Somehow set things up so that when the domain attempt to add the supplier to the supply chain of ZC789, it is informed that no such supplier exists
- When we attempt to add ZS654 to the supply chain of ZC789
- Then assert that the reply is not found

Test 4:

- Somehow set things up so that no userId is provided to the domain
- When we query the API with no given userID
- Then assert that the response is that of a bad request

Test 5:

- Somehow set things up so that no targetCompanyId is provided to the domain
- When we query the API with no given targetCompanyId
- Then assert that the response is that of a bad request

Test 6:

- Somehow set things up so that an invalid userId is provided to the domain
- When we query the API with an invalid userId
- Then assert that the response is that of not found